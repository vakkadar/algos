package org.sanatana
package ds

trait Bag[A] extends  Iterable[A] {
  def add(item:A):Unit
  def isEmpty:Boolean
  def size:Int
}

trait Removable[A] {
  def remove():A
}

trait Stack[A] extends Bag[A] with Removable[A]{
  def push(item:A)
  override def add(item:A):Unit = push(item)
  def pop():A
  override def remove():A = pop()
}

trait Queue[A] extends Bag[A] with Removable[A]{
  def enqueue(item:A)
  override def add(item:A):Unit = enqueue(item)
  def dequeue():A
  override def remove():A = dequeue()
}
