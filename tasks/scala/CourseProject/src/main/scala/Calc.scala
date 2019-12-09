import scalafx.scene.text.FontWeight.Black

import scala.swing._
import scala.annotation.switch



object Calc {
  def changeOp(op:String, tmp1:Double, tmp2:Double): Double = op match
  {
    case "+" => tmp1 + tmp2
    case "-" => tmp1 - tmp2
    case "*" => tmp1 * tmp2
    case "/" => tmp1 / tmp2
    case _ => 0
  }

    var comma:Boolean = false
    var flag:Boolean = false

    var a:Double = 0
    var b:Double = 0
    var c:Double = 0
    var op1:String = ""
    var op2:String = ""

    val frame = new MainFrame {
    title = "iDno"
    import BorderPanel.Position._

    contents = new BorderPanel {

      var label = new Label(" ")
      label.opaque = true
      label.foreground = java.awt.Color.BLACK
      var flag = false
      layout += new GridPanel(3, 1) {
        contents += new Label("")
        contents += label
        contents += new Label("")
      } -> North

      label.text = "0"

      layout += new GridPanel(5, 4) {
        contents += new Button(Action("AC"){
          label.text = "0"
          comma = false
          flag = false
          a = 0
          b = 0
          c = 0
          op1 = ""
          op2 = ""
        })
        contents += new Button(Action("<<") {
          if (label.text != "Error")
          {
            if (label.text.last == '.') { comma = false }
            label.text = label.text.dropRight(1)
            if ((label.text == "") || (label.text == "-"))
            {
              label.text = "0"
              flag = false
            }
          }
        })
        contents += new Button(Action("Exit") {
          sys.exit(0)
        })
        contents += new Button(Action("/") {
          if (label.text != "Error") {
            if (op2 != "") {
              c = label.text.toDouble
              if (op2 == "*") {
                b = changeOp(op2, b, c)
                label.text = b.toString
                op2 = "/"
              }
              else {
                if (c == 0) {
                  label.text = "Error"
                  flag = false
                  comma = false
                  op1 = ""
                  op2 = ""
                }
                else {
                  b = changeOp(op2, b, c)
                  label.text = b.toString
                }
              }
            }
            else if (op1 != "") {
              b = label.text.toDouble
              if (op1 == "*") {
                a = changeOp(op1, a, b)
                label.text = a.toString
                op1 = "/"
              }
              else if (op1 == "/") {
                if (b == 0) {
                  label.text = "Error"
                  flag = false
                  comma = false
                  op1 = ""
                  op2 = ""
                }
                else {
                  a = changeOp(op1, a, b)
                  label.text = a.toString
                }
              }
              else {
                op2 = "/"
              }
            }
            else {
              a = label.text.toDouble
              op1 = "/"
            }
            flag = false
            comma = true
          }
        })
        contents += new Button(Action("7") {

          if (flag){
            label.text += "7"
          } else {
            label.text = "7"
            flag = true
            comma = false
          }
        })
        contents += new Button(Action("8") {
          if (flag){
            label.text += "8"
          } else {
            label.text = "8"
            flag = true
            comma = false
          }
        })
        contents += new Button(Action("9") {
          if (flag){
            label.text += "9"
          } else {
            label.text = "9"
            flag = true
            comma = false
          }
        })
        contents += new Button(Action("*") {
          if (label.text != "Error") {
            if (op2 != "") {
              c = label.text.toDouble
              if (op2 == "*") {
                b = changeOp(op2, b, c)
                label.text = b.toString
              }
              else {
                if (c == 0) {
                  label.text = "Error"
                  flag = false
                  comma = false
                  op1 = ""
                  op2 = ""
                }
                else {
                  b = changeOp(op2, b, c)
                  label.text = b.toString
                  op2 = "*"
                }
              }
            }
            else if (op1 != "") {
              b = label.text.toDouble
              if (op1 == "*") {
                a = changeOp(op1, a, b)
                label.text = a.toString
              }
              else if (op1 == "/") {
                if (b == 0) {
                  label.text = "Error"
                  flag = false
                  comma = false
                  op1 = ""
                  op2 = ""
                }
                else {
                  a = changeOp(op1, a, b)
                  label.text = a.toString
                  op1 = "*"
                }
              }
              else {
                op2 = "*"
              }
            }
            else {
              a = label.text.toDouble
              op1 = "*"
            }
            flag = false
            comma = true
          }
        })
        contents += new Button(Action("4") {
          if (flag){
            label.text += "4"
          } else {
            label.text = "4"
            flag = true
            comma = false
          }
        })
        contents += new Button(Action("5") {
          if (flag){
            label.text += "5"
          } else {
            label.text = "5"
            flag = true
            comma = false
          }
        })
        contents += new Button(Action("6") {
          if (flag){
            label.text += "6"
          } else {
            label.text = "6"
            flag = true
            comma = false
          }
        })
        contents += new Button(Action("+") {
          if (label.text != "Error") {
            if (op2 != "") {
              c = label.text.toDouble
              if ((op2 == "/") && (c == 0)) {
                label.text = "Error"
                flag = false
                comma = false
                op1 = ""
                op2 = ""
              }
              else {
                b = changeOp(op2, b, c)
                a = changeOp(op1, a, b)
                op1 = "+"
                op2 = ""
                label.text = a.toString
              }
            }
            else if (op1 != "") {
              b = label.text.toDouble
              if ((op1 == "/") && (b == 0)) {
                label.text = "Error"
                flag = false
                comma = false
                op1 = ""
                op2 = ""
              }
              else {
                a = changeOp(op1, a, b)
                op1 = "+"
                label.text = a.toString
              }
            }
            else {
              a = label.text.toDouble
              op1 = "+"
            }
            comma = true
            flag = false
          }
        })
        contents += new Button(Action("1") {
          if (flag){
            label.text += "1"
          } else {
            label.text = "1"
            flag = true
            comma = false
          }
        })
        contents += new Button(Action("2") {
          if (flag){
            label.text += "2"
          } else {
            label.text = "2"
            flag = true
            comma = false
          }
        })
        contents += new Button(Action("3") {
          if (flag){
            label.text += "3"
          } else {
            label.text = "3"
            flag = true
            comma = false
          }
        })
        contents += new Button(Action("-") {
          if (label.text != "Error") {
            if (op2 != "") {
              c = label.text.toDouble
              if ((op2 == "/") && (c == 0)) {
                label.text = "Error"
                flag = false
                comma = false
                op1 = ""
                op2 = ""
              }
              else {
                b = changeOp(op2, b, c)
                a = changeOp(op1, a, b)
                op1 = "-"
                op2 = ""
                label.text = a.toString
              }
            }
            else if (op1 != "") {
              b = label.text.toDouble
              if ((op1 == "/") && (b == 0)) {
                label.text = "Error"
                flag = false
                comma = false
                op1 = ""
                op2 = ""
              }
              else {
                a = changeOp(op1, a, b)
                op1 = "-"
                label.text = a.toString
              }
            }
            else {
              a = label.text.toDouble
              op1 = "-"
            }
            comma = true
            flag = false
          }
        })
        contents += new Button(Action("0") {
          if (flag){
            label.text += "0"
          } else {
            label.text = "0"
            flag = true
            comma = false
          }
        })
        contents += new Button(Action("-/+") {
          if ((label.text != "0") && (label.text != "Error")) {
            if (label.text.charAt(0) == '-') {
              label.text = label.text.reverse.dropRight(1).reverse
            } else {
              label.text = "-" + label.text
            }
          }
        })
        contents += new Button(Action(".") {
          if (!comma) {
            comma = true
            label.text += "."
            flag = true
          }
        })
        contents += new Button(Action("=") {
          if (label.text != "Error") {
            if (op2 != "") {
              c = label.text.toDouble
              if ((op2 == "/") && (c == 0)) {
                label.text = "Error"
                flag = false
                comma = false
              }
              else {
                b = changeOp(op2, b, c)
                a = changeOp(op1, a, b)
                label.text = a.toString
              }
            }
            else if (op1 != "") {
              b = label.text.toDouble
              if ((op1 == "/") && (b == 0)) {
                label.text = "Error"
                flag = false
                comma = false
              }
              else {
                a = changeOp(op1, a, b)
                label.text = a.toString
              }
            }
            else {
              a = label.text.toDouble
            }
            op1 = ""
            op2 = ""
            comma = true
            flag = false
          }
        })

      } -> Center
    }

    size = new Dimension(313, 500)
    centerOnScreen
  }

  def main(args: Array[String]): Unit = frame.visible = true
}
