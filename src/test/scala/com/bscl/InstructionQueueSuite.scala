package com.bscl

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class InstructionQueueSuite extends FunSuite with ShouldMatchers {
  test("Can construct queue") {
    val queue = new InstructionQueue
  }
  
  test("Supports any number of instruction messages") {
    val m1 = InstructionMessage(1)
    val m2 = InstructionMessage(2)
    val m3 = InstructionMessage(3)
    
    val queue = new InstructionQueue
    val queue2 = queue.place(m1).place(m2).place(m3)
    
    queue2.messages should equal (List(m1, m2, m3))
  }
  
}