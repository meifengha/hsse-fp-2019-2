import scala.swing._

import calculator.Calculator

class Main extends Calculator {

  def runCalculator(x: String): String = {
    try {
      run(x).calculate.toString
    } catch {
      case e: Exception => "error"
    }
  }
}

object Lay extends Main {

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

      layout += new GridPanel(5, 4) {
        contents += new Button(Action("C") {
          label.text = " "
        })

        contents += new Button(Action("ERASE") {
          if (flag) {
            label.text = "  "
            flag = false
          }
          label.text = label.text.dropRight(2)
        })

        contents += new Button(Action(" = ") {
          var d = label.text
          d = d.replaceAll("""^\s+(?m)""", "")
          val x = runCalculator(d)
          label.text = "Результат: " + x
          flag = true
        })

        contents += new Button(Action(" / ") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + " / "
        })

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

        contents += new Button(Action(" * ") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + " * "
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


        contents += new Button(Action(" - ") {
          if (flag) {
            label.text = " "
            flag = false
          }
          label.text = label.text + " - "
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

        contents += new Button(Action("EXIT") {
          sys.exit(0)
        })
      } -> Center
   }

    size = new Dimension(400, 550)
    centerOnScreen
  }

  def main(args: Array[String]): Unit = frame.visible = true
}
