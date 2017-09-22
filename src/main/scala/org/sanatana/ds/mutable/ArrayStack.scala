package org.sanatana
package ds
package mutable

import scala.collection.mutable.ArrayBuilder
import scala.reflect.ClassTag

class FixedArrayStack[A:ClassTag](val capacity :Int) extends ds.Stack[A]{
  private [this] var internal:Array[A] = new Array[A](capacity)
  private [this] var startPosition = 0
  def getCapacity:Int = capacity
  private def isFull :Boolean = size == capacity

  def push(item: A): Unit = {
    if(isFull)
      throw new IllegalStateException("Cannot call pop on a empty stack")
    else{
      internal(startPosition) = item
      startPosition += 1
    }
  }

  def pop(): A = {
    if(isEmpty)
      throw new NoSuchElementException("Cannot call pop on a empty stack")
    else{
      startPosition -= 1  // not sure how to solve loitering problem
      val item = internal(startPosition)
      item
    }
  }


  def isEmpty: Boolean = startPosition == 0

  def size: Int =  startPosition

  override def iterator = new Iterator[A] {
    private [this] var n = startPosition
    def hasNext:Boolean  = n != 0
    def next():A = {
      n -= 1
      internal(n)
    }
    def remove():A = {
      throw new IllegalStateException("Remove is not supported in iterator")
      // if intermediate value is removed we need to adjust the array positions
      // can also lead to concurrent modification exception
      // if remove is attempted at the same time push or pop is attempted
    }
  }
}

object FixedArrayStack {
  def apply[A:ClassTag](initCapacity:Int =2):Stack[A] = new FixedArrayStack[A](initCapacity)

}