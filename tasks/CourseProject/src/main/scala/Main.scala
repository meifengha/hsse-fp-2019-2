import java.awt._
import javax.imageio._
import java.io.File
import java.awt.image.BufferedImage

class Complex (val real : Double, val imaginary : Double) {
  def +(c : Complex) = new Complex(real + c.real, imaginary + c.imaginary)
  def *(c : Complex) = new Complex(real * c.real - imaginary * c.imaginary, real * c.imaginary + imaginary * c.real)
  def abs() = real*real + imaginary*imaginary
}

object Main {
  def steps(c : Complex, max : Int): Int = {
    var newNumber = new Complex(0, 0)
    for (i <- 0 to max) {
      newNumber = newNumber*newNumber + c
      if (newNumber.abs > 4) {
        return i
      }
    }
    return 0
  }

  def main(args: Array[String]) {
    val w = 500
    val h = 500

    val outputFile = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    val graphic = outputFile.getGraphics();

    //Умножаем х и у на константы для перевода пикселей в координаты для проведения расчётов
    for (y <- 0 to h) {
      for (x <- 0 to w) {
        val c = new Complex((x*3.0)/w - 2.0, (y*3.0)/h - 1.5)
        // Определение цвета точек
        graphic.setColor(new Color(steps(c, 255)/2, steps(c, 255)/4, steps(c, 255)))
        // Прорисовка точки как прямой с концами в одной точке
        graphic.drawLine(x,y,x,y)
      }
    }
    ImageIO.write(outputFile, "png", new File("Mandelbrot.png"));
  }
}
