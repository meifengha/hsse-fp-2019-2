package Calc
import scala.util.parsing.combinator._

sealed abstract class Expression
case class X(name: String) extends Expression
case class Const(value : Double) extends Expression
case class AddOp(left : Expression, right : Expression) extends Expression
case class SubOp(left : Expression, right : Expression) extends Expression
case class MulOp(left : Expression, right : Expression) extends Expression
case class DivOp(left : Expression, right : Expression) extends Expression
case class Neg(expr : Expression) extends Expression

// Парсим строку в дерево
object ExprParser extends RegexParsers {
  def variable  : Parser[Expression] = """[a-z]""".r ^^ { x => X(x) }
  def number    : Parser[Expression] = """\d+(\.\d*)?""".r ^^ { x => Const(x.toDouble) }
  def factor    : Parser[Expression] = (number | variable) | "(" ~> expr <~ ")"
  def term      : Parser[Expression] = factor ~ rep( "*" ~ factor | "/" ~ factor) ^^ {
    case number ~ list => list.foldLeft(number) {
      case (x, "*" ~ y) => MulOp(x,y)
      case (x, "/" ~ y) => DivOp(x,y)
    }
  }
  def expr  : Parser[Expression] = term ~ rep("+" ~ term | "-" ~ term) ^^ {
    case number ~ list => list.foldLeft(number) {
      case (x, "+" ~ y) => AddOp(x,y)
      case (x, "-" ~ y) => SubOp(x,y)
    }
  }

  def apply(input: String): Expression = parseAll(expr, input) match {
    case Success(result, _) => result
    case failure : NoSuccess => scala.sys.error(failure.msg)
  }
}

// Вычисляем выражение
object ExprEval {
  def eval(expression : Expression, valMap: Map[String, Double]) : Double = expression match {
    case X(name) => valMap(name).toDouble
    case Const(cst) => cst
    case AddOp(left, right) => eval(left, valMap) + eval(right, valMap)
    case SubOp(left, right) => eval(left, valMap) - eval(right, valMap)
    case MulOp(left, right) => eval(left, valMap) * eval(right, valMap)
    case DivOp(left, right) => eval(left, valMap) / eval(right, valMap)
    case Neg(expr) => - eval(expr, valMap)
  }
}