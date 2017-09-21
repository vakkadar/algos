package org.sanatana
package ds
package mutable

import scala.collection.mutable.ArrayBuilder
import scala.reflect.ClassTag

class FixedArrayStack[A:ClassTag](val capacity :Int) extends ds.Stack[A]{
  private [this] var internal:Array[A] = new Array[A](capacity)
  private [this] var startPosition = -1

  private def isFull :Boolean = startPosition == capacity -1

  def push(item: A): Unit = {
    if(isFull)
      throw new IllegalStateException("Cannot call pop on a empty stack")
    else{
      startPosition += 1
      internal(startPosition) = item
    }
  }

  def pop(): A = {
    if(isEmpty)
      throw new NoSuchElementException("Cannot call pop on a empty stack")
    else{
      val item = internal(startPosition)
      startPosition -= 1 // not sure how to solve loitering problem
      item
    }
  }


  def isEmpty: Boolean = startPosition == -1

  def size: Int =  startPosition+1

  override def iterator = new Iterator[A] {
    private [this] var n = startPosition
    def hasNext:Boolean  = n != -1
    def next():A = {
      val item = internal(n)
      n -= 1
      item
    }
    def remove():A = throw new IllegalStateException("Remove is not supported in iterator")
  }
}

object FixedArrayStack {
  def apply[A:ClassTag](initCapacity:Int =2):Stack[A] = new FixedArrayStack[A](initCapacity)


}