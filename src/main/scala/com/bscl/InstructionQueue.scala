package com.bscl

/**
 * Basic implementation of the BSCL instruction queue.
 * @param messages  The messages in the queue, with the front of the queue
 *     as the first item in the list.
 */
class InstructionQueue private(val messages: List[InstructionMessage]) {
  /**
   * Construct and empty queue
   */
  def this() = this(List())
  
  /**
   * Place a message at the back of the queue, but it will be pushed
   * forward according to its priority.
   */
  def place(m: InstructionMessage) = {
    val (before, after) = messages.span( _.priority >= m.priority )
    new InstructionQueue(before ++ List(m) ++ after)
  }
  
  /**
   * Number of messages in queue
   */
  def size = messages.size
  
  /**
   * Remove a message that satisfies a particular predicate
   */
  def remove(p: InstructionMessage => Boolean) =
    new InstructionQueue(messages filterNot p)
  
  /**
   * Retrieve the message at the front of the queue.
   * Returns the message and the new queue as a pair.
   */
  def retrieve =
    try {
      (messages.head, new InstructionQueue(messages.tail))
    } catch {
      case e: NoSuchElementException => throw new EmptyQueueException
    }
  
  /**
   * True if the queue is empty
   */
  def isEmpty = messages.isEmpty
}

/**
 * Thrown if a trying an operation on empty queue but the queue
 * is required to be non-empty.
 */
class EmptyQueueException extends NoSuchElementException

/**
 * Simple implementation of an instruction message. For convenience
 * some arguments are optional
 * @param instructionType  Required
 * @param productCode  Defaults to 1 if not specified
 * @param uom  An integer 0 to 255, which is strictly a byte, but on the
 *     JVM platform a real byte is unsigned, so we're using an int to avoid
 *     ambiguity. Defaults to 0.
 * @param timestamp  A timestamp in some unspecified units, defaulting to 0. 
 */
case class InstructionMessage(val instructionType: Int,
    productCode: Int = 1,
    quantity: Int = 1,
    uom: Int = 0,
    timestamp: Int = 0) {
  import InstructionPriority._
  
  def priority =
    if (0 < instructionType && instructionType < 11) High
    else if (10 < instructionType && instructionType < 91) Medium
    else Low

}

/**
 * Priorities for InstructionType
 */
object InstructionPriority extends Enumeration {
  type InstructionPriority = Value
  val Low, Medium, High = Value
}