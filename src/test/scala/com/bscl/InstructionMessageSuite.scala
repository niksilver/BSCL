package com.bscl

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class InstructionMessageSuite extends FunSuite with ShouldMatchers {
  test("Message priority") {
    val m0 = InstructionMessage(0)
    
    m0.priority should equal (InstructionPriority.Low)
  }
}