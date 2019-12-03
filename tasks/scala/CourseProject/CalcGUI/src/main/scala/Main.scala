import infixutils.InfixUtils
import scala.util.control.NonFatal
import scala.swing._

class Main extends InfixUtils with ReversePolishLogic {

  def runCalculator(x: String): String = {
    try {
      val userInput = x

      // Check there aren't any unexpected characters
      inputSanityCheck(userInput) match {
        case Left(desc) =>
          "Incorrect input"

        case Right(inputLst) =>
          // Convert the infix into reverse polish, and then evaluate
          val reversePolishNotationArray = convertFromInfixToReversePolish(inputLst)
          val expressionResult = evaluateReversePolishInput(reversePolishNotationArray)

          //console log
          println(s"Result = $expressionResult")
          " " + expressionResult
      }
    } catch {
      case NonFatal(e) =>
        "error"
    }
  }

  def inputSanityCheck(userInputStr: String): Either[String, List[String]] = {
    try {
      val inputLst = userInputStr.split(" ").toList

      val validInput = inputLst.forall(_.forall(char => char.isDigit || InfixUtils.operandToPrecedenceMap.contains(char.toString)))

      if (!validInput) {
        Left("bad characters")

      } else {
        Right(inputLst)
      }

    } catch {
      case NonFatal(e) =>
        println(e)
        Left("Separate each operand/operator with a space")
    }
  }

}


object Lay extends Main {
  val main = new Main

  val frame = new MainFrame {
    title = "Калькулятор"

    import BorderPanel.Position._

    contents = new BorderPanel {

      var label = new Label(" ")
      label.opaque = true
      //label.foreground = java.awt.Color.getHSBColor(240,100,55)
      label.foreground = java.awt.Color.BLUE
      var flag = false
      layout += new GridPanel(3, 1) {
        contents += new Label("")
        contents += label
        contents += new Label("")
      } -> North

      layout += new GridPanel(4, 5) {
        contents += new Button(Action("7") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + "7"
        })
        contents += new Button(Action("8") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + "8"
        })
        contents += new Button(Action("9") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + "9"
        })
        contents += new Button(Action("<——") {
          if (flag) {
            label.text = "  "
            flag = false
          }
          label.text = label.text.dropRight(1)
        })
        contents += new Button(Action("C") {

          label.text = " "
        })
        contents += new Button(Action("4") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + "4"
        })
        contents += new Button(Action("5") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + "5"
        })
        contents += new Button(Action("6") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + "6"
        })
        contents += new Button(Action(" / ") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + " / "
        })
        contents += new Button(Action(" * ") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + " * "
        })
        contents += new Button(Action("1") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + "1"
        })
        contents += new Button(Action("2") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + "2"
        })
        contents += new Button(Action("3") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + "3"
        })
        contents += new Button(Action(" + ") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + " + "
        })
        contents += new Button(Action(" - ") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + " -"
        })
        contents += new Button(Action(" ( ") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + "( "
        })
        contents += new Button(Action("0") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + "0"
        })
        contents += new Button(Action(" ) ") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + " )"
        })
        contents += new Button(Action(" = ") {
          var d = label.text
          d = d.replaceAll("""^\s+(?m)""", "")
          val x = main.runCalculator(d)
          label.text = "Результат: " + x
          flag = true

        })
        contents += new Button(Action("EXIT") {
          sys.exit(0)
        })
      } -> Center
    }
    size = new Dimension(500, 400)
    centerOnScreen
  }

  def main(args: Array[String]): Unit = frame.visible = true
}