package calculator

import javax.swing._
import java.awt._
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import calculator.Panel
import javax.swing.border.EmptyBorder

object App
{
 
  def main(args: Array[String]): Unit = new App
}

class App private
{
  private val font = new Font("Verdana", Font.BOLD, 14)
  private val calculator = new Calculator
  private val display = new JTextField 
  {
    setName("Display")
    setText(calculator.getCurrValue)
    setHorizontalAlignment(SwingConstants.RIGHT)
    setFont(font)
    setEditable(false)
  }
  

  new JFrame
  {
    setName("Calculator")
    setTitle("Calculator")
    setContentPane(new Panel
    {
      setBorder(new EmptyBorder(2, 2, 2, 6))
      atNewLineWithColumnSpan(display, 7)
      atNewLine(button("C")).beside(button("/")).beside(button("*")).beside(button("<=")).beside(button("sin"))
      atNewLine(button("7")).beside(button("8")).beside(button("9")).beside(button("+")).beside(button("cos"))
      atNewLine(button("4")).beside(button("5")).beside(button("6")).beside(button("-")).beside(button("tan"))
      atNewLine(button("1")).beside(button("2")).beside(button("3")).beside(button("=")).beside(button("Pi"))
      atNewLineWithColumnSpan(button("0"), 3).beside(button(".")).beside(button("sqrt(x)"))
    })
    setResizable(false)
    pack()
  }.setVisible(true)

  private def button(name: String): JButton = new JButton 
  {
    setName(name)
    setAction(calculatorAction(name))
    setFocusable(false)
    setFont(font)
    registerKeyEventDispatcher(this)
  }

  private def registerKeyEventDispatcher(button: JButton): Unit =
    KeyboardFocusManager.getCurrentKeyboardFocusManager.addKeyEventDispatcher(new KeyEventDispatcher 
        {
        override def dispatchKeyEvent(ev: KeyEvent): Boolean = 
          {
            if (ev.getID == KeyEvent.KEY_TYPED && ev.getKeyChar.toString == button.getName) button.doClick()
            false
      }
    })

  private def calculatorAction(name: String): AbstractAction = new AbstractAction(name)
  {
    override def actionPerformed(event: ActionEvent): Unit =
      try 
    {
        calculator.pressButton(name)
        display.setText(calculator.getCurrValue)
        
      }
    catch 
    {
        case ex: IllegalArgumentException => Toolkit.getDefaultToolkit.beep()
     }
  }
}