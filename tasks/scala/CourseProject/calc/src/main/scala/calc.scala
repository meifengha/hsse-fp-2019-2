package calc

import scala.swing._

import scala.util.parsing.combinator.JavaTokenParsers

abstract class Expr
case class Val(value : Double) extends Expr
case class UnOp(operator : String, operand : Expr) extends Expr
case class BiOp(operator : String, lhs : Expr, rhs : Expr) extends Expr

class Calculator {

  def parse(s: String): Expr = {

    object ExpressionParser extends JavaTokenParsers {
      def expr: Parser[Expr] =
        (term ~ "+" ~ term) ^^ { case lhs ~ plus ~ rhs => BiOp("+", lhs, rhs) } |
          (term ~ "-" ~ term) ^^ { case lhs ~ minus ~ rhs => BiOp("-", lhs, rhs) } |
          term

      def term: Parser[Expr] =
        (factor ~ "*" ~ factor) ^^ { case lhs ~ times ~ rhs => BiOp("*", lhs, rhs) } |
          (factor ~ "/" ~ factor) ^^ { case lhs ~ div ~ rhs => BiOp("/", lhs, rhs) } |
          factor

      def factor: Parser[Expr] =
        "(" ~> expr <~ ")" |
          floatingPointNumber ^^ { x => Val(x.toDouble) }

      def parse(s: String) = parseAll(expr, s)
    }

    ExpressionParser.parse(s).get
  }

  def simplify(e: Expr): Expr = {

    def combine(e: Expr) = e match {
      case UnOp("-", UnOp("-", x)) => x
      case UnOp("+", x) => x
      case BiOp("*", x, Val(1)) => x
      case BiOp("*", Val(1), x) => x
      case BiOp("*", x, Val(0)) => Val(0)
      case BiOp("*", Val(0), x) => Val(0)
      case BiOp("/", x, Val(1)) => x
      case BiOp("/", x1, x2) if x1 == x2 => Val(1)
      case BiOp("+", x, Val(0)) => x
      case BiOp("+", Val(0), x) => x
      case _ => e
    }

    val subs = e match {
      case BiOp(op, lhs, rhs) => BiOp(op, simplify(lhs), simplify(rhs))
      case UnOp(op, operand) => UnOp(op, simplify(operand))
      case _ => e
    }

    combine(subs)
  }

  def evaluate(e: Expr): Double = {
    e match {
      case Val(x) => x
      case UnOp("-", x) => -evaluate(x)
      case BiOp("+", l, r) => (evaluate(l) + evaluate(r))
      case BiOp("-", l, r) => (evaluate(l) - evaluate(r))
      case BiOp("*", l, r) => (evaluate(l) * evaluate(r))
      case BiOp("/", l, r) => (evaluate(l) / evaluate(r))
    }
  }
}

class Main extends Calculator {

  def runCalculator(x: String): String = {
    try {
      evaluate(simplify(parse(x))).toString
    } catch {
      case e: Exception => "error"
    }
  }
}

object Lay extends Main {

  val frame = new MainFrame {
    title = "Calculator"
    import BorderPanel.Position._
    contents = new BorderPanel {
      var label = new Label("0")
      label.opaque = true
      label.foreground = java.awt.Color.BLUE
      label.font = new Font("Trebuchet MS", 0, 26);
      var flag = false

      layout += new GridPanel(3, 1) {
        contents += new Label("")
        contents += label
        contents += new Label("")
      } -> North

      layout += new GridPanel(5, 4) {

        contents += new Button(Action("EXIT") {
          sys.exit(0)
        }) {
          font = new Font("Trebuchet MS", 0, 26);
          foreground = java.awt.Color.RED

        }

        contents += new Button(Action("C") {
          label.text = "0"
        }) {
          font = new Font("Trebuchet MS", 0, 36);
        }

        contents += new Button(Action("<--") {
          if (label.text.charAt(label.text.size - 1) == ' ') {
            label.text = label.text.dropRight(3)
          } else {
            label.text = label.text.dropRight(1)
          }

          if (label.text == "")
          {
            label.text = "0"
            flag = false
          }
        }) {
          font = new Font("Trebuchet MS", 0, 36);
        }
        contents += new Button(Action(" / ") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + " / "
        }) {
          font = new Font("Trebuchet MS", 0, 46);
        }

        contents += new Button(Action("7") {
          if (flag) {
            label.text = " "
            flag = false
          }
          if (label.text == "0") {
            label.text = "7"
          } else {
            label.text = label.text + "7"
          }
        }) {
          font = new Font("Trebuchet MS", 0, 36);
        }

        contents += new Button(Action("8") {
          if (flag) {
            label.text = " "
            flag = false
          }
          if (label.text == "0") {
            label.text = "8"
          } else {
            label.text = label.text + "8"
          }
        }) {
          font = new Font("Trebuchet MS", 0, 36);
        }

        contents += new Button(Action("9") {
          if (flag) {
            label.text = " "
            flag = false
          }
          if (label.text == "0") {
            label.text = "9"
          } else {
            label.text = label.text + "9"
          }
        }) {
          font = new Font("Trebuchet MS", 0, 36);
        }

        contents += new Button(Action(" * ") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + " * "
        }) {
          font = new Font("Trebuchet MS", 0, 46);
        }

        contents += new Button(Action("4") {
          if (flag) {
            label.text = " "
            flag = false
          }
          if (label.text == "0") {
            label.text = "4"
          } else {
            label.text = label.text + "4"
          }
        }) {
          font = new Font("Trebuchet MS", 0, 36);
        }

        contents += new Button(Action("5") {
          if (flag) {
            label.text = " "
            flag = false
          }
          if (label.text == "0") {
            label.text = "5"
          } else {
            label.text = label.text + "5"
          }
        }) {
          font = new Font("Trebuchet MS", 0, 36);
        }

        contents += new Button(Action("6") {
          if (flag) {
            label.text = " "
            flag = false
          }
          if (label.text == "0") {
            label.text = "6"
          } else {
            label.text = label.text + "6"
          }
        }) {
          font = new Font("Trebuchet MS", 0, 36);
        }

        contents += new Button(Action(" - ") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + " - "
        }) {
          font = new Font("Trebuchet MS", 0, 46);
        }

        contents += new Button(Action("1") {
          if (flag) {
            label.text = " "
            flag = false
          }
          if (label.text == "0") {
            label.text = "1"
          } else {
            label.text = label.text + "1"
          }
        }) {
          font = new Font("Trebuchet MS", 0, 36);
        }

        contents += new Button(Action("2") {
          if (flag) {
            label.text = " "
            flag = false
          }
          if (label.text == "0") {
            label.text = "2"
          } else {
            label.text = label.text + "2"
          }
        }) {
          font = new Font("Trebuchet MS", 0, 36);
        }

        contents += new Button(Action("3") {
          if (flag) {
            label.text = " "
            flag = false
          }
          if (label.text == "0") {
            label.text = "3"
          } else {
            label.text = label.text + "3"
          }
        }) {
          font = new Font("Trebuchet MS", 0, 36);
        }

        contents += new Button(Action(" + ") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + " + "
        }) {
          font = new Font("Trebuchet MS", 0, 46);
        }

        contents += new Button(Action("(") {
          if (flag) {
            label.text = " "
            flag = false
          }
          if (label.text == "0") {
            label.text = "("
          } else {
            label.text = label.text + "("
          }
        }) {
          font = new Font("Trebuchet MS", 0, 36);
        }

        contents += new Button(Action("0") {
          if (flag) {
            label.text = " "
            flag = false
          }
          if (label.text != "0") {
            label.text = label.text + "0"
          }
        }) {
          font = new Font("Trebuchet MS", 0, 36);
        }

        contents += new Button(Action(")") {
          if (flag) {
            label.text = " "
            flag = false
          }
          if (label.text == "0") {
            label.text = ")"
          } else {
            label.text = label.text + ")"
          }
        }) {
          font = new Font("Trebuchet MS", 0, 36);
        }

        contents += new Button(Action(" = ") {
          var d = label.text
          d = d.replaceAll("""^\s+(?m)""", "")
          val x = runCalculator(d)
          label.text = "Result = " + x
          flag = true
        }) {
          font = new Font("Trebuchet MS", 0, 46);
        }

      } -> Center
    }

    size = new Dimension(400, 550)
    centerOnScreen
  }

  def main(args: Array[String]): Unit = frame.visible = true
}