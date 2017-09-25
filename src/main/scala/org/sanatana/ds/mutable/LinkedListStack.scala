package org.sanatana
package ds
package mutable

import scala.reflect.ClassTag

class LinkedListStack[A:ClassTag] extends Stack[A]{

  private [this] var head:Node[A] = null
  private [this] var counter:Int = 0

  case class Node[A](item:A, nextNode:Node[A])

  override def push(item: A): Unit = {
    val newHead = Node(item, head)
    head = newHead
    counter += 1
  }

  override def pop(): A = {
    if (isEmpty)
      throw new NoSuchElementException("Cannot call pop on a empty stack")
    else {
      val item = head.item
      head = head.nextNode
      counter -= 1
      item
    }
  }

  override def isEmpty: Boolean = counter == 0

  override def size: Int = counter

  override def getCapacity: Int = Int.MaxValue

  override def iterator: Iterator[A] = new Iterator[A] {
    private [this] var iteratorHead :Node[A] = head
    def hasNext:Boolean  = null != iteratorHead
    def next():A = {
      val item = iteratorHead.item
      iteratorHead = iteratorHead.nextNode
      item
    }
    def remove():A = {
      throw new IllegalStateException("Remove is not supported in iterator")
      // if intermediate value is removed we need to adjust the array positions
      // can also lead to concurrent modification exception
      // if remove is attempted at the same time push or pop is attempted
    }
  }
}

object LinkedListStack {
  def apply[A:ClassTag](initCapacity:Int =2):Stack[A] = new LinkedListStack[A]()

}
