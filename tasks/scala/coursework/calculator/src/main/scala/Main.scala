package coursework.calculator.src.main.scala

import scala.swing._
import javax.swing.{ActionMap, InputMap, JComponent, KeyStroke}
import scala.swing.event.Key

object Main extends App with scalax.chart.module.Charting {

  val calculator = new CalculatorShell
  val frame: Frame = new Frame {
    title = "Калькулятор"
    val panel: GridBagPanel = calculator.CalcPanel()
    contents = panel

    val inputMap: InputMap = panel.peer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
    val actionMap: ActionMap = panel.peer.getActionMap

    val symbols: List[(Char, List[Int])] = List(
      ('0', List(Key.Numpad0.id, Key.Key0.id)),
      ('1', List(Key.Numpad1.id, Key.Key1.id)),
      ('2', List(Key.Numpad2.id, Key.Key2.id)),
      ('3', List(Key.Numpad3.id, Key.Key3.id)),
      ('4', List(Key.Numpad4.id, Key.Key4.id)),
      ('5', List(Key.Numpad5.id, Key.Key5.id)),
      ('6', List(Key.Numpad6.id, Key.Key6.id)),
      ('7', List(Key.Numpad7.id, Key.Key7.id)),
      ('8', List(Key.Numpad8.id, Key.Key8.id)),
      ('9', List(Key.Numpad9.id, Key.Key9.id)),
      ('+', List(Key.Plus.id)),
      ('-', List(Key.Minus.id)),
      ('*', List(Key.Multiply.id)),
      ('/', List(Key.Slash.id)),
      ('(', List(Key.OpenBracket.id)),
      (')', List(Key.CloseBracket.id)),
      ('.', List(Key.Comma.id))
    )

    def actionsGen(list: List[(Char, List[Int])]): List[(Action, List[Int])] = {
      if (list.isEmpty) {
        List()
      } else {
        (Action("key" + list.head._1) { calculator.inputField.text += list.head._1 }, list.head._2) :: actionsGen(list.tail)
      }
    }
    val actions: List[(Action, List[Int])] = actionsGen(symbols)
    actions.foreach(action => {
      actionMap.put(action._1.title, action._1.peer)
      action._2.foreach(key => inputMap.put(KeyStroke.getKeyStroke(key, 0), action._1.title))
    })

    val actBck: Action = Action("keyBck") {
      if (calculator.inputField.text.length > 0) {
        calculator.inputField.text = calculator.inputField.text.substring(0, calculator.inputField.text.length - 1)
      }
    }
    inputMap.put(KeyStroke.getKeyStroke(Key.BackSpace.id, 0), "keyBck")
    actionMap.put("keyBck", actBck.peer)

    size = new Dimension(250, 320)
    centerOnScreen()
    resizable = false
  }
  frame.open()

}