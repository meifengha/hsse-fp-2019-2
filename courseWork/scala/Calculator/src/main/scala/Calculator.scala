import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}

object Calculator {

  def calculate(operands: ListBuffer[String], operators: ListBuffer[Char]): String = {
    var i = 0
    operators.foreach(x => {
      if (x.equals('*') || x.equals('/')) {
        operands.update(i, count(operands.apply(i), operands.apply(i + 1), x))
        operands.remove(i + 1)
        operators.remove(i)
        i -= 1
      }
      i += 1
    })

    i = 0
    operators.foreach(x => {
      if (x.equals('+') || x.equals('-')) {
        operands.update(i, count(operands.apply(i), operands.apply(i + 1), x))
        operands.remove(i + 1)
        operators.remove(i)
        i -= 1
      }
      i += 1
    })

    operands.head
  }

  def count(first: String, second: String, op: Char): String = {
    def parse(value: String): Try[Double] = Try(value.toDouble)

    (parse(first), parse(second)) match {
      case (Success(firstDouble), Success(secondDouble)) => {
        op match {
          case '+' => (firstDouble + secondDouble).toString
          case '-' => (firstDouble - secondDouble).toString
          case '*' => (firstDouble * secondDouble).toString
          case '/' => (firstDouble / secondDouble).toString
        }
      }
      case (Failure(err), _) => err.getMessage
      case (_, Failure(err)) => err.getMessage
    }
  }
}
