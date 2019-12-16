import calc.Calculator
import scala.swing._

object Main {

  val ta1,ta2 = new TextArea
  ta1.border = Swing.EmptyBorder(20, 10, 20, 10)
  ta1.font = new Font("Arial", 1, 35)
  ta2.border = ta1.border
  ta2.font = new Font("Arial", 0, 15)

  val bt1 = new Button(Action("Calculate"){
    val expr = ta1.text.trim
    if (expr!="") {
      Calculator.calculate(expr)
      if (ta2.text != "") ta2.text += '\n'
      ta2.text += (if (Calculator.isError) Calculator.errorStr else expr +" = "+Calculator.result)
    }
  })

  bt1.font = new Font("Arial", 1, 25)
  bt1.preferredSize = new Dimension(300, 35)

  val bt2 = new Button(Action("Clear history") {
    ta2.text = ""
  })
  bt2.font = new Font("Arial", 1, 25)
  bt2.preferredSize = new Dimension(300, 35)

  val frame = new MainFrame {
    title = "Calculator"
    import BorderPanel.Position._
    contents = new BorderPanel {
      layout += new BorderPanel {
        layout += new BoxPanel(Orientation.Horizontal) {
          border = Swing.EmptyBorder(0, 10, 0, 10)
          contents += bt1
          contents += bt2
        } -> South
        layout += ta1 -> Center
      } -> North
      layout += ta2 -> Center
    }
    size = new Dimension(600,500)
    centerOnScreen
    ta1.text = "2+2*2"
  }

  def main(args: Array[String]): Unit = {
    frame.visible = true
  }
}
