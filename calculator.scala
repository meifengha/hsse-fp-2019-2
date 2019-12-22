import scala.collection.immutable.StringOps._
import scala.util.{Try, Success, Failure}

def calculator(left: String, op: String, right: String): Unit = {

  def parse(value: String) = Try(value.toDouble)
  
  (parse(left), parse(right)) match {
    case (Success(leftDouble), Success(rightDouble)) => {
      op match {
        case "/" => println(leftDouble / rightDouble)
        case "*" => println(leftDouble * rightDouble)
        case "+" => println(leftDouble + rightDouble)
        case "-" => println(leftDouble - rightDouble)
        case invalid: String => println(s"Invalid operator $invalid.")
      }
    }
    case (Failure(e), _) => println(s"Could not parse $left.")
    case(_, Failure(e)) => println(s"Could not parse $right.")
    case(Failure(e1), Failure(e2)) => println(s"Could not parse $left and $right.")
  }
  
}
