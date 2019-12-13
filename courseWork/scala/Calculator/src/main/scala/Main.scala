import javafx.event
import scalafx.application
import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.layout.{BorderPane, GridPane}

import scala.collection.mutable.ListBuffer

object Main extends JFXApp {
  stage = new application.JFXApp.PrimaryStage {
    title = "Calculator"
    scene = new Scene(290, 430) {

      val textField = new TextField()
      textField.prefWidth = 300
      textField.prefHeight = 50

      val buttonDel = new Button("DEL")
      buttonDel.prefWidth = 60
      buttonDel.prefHeight = 60
      buttonDel.setOnAction((_: event.ActionEvent) => {
        if (!textField.getText.isEmpty)
          textField.setText(textField.getText(0, textField.getText.length - 1))
      })
      val buttonCE = new Button("CE")
      buttonCE.prefWidth = 60
      buttonCE.prefHeight = 60
      buttonCE.setOnAction((_: event.ActionEvent) => textField.clear())
      val button0 = new Button("0")
      button0.prefWidth = 60
      button0.prefHeight = 60
      button0.setOnAction((_: event.ActionEvent) => textField.setText(textField.getText() + "0"))
      val button1 = new Button("1")
      button1.prefWidth = 60
      button1.prefHeight = 60
      button1.setOnAction((_: event.ActionEvent) => textField.setText(textField.getText() + "1"))
      val button2 = new Button("2")
      button2.prefWidth = 60
      button2.prefHeight = 60
      button2.setOnAction((_: event.ActionEvent) => textField.setText(textField.getText() + "2"))
      val button3 = new Button("3")
      button3.prefWidth = 60
      button3.prefHeight = 60
      button3.setOnAction((_: event.ActionEvent) => textField.setText(textField.getText() + "3"))
      val button4 = new Button("4")
      button4.prefWidth = 60
      button4.prefHeight = 60
      button4.setOnAction((_: event.ActionEvent) => textField.setText(textField.getText() + "4"))
      val button5 = new Button("5")
      button5.prefWidth = 60
      button5.prefHeight = 60
      button5.setOnAction((_: event.ActionEvent) => textField.setText(textField.getText() + "5"))
      val button6 = new Button("6")
      button6.prefWidth = 60
      button6.prefHeight = 60
      button6.setOnAction((_: event.ActionEvent) => textField.setText(textField.getText() + "6"))
      val button7 = new Button("7")
      button7.prefWidth = 60
      button7.prefHeight = 60
      button7.setOnAction((_: event.ActionEvent) => textField.setText(textField.getText() + "7"))
      val button8 = new Button("8")
      button8.prefWidth = 60
      button8.prefHeight = 60
      button8.setOnAction((_: event.ActionEvent) => textField.setText(textField.getText() + "8"))
      val button9 = new Button("9")
      button9.prefWidth = 60
      button9.prefHeight = 60
      button9.setOnAction((_: event.ActionEvent) => textField.setText(textField.getText() + "9"))
      val buttonPoint = new Button(".")
      buttonPoint.prefWidth = 60
      buttonPoint.prefHeight = 60
      buttonPoint.setOnAction((_: event.ActionEvent) => textField.setText(textField.getText() + "."))
      val buttonResult = new Button("=")
      buttonResult.prefWidth = 60
      buttonResult.prefHeight = 60
      buttonResult.setOnAction((_: event.ActionEvent) => {
        val input = textField.getText
        val operands = ListBuffer.empty[String]
        val operators = ListBuffer.empty[Char]
        val operand = new StringBuilder()
        input.toCharArray.foreach((char: Char) => {
          if (!char.equals(' ')) {
            if (char.equals('+') || char.equals('-') || char.equals('*') || char.equals('/')) {
              operands.addOne(operand.toString())
              operand.clear()
              operators.addOne(char)
            } else {
              operand.append(char)
            }
          }
        })
        operands.addOne(operand.toString())

        textField.setText(Calculator.calculate(operands, operators))
      })
      val buttonAdd = new Button("+")
      buttonAdd.prefWidth = 60
      buttonAdd.prefHeight = 60
      buttonAdd.setOnAction((_: event.ActionEvent) => textField.setText(textField.getText() + "+"))
      val buttonSub = new Button("-")
      buttonSub.prefWidth = 60
      buttonSub.prefHeight = 60
      buttonSub.setOnAction((_: event.ActionEvent) => textField.setText(textField.getText() + "-"))
      val buttonMul = new Button("*")
      buttonMul.prefWidth = 60
      buttonMul.prefHeight = 60
      buttonMul.setOnAction((_: event.ActionEvent) => textField.setText(textField.getText() + "*"))
      val buttonDiv = new Button("/")
      buttonDiv.prefWidth = 60
      buttonDiv.prefHeight = 60
      buttonDiv.setOnAction((_: event.ActionEvent) => textField.setText(textField.getText() + "/"))


      root = new BorderPane {

        top = new GridPane {
          padding = Insets(10)
          add(textField, 1, 1)
        }

        center = new GridPane {
          hgap = 10
          vgap = 10

          add(buttonCE, 1, 1)
          add(buttonDel, 2, 1)

          add(button1, 1, 2)
          add(button2, 2, 2)
          add(button3, 3, 2)
          add(buttonAdd, 4, 2)

          add(button4, 1, 3)
          add(button5, 2, 3)
          add(button6, 3, 3)
          add(buttonSub, 4, 3)

          add(button7, 1, 4)
          add(button8, 2, 4)
          add(button9, 3, 4)
          add(buttonMul, 4, 4)

          add(button0, 1, 5)
          add(buttonPoint, 2, 5)
          add(buttonResult, 3, 5)
          add(buttonDiv, 4, 5)
        }
      }
    }
  }
}
