package Calc

object Main {
  def main(args: Array[String]) {
    val expressionList = List(
      "9 + 4",
      "12 - 4",
      "54 * 9",
      "9 / 4"
    );

    for (expression <- expressionList) {
      println(expression + " = " + calculate(expression));
    }
  }

  def calculate(expression: String): Double = {
    def evaluate(expression: List[String]): Double = expression match {
      case l :: "+" :: r :: rest => evaluate((l.toDouble + r.toDouble).toString :: rest)
      case l :: "-" :: r :: rest => evaluate((l.toDouble - r.toDouble).toString :: rest)
      case l :: "*" :: r :: rest => evaluate((l.toDouble * r.toDouble).toString :: rest)
      case l :: "/" :: r :: rest => evaluate((l.toDouble / r.toDouble).toString :: rest)
      case value :: Nil => value.toDouble
    }

    evaluate(expression.split("\\s").toList)
  }
}
