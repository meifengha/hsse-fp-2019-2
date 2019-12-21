package coursework.calculator.src.main.scala

import util.parsing.combinator.JavaTokenParsers

abstract class Expression
case class Value(value: Double) extends Expression
case class UnaryOperation(operator: String, operand: Expression) extends Expression
case class BinaryOperation(operator: String, firstArg: Expression, secondArg: Expression) extends Expression

object Calculator {

  // синтаксический анализатор выражения
  def parse(s: String): Expression = {

    object ExpressionParser extends JavaTokenParsers {
      def expr: Parser[Expression] =
        (term ~ "+" ~ term) ^^ { case firstArg ~ _ ~ secondArg => BinaryOperation("+", firstArg, secondArg) } |
          (term ~ "-" ~ term) ^^ { case firstArg ~ _ ~ secondArg => BinaryOperation("-", firstArg, secondArg) } |
          term

      def term: Parser[Expression] =
        (factor ~ "*" ~ factor) ^^ { case firstArg ~ _ ~ secondArg => BinaryOperation("*", firstArg, secondArg) } |
          (factor ~ "/" ~ factor) ^^ { case firstArg ~ _ ~ secondArg => BinaryOperation("/", firstArg, secondArg) } |
          factor

      def factor: Parser[Expression] =
        "(" ~> expr <~ ")" |
          floatingPointNumber ^^ { x => Value(x.toDouble) }

      def parse(s: String): ExpressionParser.ParseResult[Expression] = parseAll(expr, s)
    }

    ExpressionParser.parse(s).get
  }

  def simplify(e: Expression): Expression = {

    // упрощение выражения с помощью очевидных арифметических случае случаев
    def combine(e: Expression) = e match {
      case UnaryOperation("-", UnaryOperation("-", x)) => x
      case UnaryOperation("+", x) => x
      case BinaryOperation("*", x, Value(1)) => x
      case BinaryOperation("*", Value(1), x) => x
      case BinaryOperation("*", _, Value(0)) => Value(0)
      case BinaryOperation("*", Value(0), _) => Value(0)
      case BinaryOperation("/", x, Value(1)) => x
      case BinaryOperation("/", x1, x2) if x1 == x2 => Value(1)
      case BinaryOperation("+", x, Value(0)) => x
      case BinaryOperation("+", Value(0), x) => x
      case _ => e
    }

    // упрощение подвыражений при помощи модели абстрактного синтаксического дерева
    val subs = e match {
      case BinaryOperation(op, firstArg, secondArg) => BinaryOperation(op, simplify(firstArg), simplify(secondArg))
      case UnaryOperation(op, operand) => UnaryOperation(op, simplify(operand))
      case _ => e
    }

    combine(subs)
  }

  def calculate(e: Expression): Double = {
    e match {
      case Value(x) => x
      case UnaryOperation("-", x) => -calculate(x)
      case BinaryOperation("+", l, r) => calculate(l) + calculate(r)
      case BinaryOperation("-", l, r) => calculate(l) - calculate(r)
      case BinaryOperation("*", l, r) => calculate(l) * calculate(r)
      case BinaryOperation("/", l, r) => calculate(l) / calculate(r)
    }
  }
}
