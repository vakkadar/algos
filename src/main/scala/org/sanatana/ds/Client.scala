package org.sanatana
package ds

import scala.io.StdIn._

trait Client[A] {

  def removeItem():A
  def addItem(item:A):Unit
  def converter:String => A
  def loop(input:String):Unit = {
    if(input != ""){
      if(input == "-")
        println(removeItem())
      else
        addItem(converter(input))
      loop(readLine())
    }
    else
      println("Exiting")
  }

}
