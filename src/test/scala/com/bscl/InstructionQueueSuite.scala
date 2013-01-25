package com.bscl

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class InstructionQueueSuite extends FunSuite with ShouldMatchers {
  test("Can construct queue") {
    val queue = new InstructionQueue
  }
  
  test("Supports any number of instruction messages (1)") {
    val m1 = InstructionMessage(1)
    val m2 = InstructionMessage(2)
    val m3 = InstructionMessage(3)
    
    val queue = new InstructionQueue
    val queue2 = queue.place(m1).place(m2).place(m3)
    
    queue2.messages should equal (List(m1, m2, m3))
  }

  test("Supports any number of instruction messages (2)") {
    val msg = InstructionMessage(1)
    def place(n: Int, accum: InstructionQueue): InstructionQueue = n match {
      case 0 => accum
      case _ => place(n-1, accum.place(msg))
    }
    
    place(10, new InstructionQueue).size should equal (10)
    place(100, new InstructionQueue).size should equal (100)
    place(1000, new InstructionQueue).size should equal (1000)
  }
  
  test("Prioritises messages according to instruction type") {
    val m10 = InstructionMessage(10)
    val m50 = InstructionMessage(50)
    val m95 = InstructionMessage(95)
    
    // Make a queue with a low priority item first in and
    // a medium priority item next
    val queueA = new InstructionQueue().place(m95).place(m50)
    
    // The medium priority item should appear first
    queueA.messages should equal (List(m50, m95))
    
    // Place medium, place low
    val queueB = new InstructionQueue().place(m50).place(m95)
    
    // Should be medium, low
    queueB.messages should equal (List(m50, m95))
    
    // Place Low, High, Medium
    val queueC = new InstructionQueue().place(m95).place(m10).place(m50)
    
    // The higher priority items should appear first
    queueC.messages should equal (List(m10, m50, m95))
    
  }
  
  test("Size") {
    val queue = new InstructionQueue
    val m1 = InstructionMessage(1)
    val m2 = InstructionMessage(2)
    val m3 = InstructionMessage(3)

    queue.size should equal(0)
    queue.place(m1).size should equal (1)
    queue.place(m1).place(m2).size should equal (2)
    queue.place(m1).place(m2).place(m3).size should equal (3)
  }
  
}