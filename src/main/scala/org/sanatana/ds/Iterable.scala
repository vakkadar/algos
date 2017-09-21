package org.sanatana
package ds

trait Iterable[A] {
  def iterator : Iterator[A]
}

trait Iterator[A]{
  def hasNext:Boolean
  def next():A
  def remove():A
}
