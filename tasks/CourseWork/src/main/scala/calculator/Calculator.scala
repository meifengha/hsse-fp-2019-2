package calculator

import scala.math.BigDecimal
import java.math.MathContext
import scala.math.sqrt



class Calculator
{
  private val precision = 10
  private var prevValue: String = _
  private var currValue: String = _
  private var operation: String = _
  private var isNewValue: Boolean = _
  
  reset()

  def getCurrValue: String = currValue
  
  def pressButton(name: String): Unit = name match
  {
      case "C" => reset()
      case "=" => completeOperation()
      case "<=" => { setOperation(name) ; completeOperation() }
      case "+" | "-" | "*" | "/" => setOperation(name)
      case "tan" | "sin" | "cos"  => { setOperation(name) ; completeOperation() }
      case "sqrt(x)" => { setOperation(name) ; completeOperation() }
      case "Pi" => {currValue = Math.PI.toString(); setOperation("=")}
      case _ => appendDigit(name)
    }

  private def reset(): Unit = 
  {
    currValue = "0"
    setOperation("=")
  }

  private def appendDigit(digit: String): Unit = 
  {
    val v = if (isNewValue && digit != ".") "" 
            else currValue
    if (isDuplicatedDot(v, digit) || isLengthMaximal(v)) throw new IllegalArgumentException
    currValue = dropLeftZeros(v + digit)
    isNewValue = false
  }

  private def setOperation(op: String): Unit = 
  {
    operation = op
    prevValue = currValue
    isNewValue = true
  }

  private def completeOperation(): Unit =
  {
    try 
    {
      currValue = dropRightZeros(round(evaluate()).toString)
    }
    catch 
    {
      case ex: RuntimeException => currValue = "Error"
    }
    isNewValue = true
  }

  private def evaluate(): BigDecimal = 
  {
    val x = BigDecimal(prevValue)
    val y = BigDecimal(currValue)
    val z = BigDecimal(10)
    operation match 
    {
      case "<=" => (x - (x % 10)) / 10
      case "+" => x + y
      case "-" => x - y
      case "*" => x * y
      case "/" => x / y
      case "tan" => BigDecimal.double2bigDecimal(Math.tan(y.toDouble))
      case "sin" => BigDecimal.double2bigDecimal(Math.sin(y.toDouble))
      case "cos" => BigDecimal.double2bigDecimal(Math.cos(y.toDouble))
      case "sqrt(x)" => sqrt(x.toDouble)
      case _ => y
    }
  }

  private def round(v: BigDecimal): BigDecimal =
  {
    v.round(new MathContext(precision))
  }

  private def isLengthMaximal(v: String): Boolean = v.replace(".", "").length == precision

  private def isDuplicatedDot(v: String, digit: String): Boolean = v.contains(".") && digit == "."

  
  private def dropRightZeros(v: String): String =
    if (v.contains(".") && (v.endsWith("0") || v.endsWith("."))) dropRightZeros(v.dropRight(1)) 
     else v

 
  private def dropLeftZeros(v: String): String =
    if (v.startsWith("0") && v.length > 1 && !v.startsWith("0.")) dropLeftZeros(v.drop(1)) 
      else v
}