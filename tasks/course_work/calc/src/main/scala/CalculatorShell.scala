package coursework.calculator.src.main.scala

import Main.{Color, Font}
import javax.swing.BorderFactory

import scala.swing.{Alignment, Button, Dimension, GridBagPanel, Insets, TextField}
import scala.swing.event.ButtonClicked

class CalculatorShell() {

  private val functionalCalculator: Calculator.type = Calculator
  val btnFont = new Font("Arial", 0, 17)
  val fieldFont = new Font("Arial", 0, 20)
  val btnColor = new Color(0, 191, 255)
  val btnSize = new Dimension(50, 45)
  val doubleBtnSize = new Dimension(50, 90)

  var inputField: TextField = new TextField(" ") {
    font = fieldFont
    background = btnColor
    opaque = true
    border = BorderFactory.createCompoundBorder(
      BorderFactory.createLoweredBevelBorder(),
      BorderFactory.createEmptyBorder(0, 5, 0, 5))
    editable = false
    horizontalAlignment = Alignment.Right
  }

  var outputField: TextField = new TextField(" ") {
    font = fieldFont
    background = btnColor
    opaque = true
    border = BorderFactory.createCompoundBorder(
      BorderFactory.createLoweredBevelBorder(),
      BorderFactory.createEmptyBorder(0, 5, 0, 5))
    editable = false
    horizontalAlignment = Alignment.Right
  }

  val btn1: Button = new Button("1") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }
  val btn2: Button = new Button("2") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }
  val btn3: Button = new Button("3") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }
  val btn4: Button = new Button("4") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }
  val btn5: Button = new Button("5") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }
  val btn6: Button = new Button("6") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }
  val btn7: Button = new Button("7") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }
  val btn8: Button = new Button("8") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }
  val btn9: Button = new Button("9") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }
  val btn0: Button = new Button("0") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }
  val btnPoint: Button = new Button(".") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }
  val btnCalculate: Button = new Button("=") {
    font = btnFont
    background = btnColor
    preferredSize = doubleBtnSize
  }
  val btnMinus: Button = new Button("-") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }
  val btnPlus: Button = new Button("+") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }

  val btnMultiply: Button = new Button("*") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }

  val btnDivide: Button = new Button("/") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }

  val btnOpenBracket: Button = new Button("(") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }

  val btnCloseBracket: Button = new Button(")") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }

  val btnClean: Button = new Button("C") {
    font = btnFont
    background = btnColor
    preferredSize = btnSize
  }

  def CalcPanel(): GridBagPanel = {
    val contents: GridBagPanel = new GridBagPanel() {
      var c = new Constraints()
      c.gridx = 0
      c.gridy = 0
      c.gridwidth = 4
      c.insets = new Insets(3, 3, 3, 3)
      c.fill = GridBagPanel.Fill.Both
      add(inputField, c)

      c.gridwidth = 4
      c.gridx = 0
      c.gridy = 1
      add(outputField, c)

      c.gridwidth = 1
      c.gridx = 0
      c.gridy = 2
      add(btnClean, c)

      c.gridx = 0
      c.gridy = 3
      add(btn7, c)

      c.gridx = 0
      c.gridy = 4
      add(btn4, c)

      c.gridx = 0
      c.gridy = 5
      add(btn1, c)

      c.gridx = 1
      c.gridy = 2
      add(btnOpenBracket, c)

      c.gridx = 2
      c.gridy = 2
      add(btnCloseBracket, c)

      c.gridx = 3
      c.gridy = 2
      add(btnDivide, c)

      c.gridx = 1
      c.gridy = 3
      add(btn8, c)

      c.gridx = 2
      c.gridy = 3
      add(btn9, c)

      c.gridx = 3
      c.gridy = 3
      add(btnMultiply, c)

      c.gridx = 1
      c.gridy = 4
      add(btn5, c)

      c.gridx = 2
      c.gridy = 4
      add(btn6, c)

      c.gridx = 3
      c.gridy = 4
      add(btnMinus, c)

      c.gridx = 1
      c.gridy = 5
      add(btn2, c)

      c.gridx = 2
      c.gridy = 5
      add(btn3, c)

      c.gridx = 3
      c.gridy = 5
      add(btnPlus, c)

      c.gridwidth = 2
      c.gridx = 0
      c.gridy = 6
      add(btn0, c)

      c.gridwidth = 1
      c.gridx = 2
      c.gridy = 6
      add(btnPoint, c)

      c.gridx = 3
      c.gridy = 6
      add(btnCalculate, c)

      listenTo(btn0)
      listenTo(btn1)
      listenTo(btn2)
      listenTo(btn3)
      listenTo(btn4)
      listenTo(btn5)
      listenTo(btn6)
      listenTo(btn7)
      listenTo(btn8)
      listenTo(btn9)
      listenTo(btnPlus)
      listenTo(btnMinus)
      listenTo(btnPoint)
      listenTo(btnCalculate)
      listenTo(btnClean)
      listenTo(btnMultiply)
      listenTo(btnDivide)
      listenTo(btnCloseBracket)
      listenTo(btnOpenBracket)

      reactions += {
        case ButtonClicked(`btn0`) => inputField.text += btn0.text
        case ButtonClicked(`btn1`) => inputField.text += btn1.text
        case ButtonClicked(`btn2`) => inputField.text += btn2.text
        case ButtonClicked(`btn3`) => inputField.text += btn3.text
        case ButtonClicked(`btn4`) => inputField.text += btn4.text
        case ButtonClicked(`btn5`) => inputField.text += btn5.text
        case ButtonClicked(`btn6`) => inputField.text += btn6.text
        case ButtonClicked(`btn7`) => inputField.text += btn7.text
        case ButtonClicked(`btn8`) => inputField.text += btn8.text
        case ButtonClicked(`btn9`) => inputField.text += btn9.text
        case ButtonClicked(`btnPlus`) => inputField.text += btnPlus.text
        case ButtonClicked(`btnMinus`) => inputField.text += btnMinus.text
        case ButtonClicked(`btnPoint`) => inputField.text += btnPoint.text
        case ButtonClicked(`btnCloseBracket`) => inputField.text += btnCloseBracket.text
        case ButtonClicked(`btnOpenBracket`) => inputField.text += btnOpenBracket.text
        case ButtonClicked(`btnMultiply`) => inputField.text += btnMultiply.text
        case ButtonClicked(`btnDivide`) => inputField.text += btnDivide.text
        case ButtonClicked(`btnClean`) => inputField.text = ""
        case ButtonClicked(`btnCalculate`) => outputField.text = {
          try {
            s"%1.5f".format(functionalCalculator.calculate(functionalCalculator.parse(inputField.text)))
          } catch {
            case _: Exception => "Error"
          }

        }
      }
    }
    contents
  }

}
