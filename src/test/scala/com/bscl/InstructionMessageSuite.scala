package com.bscl

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class InstructionMessageSuite extends FunSuite with ShouldMatchers {
  test("Message priority") {
    
    InstructionMessage(1).priority should equal (InstructionPriority.High)
    InstructionMessage(10).priority should equal (InstructionPriority.High)
    InstructionMessage(11).priority should equal (InstructionPriority.Medium)
    InstructionMessage(90).priority should equal (InstructionPriority.Medium)
    InstructionMessage(91).priority should equal (InstructionPriority.Low)
    InstructionMessage(99).priority should equal (InstructionPriority.Low)
  }
}