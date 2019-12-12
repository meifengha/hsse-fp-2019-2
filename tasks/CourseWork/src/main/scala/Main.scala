import java.awt._
import javax.imageio._
import java.io.File
import java.awt.image.BufferedImage

object Main {
  //Известно, что множество Мандельброта находится внутри круга радиуса 2.
  //Если на каком-то шаге орбита точки выходит из этого круга, она уже никогда не вернется обратно.
  //Поэтому определяем количество итераций, спустя которое точка останется в кругу или выйдет из него
  def iterations(c : ComplexNumber, max : Int): Int = {
    var compNum = new ComplexNumber(0, 0)
    var numOfIter:Int = 0
    do {
      compNum = compNum*compNum + c
      numOfIter += 1
    } while ((compNum.abs < 4) && (numOfIter < max))
    if (compNum.abs > 4)
      numOfIter;
    else
      0;
  }

  def main(args: Array[String]) {
    val width = 1000
    val height = 1000

    val img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    val g = img.getGraphics();

    // Константы для перевода пикселей в координаты для проведения расчётов
    val viewX = -2.0;
    val viewY = -1.5;
    val dx = 3.0/width; val dy = 3.0/height

    for (y <- 0 to height) {
      for (x <- 0 to width) {
        val re:Double = dx*x + viewX
        val im:Double = dy*y + viewY
        val c = new ComplexNumber(re,im)
        val numOfIter:Int = iterations(c, 200)
        g.setColor(new Color(numOfIter, numOfIter/4, numOfIter))
        g.drawLine(y,x,y,x)
      }
    }
    ImageIO.write(img, "png", new File("Mandelbrot.png"));
  }
}
