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
  
  test("Constructor - correct argument defaults") {
    val msg = new InstructionMessage(3)
    msg.instructionType should equal (3)
    msg.productCode should equal (1)
    msg.quantity should equal (1)
    msg.uom should equal (0)
    msg.timeStamp should equal (1)
  }
  
  test("Constructor - can set all parameters") {
    val msg = new InstructionMessage(3, 11, 22, 33, 44)
    msg.instructionType should equal (3)
    msg.productCode should equal (11)
    msg.quantity should equal (22)
    msg.uom should equal (33)
    msg.timeStamp should equal (44)
  }
  
  test("isValid") {
    (new InstructionMessage(3, 11, 22, 33, 44)).isValid should be (true)

    // With defaults
    (new InstructionMessage(0)).isValid should be (false)
    (new InstructionMessage(1)).isValid should be (true)
    (new InstructionMessage(99)).isValid should be (true)
    (new InstructionMessage(100)).isValid should be (false)
    
    // instructionType bounds
    (new InstructionMessage(0,   11, 22, 33, 44)).isValid should be (false)
    (new InstructionMessage(1,   11, 22, 33, 44)).isValid should be (true)
    (new InstructionMessage(99,  11, 22, 33, 44)).isValid should be (true)
    (new InstructionMessage(100, 11, 22, 33, 44)).isValid should be (false)

    // productCode bounds
    (new InstructionMessage(3, 0, 22, 33, 44)).isValid should be (false)
    (new InstructionMessage(3, 1, 22, 33, 44)).isValid should be (true)

    // quantity bounds
    (new InstructionMessage(3, 11, 0, 33, 44)).isValid should be (false)
    (new InstructionMessage(3, 11, 1, 33, 44)).isValid should be (true)

    // UOM bounds
    (new InstructionMessage(3, 11, 22, -1,  44)).isValid should be (false)
    (new InstructionMessage(3, 11, 22, 0,   44)).isValid should be (true)
    (new InstructionMessage(3, 11, 22, 255, 44)).isValid should be (true)
    (new InstructionMessage(3, 11, 22, 256, 44)).isValid should be (false)

    // timeStamp bounds
    (new InstructionMessage(3, 11, 22, 33, 0)).isValid should be (false)
    (new InstructionMessage(3, 11, 22, 33, 1)).isValid should be (true)
  }
}