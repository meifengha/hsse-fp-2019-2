package calculator

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.text.Font

object CalculatorGUI extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title.value = "Calculator"

    width = 300
    height = 270
    centerOnScreen()
    scene = new Scene  {


      fill = Color.rgb(242,241,240)


      val text = new TextField


      text.prefHeight = 60
      text.prefWidth = 290

      text.layoutX = 5
      text.layoutY = 5

      text.setFont(Font.font("Serif", 22))

      val pane = new GridPane

      pane.setHgap(5)
      pane.setVgap(5)


      val button0 = new Button("0")
      val button1 = new Button("1")
      val button2 = new Button("2")
      val button3 = new Button("3")
      val button4 = new Button("4")
      val button5 = new Button("5")
      val button6 = new Button("6")
      val button7 = new Button("7")
      val button8 = new Button("8")
      val button9 = new Button("9")



      val divide: Button = new Button{text = "/";tooltip = "Divide"}
      val plus: Button = new Button{text = "+";tooltip = "Add"}
      val minus: Button = new Button{text = "-";tooltip = "Subtract"}
      val floatPoint  = new Button(".")
      val startGroup: Button = new Button{text = "(";tooltip = "Start Group"}
      val endGroup: Button = new Button{text = ")";tooltip = "End Group"}
      val equals: Button = new Button{text = "=";tooltip = "Equals for Linear Equation"}
      val X: Button = new Button{text = "x";tooltip = "Variable for Linear Equation"}
      val multiply: Button = new Button{text = "*";tooltip = "Multiply"}
      val clearDisplay: Button = new Button{text = "C"; tooltip = "Clear Screen"}
      val log: Button = new Button{text = "log";tooltip = "Logarithm"}
      val calculate: Button = new Button{text = "calc";tooltip = "Calculate Result"}



      val buttonWidth = 54

      button7.prefWidth = buttonWidth
      button8.prefWidth = buttonWidth
      button9.prefWidth = buttonWidth
      divide.prefWidth = buttonWidth
      multiply.prefWidth = buttonWidth


      button4.prefWidth = buttonWidth
      button5.prefWidth = buttonWidth
      button6.prefWidth = buttonWidth
      plus.prefWidth = buttonWidth
      minus.prefWidth = buttonWidth

      button3.prefWidth = buttonWidth
      button2.prefWidth = buttonWidth
      button1.prefWidth = buttonWidth
      startGroup.prefWidth = buttonWidth
      endGroup.prefWidth = buttonWidth

      button0.prefWidth = buttonWidth
      floatPoint.prefWidth = buttonWidth
      equals.prefWidth = buttonWidth
      X.prefWidth = buttonWidth
      log.prefWidth = buttonWidth


      clearDisplay.prefWidth = buttonWidth
      calculate.prefWidth = buttonWidth



      clearDisplay.onAction  = (_: ActionEvent) => {
        text.text = ""
      }

      button0.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "0"
      }

      button1.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "1"
      }

      button2.onAction  = (_: ActionEvent) => {
        text.text = text.text.apply + "2"
      }

      button3.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "3"
      }

      button4.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "4"
      }

      button5.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "5"
      }

      button6.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "6"
      }

      button7.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "7"
      }


      button8.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "8"
      }

      button9.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "9"
      }


      divide.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "/"
      }

      multiply.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "*"
      }

      plus.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "+"
      }

      minus.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "-"
      }

      equals.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "="
      }

      X.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "x"
      }

      log.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "log"
      }

      floatPoint.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "."
      }

      startGroup.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + "("
      }

      endGroup.onAction  = (_: ActionEvent) => {
        text.text = text.text.value + ")"
      }


      calculate.onAction  = (_: ActionEvent) => {

        val calculatedValue = Calculator.calculate(text.text.value)
        text.text = calculatedValue
      }

      pane.add(button7,1,0)
      pane.add(button8,2,0)
      pane.add(button9,3,0)
      pane.add(divide,4,0)
      pane.add(multiply,5,0)

      pane.add(button4,1,1)
      pane.add(button5,2,1)
      pane.add(button6,3,1)
      pane.add(plus,4,1)
      pane.add(minus,5,1)

      pane.add(button3,1,2)
      pane.add(button2,2,2)
      pane.add(button1,3,2)
      pane.add(startGroup,4,2)
      pane.add(endGroup,5,2)

      pane.add(button0,1,3)
      pane.add(floatPoint,2,3)
      pane.add(equals,3,3)
      pane.add(X ,4,3)
      pane.add(log,5,3)


      pane.add(clearDisplay,1,4)
      pane.add(calculate,2,4)

      pane.setLayoutY(80)

      content = List(text,pane)

    }

  }

}