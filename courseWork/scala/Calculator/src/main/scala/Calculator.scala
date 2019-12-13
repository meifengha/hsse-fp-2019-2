import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}

object Calculator {

  def calculate(operands: ListBuffer[String], operators: ListBuffer[Char]): String = {
    for ((x, i) <- operators.zipWithIndex) {
      if (x.equals('*') || x.equals('/')) {
        operands.insert(i, count(operands.apply(i), operands.apply(i + 1), x))
        operands.remove(i + 1)
        operators.remove(i)
      }
    }
    for ((x, i) <- operators.zipWithIndex) {
      operands.insert(i, count(operands.apply(i), operands.apply(i + 1), x))
      operands.remove(i + 1)
      operators.remove(i)
    }

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
