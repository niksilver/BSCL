package com.bscl

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class InstructionQueueSuite extends FunSuite with ShouldMatchers {
  test("Can construct queue") {
    val queue = new InstructionQueue
  }
  
  test("Supports any number of instruction messages") {
    val m0 = InstructionMessage(0)
    val m1 = InstructionMessage(1)
    val m2 = InstructionMessage(2)
    
    val queue = new InstructionQueue
    val queue2 = queue.place(m0).place(m1).place(m2)
    
    queue2.messages should equal (List(m0, m1, m2))
  }
  
}