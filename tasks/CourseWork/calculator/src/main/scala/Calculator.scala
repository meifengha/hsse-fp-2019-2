import scala.collection.mutable
import scala.util.matching.Regex

trait Calculator {
  def runCalculate(inputLine: String): String = {
    try {
      val expression = infixToPostfix(inputLine)
      calculatePostfix(expression).toString
    } catch {
      case e: NoSuchElementException =>
        Console.err.println(e.getMessage)
        ""
      case e: Error =>
        Console.err.println(e.getMessage)
        ""
    }
  }

  val getToken: Regex = """\s*(-?\d*\.?\d+|[(*/+-])\s*(.*)\s*""".r

  def infixToPostfix(line :String, operations :Seq[String] = Seq()) :Seq[String] = line match {
    case "" => operations

    case getToken("(", rest) =>
      val index: Int = rest.iterator.scanLeft(1){
        case (stage, char) =>
          if (char == '(') stage + 1
          else if (char == ')') stage - 1
          else stage
      }.drop(1).indexOf(0)
      val (bracket, str) = rest.splitAt(index)
      infixToPostfix(bracket) ++ infixToPostfix(str.tail, operations)

    case getToken(token@("*" | "/"), rest) =>  //high priority operations
      infixToPostfix(rest, token +: operations)

    case getToken(token@("+" | "-"), rest) =>  //low priority operations
      operations.headOption.fold(infixToPostfix(rest, token +: operations)){
        case "-"|"+" => infixToPostfix(rest, token +: operations)
        case _ => operations.head +: infixToPostfix(rest, token +: operations.tail)
      }

    case getToken(number, rest) => number +: infixToPostfix(rest, operations)
    case _ => throw new Error(s"can't parse: $line")
  }

  def calculatePostfix(args: Seq[String]): Double = {
    val stack = new mutable.Stack[Double]()

    for (token <- args; if token.nonEmpty) token match {
      case "+" => stack.push(stack.pop() + stack.pop())
      case "*" => stack.push(stack.pop() * stack.pop())
      case "-" =>
        val tmp = stack.pop()
        stack.push(stack.pop() - tmp)
      case "/" =>
        val tmp = stack.pop()
        stack.push(stack.pop() / tmp)
      case x =>
        stack.push(x.toDouble)
    }
    stack.pop()
  }
}


