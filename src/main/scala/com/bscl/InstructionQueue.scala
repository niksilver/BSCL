package com.bscl

/**
 * Basic implementation of the BSCL instruction queue.
 */
class InstructionQueue private(val messages: List[InstructionMessage]) {
  /**
   * Construct and empty queue
   */
  def this() = this(List())
  
  def place(m: InstructionMessage) =
    new InstructionQueue(messages ++ List(m))
}


/**
 * Simple implementation of an instruction message.
 */
case class InstructionMessage(instructionType: Int) {
  def priority = InstructionPriority.Low
}

/**
 * Priorities for InstructionType
 */
object InstructionPriority extends Enumeration {
  type InstructionPriority = Value
  val Low, Medium, High = Value
}