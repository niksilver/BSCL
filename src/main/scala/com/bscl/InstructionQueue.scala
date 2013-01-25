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
}


/**
 * Simple implementation of an instruction message.
 */
case class InstructionMessage(instructionType: Int) {
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