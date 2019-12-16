import scala.util.{Try, Success, Failure}

def calculator(left: String, op: String, right: String): Unit = {

  def parse(value: String) = Try(value.toDouble)

  (parse(left), parse(right)) match {
    case (Success(leftDouble), Success(rightDouble)) =>
      op match {
        case "/" => println(leftDouble / rightDouble)
        case "*" => println(leftDouble * rightDouble)
        case "+" => println(leftDouble + rightDouble)
        case "-" => println(leftDouble - rightDouble)
        case invalid: String => println(s"Invalid operator $invalid.")
      }
    case (Failure(_), _) => println(s"Invalid operand $left.")
    case (_, Failure(_)) => println(s"Invalid operand $right.")
    case (Failure(_), Failure(_)) => println(s"Invalid operands $left and $right.")
  }
}
