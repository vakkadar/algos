package org.sanatana
package ds
package app


import mutable.LinkedListStack
import scala.io.StdIn._
import scala.annotation._
object ArithmeticEvalExpressionApp extends App {

  val ops = LinkedListStack[String]()
  val values = LinkedListStack[Double]()

  import Constants._
  def loop(input:String):Unit = {
    if(input != ""){
      val s = input.trim
      s match {
        case LeftParen  =>
        case AdditionOperator | MultiplicationOperator| MultiplicationOperator|DivisionOperator|Sqrt => ops.push(s)
        case RightParen =>
          val op = ops.pop()
          val v1 = values.pop()
          val res =
            (op: @unchecked) match {
              case MultiplicationOperator => values.pop() * v1
              case AdditionOperator => values.pop() + v1
              case SubtractionOperator => values.pop() - v1
              case DivisionOperator => values.pop() / v1
              case Sqrt => math.sqrt(v1)
            }
          values.push(res)
        case _ =>
          try {
            val d = s.toDouble
            values.push(d)
          }catch{
            case ex => println ("Ignored")
          }
      }
      loop(readLine())
    }
  }
  loop(readLine())
  println(s"Result : ${values.pop()}")

}

object Constants{

  val LeftParen = "("
  val RightParen = ")"
  val AdditionOperator = "+"
  val SubtractionOperator = "-"
  val MultiplicationOperator = "*"
  val DivisionOperator = "/"
  val Sqrt = "sqrt"

}
