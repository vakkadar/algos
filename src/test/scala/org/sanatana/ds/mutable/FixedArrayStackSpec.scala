package org.sanatana
package ds
package mutable

import org.scalatest.{FeatureSpec, FlatSpec, Matchers}

class FixedArrayStackSpec extends FlatSpec with Matchers {

  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = FixedArrayStack[Int]()
    stack.push(1)
    stack.push(2)
    stack.pop() should be(2)
    stack.pop() should be(1)
  }


  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack =FixedArrayStack[Int]()
    a [NoSuchElementException] should be thrownBy {
      emptyStack.pop()
    }
  }

  "Iterator" should "throw IllegalStateException if an stack to be pushed is full" in {
    val fullStack =FixedArrayStack[Int]()
    fullStack.push(1)
    fullStack.push(2)
    an [IllegalStateException] should be thrownBy {
      fullStack.push(3)
    }
  }

  it should "return values in reverse order of the stack" in {
    val fullStack =FixedArrayStack[Int]()

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
}