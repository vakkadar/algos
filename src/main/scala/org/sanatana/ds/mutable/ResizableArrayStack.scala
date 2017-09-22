package org.sanatana
package ds
package mutable

import scala.reflect.ClassTag

class ResizableArrayStack[A:ClassTag](initCapacity:Int) extends ds.Stack[A]{
  private [this] var startPosition = 0
  private [this] var capacity = initCapacity
  private [this] var internal:Array[A] = new Array[A](capacity)

  def getCapacity = capacity

  def push(item: A): Unit = {
    if(needIncreaseCapacity)
      performHouseKeeping(true)
    internal(startPosition) = item
    startPosition += 1
  }


  def pop(): A = {
    startPosition -= 1
    val item = internal(startPosition)
    if(needDecreaseCapacity)
      performHouseKeeping(false)
    item
  }

  override def isEmpty: Boolean = startPosition == 0

  override def size: Int = startPosition

  def isFull:Boolean =  capacity == size

  def needIncreaseCapacity:Boolean = isFull

  def needDecreaseCapacity:Boolean = capacity  / 4  == startPosition

  override def iterator: Iterator[A] = new Iterator[A] {
    private [this] var n = startPosition
    def hasNext:Boolean  = n != 0
    def next():A = {
      n -= 1
      internal(n)
    }
    def remove():A = throw new IllegalStateException("Remove is not supported in iterator")
  }

  def performHouseKeeping(increase:Boolean):Unit = {
    val newCapacity = if(increase) capacity * 2 else capacity /2
      val tmp = new Array[A](newCapacity)
      for (i <- 0 until startPosition){
        tmp(i) = internal(i)
      }
      internal = tmp
      capacity = newCapacity
  }
}
object ResizableArrayStack {
  def apply[A:ClassTag](initCapacity:Int =2):Stack[A] = new ResizableArrayStack[A](initCapacity)

}