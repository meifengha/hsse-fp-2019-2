package calculator

class Calculator {

  type Unparsed = List[Token]
  type Parsed = (Unparsed, Expression)

  def getAgg(op: String): (Expression, Expression) => Expression = op match {

    case "+" => (x: Expression, y: Expression) => Add(x,y)
    case "-" => (x: Expression, y: Expression) => Subtract(x,y)
    case "*" => (x: Expression, y: Expression) => Multiply(x,y)
    case "/" => (x: Expression, y: Expression) => Divide(x,y)
  }

  def run(input: String): Expression = {
    val tokens = Tokenizer.tokenize(input.split(" ").toList)
    AS.parse(tokens.tail, tokens.head.value)(getAgg(tokens.head.nextOp))._2
  }

  case class Token(nextOp: String, value: Expression)

  object Tokenizer {
    def tokenize(input: List[String]): Unparsed = input match {
      case Nil => Nil
      case n :: rest => rest match {
        case Nil => List(Token("", Number(n.toInt)))
        case "+" :: tail => new Token("+", Number(n.toInt)) :: tokenize(tail)
        case "-" :: tail => new Token("-", Number(n.toInt)) :: tokenize(tail)
        case "*" :: tail => new Token("*", Number(n.toInt)) :: tokenize(tail)
        case "/" :: tail => new Token("/", Number(n.toInt)) :: tokenize(tail)
        case _ => List(Token("", Number(n.toInt)))
      }
    }
  }

  class Expression {
    def calculate: Int = this match {
      case Number(n) => n
      case Multiply(l, r) => l.calculate * r.calculate
      case Divide(l, r) => l.calculate / r.calculate
      case Subtract(l, r) =>  l.calculate - r.calculate
      case Add(l, r) => l.calculate + r.calculate
    }

    def print: String = this match {
      case Number(n) => n.toString
      case Multiply(l, r) => l.print + " * " + r.print
      case Divide(l, r) => l.print + " / " + r.print
      case Subtract(l, r) =>  l.print + " - " + r.print
      case Add(l, r) => l.print + " + " + r.print
    }
  }

  case class Number(n: Int) extends Expression
  case class Add(l: Expression, r: Expression) extends Expression
  case class Subtract(l: Expression, r: Expression) extends Expression
  case class Multiply(l: Expression, r: Expression) extends Expression
  case class Divide(l: Expression, r: Expression) extends Expression

  object AS extends Expression {
    def parse(unparsed: Unparsed, acc: Expression)(aggregator: (Expression, Expression) => Expression): Parsed = unparsed match {
      case Nil => (Nil, acc)
      case right :: rest => right.nextOp match {
        case "+" => AS.parse(rest, aggregator(acc, right.value))(getAgg("+"))

        case "-" => AS.parse(rest, aggregator(acc, right.value))(getAgg("-"))

        case "*" =>
          val (next, accedMDExpr) = MD.parse(rest, right.value)(getAgg("*"))
          if (next.isEmpty) (Nil, aggregator(acc, accedMDExpr))
          else AS.parse(next.tail, aggregator(acc, accedMDExpr))(getAgg(next.head.nextOp))

        case "/" =>
          val (next, accedDivExp) = MD.parse(rest, right.value)(getAgg("/"))
          if (next.isEmpty) (Nil, aggregator(acc, accedDivExp))
          else AS.parse(next.tail, aggregator(acc, accedDivExp))(getAgg(next.head.nextOp))
        case "" => (Nil, aggregator(acc, right.value))
      }
    }
  }

  object MD extends Expression {
    def parse(unparsed: Unparsed, acc: Expression)(aggregator: (Expression, Expression) => Expression): Parsed = unparsed match {

      case Nil => (Nil, acc)
      case right :: rest => right.nextOp match {
        case "*" => MD.parse(rest, aggregator(acc, right.value))(getAgg("*"))
        case "/" => MD.parse(rest, aggregator(acc, right.value))(getAgg("/"))
        case "" => (Nil, aggregator(acc, right.value))
        case _ => (unparsed, aggregator(acc, right.value))
      }
    }
  }
}

