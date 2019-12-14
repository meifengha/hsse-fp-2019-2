import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.layout.{BackgroundFill, CornerRadii, GridPane}
import scalafx.scene.paint.{Color}
import scalafx.scene.shape.{Circle}
import scalafx.scene.text.Font

object Main extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title.value = "Calculator"
    jfxBackgroundFill2sfx(new BackgroundFill(Color.valueOf("#32302f"), CornerRadii.Empty, Insets.Empty))

    resizable = false
    var expression: String = ""

    width = 280
    height = 360
    centerOnScreen()
    scene = new Scene  {


      fill = Color.rgb(50, 48, 47)


      val text = new TextField {
        style_=("-fx-background-color: #32302f;\n" +
          "-fx-text-fill: white;\n" +
          "-fx-text-alignment: right;\n" +
          "-fx-font-size: 26;")

      }
      text.setFocusTraversable(false)
      text.text.value = "0"

      //set the sizes
      text.prefHeight = 60
      text.prefWidth = 290


      text.layoutX = 5
      text.layoutY = 5


      //set input font  and size
      text.setFont(Font.font("Serif", 22))

      //now add pane
      val pane = new GridPane

      pane.setHgap(5)
      pane.setVgap(5)

      //gray color - for number buttons
      val numberColor : String = "686563"

      def setStyleToButton(button : Button, buttonColor : String, buttonNumber : String) : Unit = {
        button.setShape(circle)
        button.setStyle("-fx-background-color: #" + buttonColor + ";")
        button.setPrefSize(buttonSize, buttonSize)
        button.onAction() = (e: ActionEvent) => {
          if (text.text.value.equals("0")) {
            text.text.value = ""
          }
          text.text = text.text.value + buttonNumber
          var addSpace : Boolean = !buttonNumber.charAt(0).isDigit && !buttonNumber.equals(".") //&& !buttonNumber.equals("(") && !buttonNumber.equals(")")
          if (addSpace) {
            expression = expression + " " + buttonNumber + " "
          } else {
            expression += buttonNumber
          }
        }
      }

      val circle : Circle = new Circle() {
        centerX_=(5)
        centerY_=(5)
        radius_=(10)
      }

      val buttonSize : Int = 50;

      //define buttons
      val button0 = new Button("0")
      setStyleToButton(button0, numberColor, "0")

      val button1 = new Button("1")
      setStyleToButton(button1, numberColor, "1")

      val button2 = new Button("2")
      setStyleToButton(button2, numberColor, "2")


      val button3 = new Button("3")
      setStyleToButton(button3, numberColor, "3")


      val button4 = new Button("4")
      setStyleToButton(button4, numberColor, "4")


      val button5 = new Button("5")
      setStyleToButton(button5, numberColor, "5")


      val button6 = new Button("6")
      setStyleToButton(button6, numberColor, "6")


      val button7 = new Button("7")
      setStyleToButton(button7, numberColor, "7")

      val button8 = new Button("8")
      setStyleToButton(button8, numberColor, "8")


      val button9 = new Button("9")
      setStyleToButton(button9, numberColor, "9")

      val operationColor : String = "f87300"

      val divide : Button = new Button("/")
      setStyleToButton(divide, operationColor, "/")

      val plus  = new Button("+")
      setStyleToButton(plus, operationColor, "+")

      val minus  = new Button("-")
      setStyleToButton(minus, operationColor, "-")

      val multiply = new Button("*")
      setStyleToButton(multiply, operationColor, "*")

      val startGroup  = new Button("(")
      setStyleToButton(startGroup, operationColor, "(")

      val endGroup  = new Button(")")
      setStyleToButton(endGroup, operationColor, ")")

      val degree = new Button("^")
      setStyleToButton(degree, operationColor, "^")

      val commandsColor : String = "bfb2a8"

      val floatPoint  = new Button(".")
      setStyleToButton(floatPoint, commandsColor, ".")

      val clearDisplay = new Button("C")
      setStyleToButton(clearDisplay, commandsColor, "C")

      val calculate = new Button("=")
      setStyleToButton(calculate, commandsColor, "=")

      //set actions
      clearDisplay.onAction  = (e: ActionEvent) => {
        text.text = "0"
        expression = ""
      }


      calculate.onAction  = (e: ActionEvent) => {
        var calculatedValue = Calculator.runCalculate(expression)
        text.text = calculatedValue
        expression = calculatedValue
      }


      //add buttons to pane and position them in the grid
      //#1 row
      pane.add(button7,1,0)
      pane.add(button8,2,0)
      pane.add(button9,3,0)
      pane.add(divide,4,0)
      pane.add(multiply,5,0)

      //#2 row

      pane.add(button4,1,1)
      pane.add(button5,2,1)
      pane.add(button6,3,1)
      pane.add(plus,4,1)
      pane.add(minus,5,1)

      //#3 row

      pane.add(button3,1,2)
      pane.add(button2,2,2)
      pane.add(button1,3,2)
      pane.add(startGroup,4,2)
      pane.add(endGroup,5,2)



      //#4 row

      pane.add(button0,1,3)
      pane.add(clearDisplay,2,3)
      pane.add(floatPoint,3,3)
      pane.add(calculate,4,3)
      pane.add(degree,5,3)


      //position grid pane
      pane.setLayoutY(100)
      pane.setStyle("-fx-background-color: #32302f");

      //create content
      content = List(text,pane)

    }

  }

}