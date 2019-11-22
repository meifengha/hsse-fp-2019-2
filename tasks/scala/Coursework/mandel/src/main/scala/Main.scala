import javax.swing.ImageIcon
import mandel.mandelbrot

import scala.swing._
import scala.swing.event._

object Main extends SimpleSwingApplication {
  def top: MainFrame = new MainFrame {
    val width = 640
    val height = 480
    var minX: Double = -2
    var maxX: Double = 1
    var minY: Double = -1
    var maxY: Double = 1

    val label = new Label {
      icon = new ImageIcon(mandelbrot.compute(width, height, minX, maxX, minY, maxY).img)
    }

    def dx(): Double = (maxX - minX) / 10.0
    def dy(): Double = (maxY - minY) / 10.0

    def moveRight(): ImageIcon = {
      maxX += dx()
      minX += dx()
      new ImageIcon(mandelbrot.compute(width, height, minX, maxX, minY, maxY).img)
    }

    def moveLeft(): ImageIcon = {
      maxX -= dx()
      minX -= dx()
      new ImageIcon(mandelbrot.compute(width, height, minX, maxX, minY, maxY).img)
    }

    def moveUp(): ImageIcon = {
      maxY -= dy()
      minY -= dy()
      new ImageIcon(mandelbrot.compute(width, height, minX, maxX, minY, maxY).img)
    }

    def moveDown(): ImageIcon = {
      maxY += dy()
      minY += dy()
      new ImageIcon(mandelbrot.compute(width, height, minX, maxX, minY, maxY).img)
    }

    def upScale(): ImageIcon = {
      maxX -= dx()
      minX += dx()
      maxY -= dy()
      minY += dy()
      new ImageIcon(mandelbrot.compute(width, height, minX, maxX, minY, maxY).img)
    }

    def downScale(): ImageIcon = {
      maxX += dx()
      minX -= dx()
      maxY += dy()
      minY -= dy()
      new ImageIcon(mandelbrot.compute(width, height, minX, maxX, minY, maxY).img)
    }

    contents = new BoxPanel(Orientation.Vertical) {
      contents += label
      listenTo(keys)
      reactions += {
        case KeyPressed(_, Key.Right, _, _) => label.icon = moveRight()
        case KeyPressed(_, Key.Left , _, _) => label.icon = moveLeft()
        case KeyPressed(_, Key.Up   , _, _) => label.icon = moveUp()
        case KeyPressed(_, Key.Down , _, _) => label.icon = moveDown()
        case KeyPressed(_, Key.Enter, _, _) => label.icon = upScale()
        case KeyPressed(_, Key.Space, _, _) => label.icon = downScale()
      }
      focusable = true
      requestFocus
    }
  }
}
