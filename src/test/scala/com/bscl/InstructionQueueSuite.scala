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
    val emptyQueue = new InstructionQueue
    def fill(n: Int) = (1 to n).foldLeft(emptyQueue)( (queue, _) => queue.place(msg) )
    
    fill(10).size should equal (10)
    fill(100).size should equal (100)
    fill(1000).size should equal (1000)
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
  
  test("Can remove messages") {
    val m1 = InstructionMessage(1)
    val m2 = InstructionMessage(2)
    val m3 = InstructionMessage(3)
    val m4 = InstructionMessage(4)
    
    val queue = (new InstructionQueue).place(m1).place(m2).place(m3).place(m4)
    
    // Make sure we know what the queue looks like first
    queue.messages should equal (List(m1, m2, m3, m4))

    queue.remove( _.instructionType == 1 ).messages should equal (List(m2, m3, m4))
    queue.remove( _.instructionType % 2 == 0 ).messages should equal (List(m1, m3))
    queue.remove( _ => true ).messages should equal (List())
  }
  
  test("Can retrieve message at front of queue") {
    val m1 = InstructionMessage(1)
    val m2 = InstructionMessage(2)
    val m3 = InstructionMessage(3)
    val m4 = InstructionMessage(4)
    
    val queue0 = (new InstructionQueue).place(m1).place(m2).place(m3).place(m4)
    
    val (out1, queue1) = queue0.retrieve
    out1 should equal (m1)
    queue1.messages should equal (List(m2, m3, m4))
    
    val (out2, queue2) = queue1.retrieve
    out2 should equal (m2)
    queue2.messages should equal (List(m3, m4))
    
    val (out3, queue3) = queue2.retrieve
    out3 should equal (m3)
    queue3.messages should equal (List(m4))
    
    val (out4, queue4) = queue3.retrieve
    out4 should equal (m4)
    queue4.messages should equal (List())
  }
  
  test("Can see if queue is empty") {
    val queue0 = new InstructionQueue
    val m1 = InstructionMessage(1)
    
    queue0.isEmpty should equal (true)
    queue0.place(m1).isEmpty should equal (false)
    
    // The queue after placing and then retrieving a message
    val queue02 = queue0.place(m1).retrieve._2
    queue02.isEmpty should equal (true)
  }
  
  test("Throws exception trying to retrieve message from empty queue") {
    evaluating {
      (new InstructionQueue).retrieve
    } should produce [EmptyQueueException]
  }
  
  test("Throws InvalidMessageException if trying to place invalid message") {
    evaluating {
      val queue = new InstructionQueue
      val msg = InstructionMessage(0)
      queue.place(msg)
    } should produce [InvalidMessageException]
  }
}