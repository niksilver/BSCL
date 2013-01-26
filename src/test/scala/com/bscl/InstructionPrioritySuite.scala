package com.bscl

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class InstructionPrioritySuite extends FunSuite with ShouldMatchers {
  test("Ordering") {
    import InstructionPriority._
    
    High should be > (Low)
    High should be > (Medium)
    Medium should be > (Low)
    
    Low should be < (High)
    Medium should be < (High)
    Low should be < (Medium)
  }
}