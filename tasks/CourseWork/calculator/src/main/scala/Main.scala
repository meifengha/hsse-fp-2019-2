import scala.swing._

object Main extends  Calculator {
  val frame: MainFrame = new MainFrame{
    title = "Calculator"

    val resultFont = new Font("Arial", 1, 50)
    val buttonFont = new Font("Arial", 0, 20)

    var resultLabel: Label = new Label("") {
      opaque = true
      foreground = java.awt.Color.BLACK
      font = resultFont
    }

    val grdPanel: GridPanel = new GridPanel(5, 4) {
      contents += new Button() {
        background = new Color(89, 89, 89)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("CE"){ resultLabel.text = "" }
      }

      contents += new Button() {
        background = new Color(89, 89, 89)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("<---") {
          if (resultLabel.text.nonEmpty) resultLabel.text = resultLabel.text.dropRight(1)
        }
      }

      contents += new Button() {
        background = new Color(89, 89, 89)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("("){ resultLabel.text += "(" }
      }

      contents += new Button() {
        background = new Color(89, 89, 89)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action(")"){ resultLabel.text += ")" }
      }

      contents += new Button() {
        background = new Color(35, 32, 32)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("7"){ resultLabel.text += "7" }
      }

      contents += new Button() {
        background = new Color(35, 32, 32)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("8"){ resultLabel.text += "8" }
      }

      contents += new Button() {
        background = new Color(35, 32, 32)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("9"){ resultLabel.text += "9" }
      }

      contents += new Button() {
        background = new Color(255, 115, 21)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("/"){ resultLabel.text += " / " }
      }

      contents += new Button() {
        background = new Color(35, 32, 32)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("4"){ resultLabel.text += "4" }
      }

      contents += new Button() {
        background = new Color(35, 32, 32)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("5"){ resultLabel.text += "5" }
      }

      contents += new Button() {
        background = new Color(35, 32, 32)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("6"){ resultLabel.text += "6" }
      }

      contents += new Button() {
        background = new Color(255, 115, 21)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("*"){ resultLabel.text += " * " }
      }

      contents += new Button() {
        background = new Color(35, 32, 32)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("1"){ resultLabel.text += "1" }
      }

      contents += new Button() {
        background = new Color(35, 32, 32)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("2"){ resultLabel.text += "2" }
      }

      contents += new Button() {
        background = new Color(35, 32, 32)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("3"){ resultLabel.text += "3" }
      }

      contents += new Button() {
        background = new Color(255, 115, 21)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("-"){ resultLabel.text += " - " }
      }

      contents += new Button() {
        background = new Color(89, 89, 89)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("."){ resultLabel.text += "." }
      }

      contents += new Button() {
        background = new Color(35, 32, 32)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("0"){ resultLabel.text += "0" }
      }

      contents += new Button() {
        background = new Color(89, 89, 89)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("="){
          resultLabel.text = runCalculate(resultLabel.text)
        }
      }

      contents += new Button("+") {
        background = new Color(255, 115, 21)
        foreground = new Color(244, 244, 244)
        font = buttonFont
        action = Action("+"){ resultLabel.text += " + " }
      }

    }

    val mainGridPanel: GridPanel = new GridPanel(2, 1) {
      contents += resultLabel
      contents += grdPanel
    }

    contents = mainGridPanel

    size = new Dimension(600, 800)
    centerOnScreen
  }

  def main(args: Array[String]): Unit = frame.visible = true
}
