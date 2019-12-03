package infixutils

trait InfixUtils {

  //Given an expression in infix notation, converts it to reverse polish notation

  def convertFromInfixToReversePolish(input: List[String]): List[String] = {

    def helper(inputTokenStr: List[String], output: List[String], stack: List[String]): List[String] = {
      inputTokenStr match {
        case x if x.isEmpty =>
          stack.reverse ::: output

        case x :: xs => x match {
          case inputToken if InfixUtils.isLeftBracket(inputToken) =>
            // Left token gets added to the stack
            helper(xs, output, inputToken :: stack)

          case inputToken if InfixUtils.isRightBracket(inputToken) =>
            val operators: List[String] = stack.takeWhile(op => !InfixUtils.isLeftBracket(op))

            val newStack = stack.drop(operators.size).dropWhile(op => InfixUtils.isLeftBracket(op))

            helper(xs, operators ::: output, newStack)

          case inputToken if InfixUtils.isOperator(inputToken) =>
            val operators: List[String] = InfixUtils.shouldPopToken(inputToken, stack)
            val newStack = stack.drop(operators.size)

            helper(xs, operators ::: output, inputToken :: newStack)

          case inputToken if InfixUtils.isOperand(inputToken) =>
            helper(xs, inputToken :: output, stack)
        }
      }
    }

    val output = helper(input, List(), List())
    output.reverse
  }

}

object InfixUtils {
  def shouldPopToken(op1: String, stack: List[String]): List[String] = {
    if (stack.isEmpty) List.empty else {
      stack.takeWhile(head => operandToPrecedenceMap(head) > operandToPrecedenceMap(op1))
    }
  }

  def isRightBracket(operator: String): Boolean = operator == ")"

  def isLeftBracket(operator: String): Boolean = operator == "("

  def isOperand(inputToken: String): Boolean = !isOperator(inputToken)

  def isOperator(inputToken: String): Boolean = operandToPrecedenceMap.contains(inputToken)

  val operandToPrecedenceMap: Map[String, Int] = Map(
    "+" -> 0,
    "-" -> 0,
    "*" -> 1,
    "/" -> 1,
    "(" -> -1,
    ")" -> -1
  )
}
