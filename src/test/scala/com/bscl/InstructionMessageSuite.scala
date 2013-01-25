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
  
  test("Constructors - correct argument defaults") {
    val msgA = new InstructionMessage(3)
    msgA.instructionType should equal (3)
    msgA.productCode should equal (1)
    msgA.quantity should equal (1)
    msgA.uom should equal (0)
    msgA.timestamp should equal (0)
  }
  
  test("Constructors - can set all parameters") {
    val msgA = new InstructionMessage(3, 11, 22, 33, 44)
    msgA.instructionType should equal (3)
    msgA.productCode should equal (11)
    msgA.quantity should equal (22)
    msgA.uom should equal (33)
    msgA.timestamp should equal (44)
  }
}