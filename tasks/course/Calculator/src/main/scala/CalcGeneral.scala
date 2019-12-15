import java.awt.{Color, Dimension, Font, Toolkit}
import scala.swing.{Action, Button, GridPanel, Label, MainFrame}

object CalcGeneral extends CalcContent
{
  val frame: MainFrame = new MainFrame{
    title = "Calculator"
    def calculatorAction(name: String): Action = new Action(name) {
      override def apply(): Unit = {
        try {
          pressButton(name)
          name match {
            case "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9" | "." => screen.text += name
            case "=" | "Pi" => screen.text = getCurrValue
            case _ => screen.text = ""
          }
        }
        catch {
          case _: IllegalArgumentException => Toolkit.getDefaultToolkit.beep()
        }
      }
    }

    val screenFont = new Font("Arial", 1, 50)
    val buttonFont = new Font("Arial", 0, 20)

    var screen: Label = new Label("") {
      opaque = true
      foreground = java.awt.Color.BLACK
      font = screenFont
    }
    val buttons: GridPanel = new GridPanel(4, 7) {
      contents += new Button() {
        background = new Color(93, 204, 255)
        foreground = new Color(35, 32, 32)
        font = buttonFont
        action = calculatorAction("C")
      }

      contents += new Button() {
        background = new Color(24, 110, 90)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = calculatorAction("+")
      }

    contents += new Button() {
      background = new Color(24, 110, 90)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("-")
    }

    contents += new Button() {
      background = new Color(24, 110, 90)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("*")
    }

    contents += new Button() {
      background = new Color(24, 110, 90)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("/")
    }

    contents += new Button() {
      background = new Color(24, 110, 90)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("x^2")
    }

    contents += new Button() {
      background = new Color(24, 110, 90)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("Mod")
    }

    contents += new Button() {
      background = new Color(18, 31, 217)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("7")
    }

    contents += new Button() {
      background = new Color(18, 31, 217)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("8")
    }

    contents += new Button() {
      background = new Color(18, 31, 217)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("9")
    }

    contents += new Button() {
      background = new Color(153, 255, 209)
      foreground = new Color(35, 32, 32)
      font = buttonFont
      action = calculatorAction("=")
    }

    contents += new Button() {
      background = new Color(24, 110, 90)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("sin")
    }

    contents += new Button() {
      background = new Color(24, 110, 90)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("x^3")
    }

    contents += new Button() {
      background = new Color(24, 110, 90)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("log")
    }

    contents += new Button() {
      background = new Color(18, 31, 217)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("4")
    }

    contents += new Button() {
      background = new Color(18, 31, 217)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("5")
    }

    contents += new Button() {
      background = new Color(18, 31, 217)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("6")
    }

    contents += new Button() {
      background = new Color(18, 31, 217)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction(".")
    }

    contents += new Button() {
      background = new Color(24, 110, 90)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("cos")
    }

    contents += new Button() {
      background = new Color(24, 110, 90)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("x^y")
    }

    contents += new Button() {
      background = new Color(24, 110, 90)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("1/x")
    }

    contents += new Button() {
      background = new Color(18, 31, 217)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("1")
    }

    contents += new Button() {
      background = new Color(18, 31, 217)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("2")
    }

    contents += new Button() {
      background = new Color(18, 31, 217)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("3")
    }

    contents += new Button() {
      background = new Color(18, 31, 217)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("0")
    }

    contents += new Button() {
      background = new Color(24, 110, 90)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("tan")
    }

    contents += new Button() {
      background = new Color(24, 110, 90)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("sqrt")
    }

    contents += new Button() {
      background = new Color(24, 110, 90)
      foreground = new Color(244, 244, 244)
      font = buttonFont
      action = calculatorAction("Pi")
    }
  }

  val mainGridPanel: GridPanel = new GridPanel(2, 1) {
    contents += screen
    contents += buttons
  }
  contents = mainGridPanel
  size = new Dimension(700, 450)
  centerOnScreen
}

def main(args: Array[String]): Unit = frame.visible = true
}