package com.bscl

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class InstructionQueueSuite extends FunSuite with ShouldMatchers {
  test("Can construct queue") {
    val queue = new InstructionQueue
  }
}