package com.bscl

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class InstructionPrioritySuite extends FunSuite with ShouldMatchers {
  test("Ordering") {
    InstructionPriority.High should be > (InstructionPriority.Low)
    InstructionPriority.High should be > (InstructionPriority.Medium)
    InstructionPriority.Medium should be > (InstructionPriority.Low)
    
    InstructionPriority.Low should be < (InstructionPriority.High)
    InstructionPriority.Medium should be < (InstructionPriority.High)
    InstructionPriority.Low should be < (InstructionPriority.Medium)
  }
}