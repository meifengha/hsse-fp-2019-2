import java.beans.Expression

import scala.collection.mutable
import scala.util.matching.Regex

object Calculator {

  val stackOperations = new mutable.Stack[String]()
  val stackOperands = new mutable.Stack[Double]()
  val queue = new mutable.Queue[String]()

  def stackCompute(operations: mutable.Stack[String], operands: mutable.Stack[Double]) : Unit = {
    if (operations.nonEmpty) {
      val next = operations.pop()
      if (!next.equals("(")) {
        next match {
          case "+" =>
            val op1: Double = operands.pop()
            val op2: Double = operands.pop()

            operands.push(op2 + op1)
          case "-" =>
            if (operands.size > 1) {
              val op1: Double = operands.pop()
              val op2: Double = operands.pop()

              operands.push(op2 - op1)
            } else {
              operands.push(-operands.pop())
            }

          case "*" =>
            val op1: Double = operands.pop()
            val op2: Double = operands.pop()

            operands.push(op2 * op1)
          case "/" =>
            val op1: Double = operands.pop()
            val op2: Double = operands.pop()

            operands.push(op2 / op1)
          case "^" =>
            val op1: Double = operands.pop()
            val op2: Double = operands.pop()

            operands.push(Math.pow(op2, op1))
        }
        stackCompute(operations, operands)
      } else {
        return
      }
    }
  }

  def pushToQueue(expression: String) : Unit = {
    if (expression.nonEmpty) {
      val array : Array[String] = expression.split(" ")
      queue.addAll(array)
    }
  }

  def getPriority(operation : String) : Int  = {
    operation match {
      case "+" => 1
      case "-" => 1
      case "*" => 2
      case "/" => 2
      case "^" => 3
      case "(" => 0
    }
  }

  def isMorePriority(oper1 : String, oper2 : String) : Boolean = {
    getPriority(oper1) < getPriority(oper2)
  }

  def isRightOperation(oper : String) : Boolean = {
    oper.equals("+") || oper.equals("-") || oper.equals("*") || oper.equals("/") || oper.equals("^")
  }

  var checker: Boolean = false;

  def calculateIter(exp : mutable.Queue[String], operations : mutable.Stack[String], operands : mutable.Stack[Double]) : Unit = {
    if (exp.nonEmpty) {
      val next: String = exp.front
      if (next.equals("")) {
        calculateIter(exp.tail, operations, operands)
      }
      else {
        if (isRightOperation(next)) {
          if (operations.nonEmpty && isMorePriority(operations.top, next)) {
            operations.push(next)
          } else if (operations.isEmpty) {
            operations.push(next)
          } else {
            stackCompute(operations, operands)
            operations.push(next)
          }
        } else if (next.equals(")")) {
          stackCompute(operations, operands)
        } else if (next.equals("(")) {
          operations.push(next)
        } else {
          operands.push(next.toDouble)
        }
        calculateIter(exp.tail, operations, operands)
      }
    }
  }

  def runCalculate(inputLine: String): String = {
    stackOperations.clear()
    stackOperands.clear()
    queue.clear()
    try {
      pushToQueue(inputLine)
      calculateIter(queue, stackOperations, stackOperands)
      stackCompute(stackOperations, stackOperands)
      stackOperands.top.toString()
    } catch {
      case e: Exception =>
        Console.err.println("You've put invalid sequence of expession!\n" +
          "")
        ""
    }
  }
}