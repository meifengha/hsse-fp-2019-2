import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object Main {

  class Complex (val real : Double, val imaginary : Double) {
    def +(c : Complex) = new Complex(real + c.real, imaginary + c.imaginary)
    def *(c : Complex) = new Complex(real * c.real - imaginary * c.imaginary, real * c.imaginary + imaginary * c.real)
    def abs() = real*real + imaginary*imaginary
  }

  def main(args: Array[String]) {
    val size = 512;
    val img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB)
    val graphics = img.getGraphics()

    for (y0 <- 0 to size) {
      for (x0 <- 0 to size) {
        val a = x0 * 3.0 / size - 2.0
        val b = y0 * 3.0 / size - 1.5
        var z = new Complex(0,0)
        val c = new Complex(a, b)
        var count = 0
        while (z.abs() < 4 && count < size) {
          z = z * z + c
          count += 1
        }
        if (z.abs() < 4){
          graphics.setColor(new Color(64, 255, 64))
          graphics.drawLine(x0, y0, x0, y0)
        }
      }
    }
    ImageIO.write(img, "png", new File("Mandel.png"))
  }
}