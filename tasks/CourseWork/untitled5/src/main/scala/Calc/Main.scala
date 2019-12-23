
package Calc {

  import scala.io.StdIn

  object Main {
    def main(args: Array[String]): Unit = {
      var a = Map[String, Double]()
      val str = StdIn.readLine()
      val expr = ExprParser.apply(str)
      val res = ExprEval.eval(expr, a)
      println( str + " = "+ res)
    }
  }

}