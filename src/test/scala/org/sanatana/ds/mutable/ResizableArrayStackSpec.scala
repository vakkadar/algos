package org.sanatana
package ds
package mutable

import org.scalatest.{FeatureSpec, FlatSpec, Matchers}

class ResizableArrayStackSpec extends FlatSpec with Matchers {

  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = ResizableArrayStack[Int]()
    stack.push(1)
    stack.push(2)
    stack.pop() should be(2)
    stack.pop() should be(1)
  }


  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack =ResizableArrayStack[Int]()
    a [NoSuchElementException] should be thrownBy {
      emptyStack.pop()
    }
  }

  "Iterator" should "throw IllegalStateException if an stack to be pushed is full" in {
    val fullStack =ResizableArrayStack[Int]()
    fullStack.push(1)
    fullStack.push(2)
    an [IllegalStateException] should be thrownBy {
      fullStack.push(3)
    }
  }

  it should "return values in reverse order of the stack" in {
    val fullStack =ResizableArrayStack[Int]()

    fullStack.push(1)
    fullStack.push(2)
    val iterator = fullStack.iterator

    iterator.hasNext should be(true)
    iterator.next() should be(2)
    iterator.hasNext should be(true)
    iterator.next() should be(1)
    iterator.hasNext should be(false)
    an [IllegalStateException] should be thrownBy {
      iterator.remove()
    }
    fullStack.isEmpty should be(false)
  }

  "its" should "capacity is doubles or goes 1/4th " in {
    val stack = ResizableArrayStack[Int]()
    stack.push(1)
    stack.push(2)
    stack.pop() should be(2)
    stack.pop() should be(1)
    stack.push(3)
    stack.push(4)
    stack.push(5)
    stack.getCapacity should be(4)
    stack.push(6)
    stack.push(7)
    stack.getCapacity should be(8)
    stack.pop should be(7)
    stack.pop should be(6)
    stack.pop should be(5)
    stack.getCapacity should be(4)
    stack.push(8)
    stack.push(9)
    stack.getCapacity should be(4)
    stack.push(10)
    stack.getCapacity should be(8)
  }
}