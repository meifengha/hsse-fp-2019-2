package course
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File
import Complex._

object Mandelbrot {
  val black = 0xffffff
  val white = 0x000000
  def run(n: Int, level: Int): Unit = {
    val png = new BufferedImage(n, n, BufferedImage.TYPE_INT_RGB)
    for {y0 <- 0 until n
         x0 <- 0 until n} {
      val x = -2.0 + x0 * 3.0 / n
      val y = -1.5 + y0 * 3.0 / n

      var z = new Complex(0, 0)
      val c = new Complex(x, y)
      for (i <- 0 until level)
        z = z * z + c
      if (z.modulus < 2)
        png.setRGB(x0, y0, white);
      else
        png.setRGB(x0, y0, black)
    }
  val output = new File("image.png");
    ImageIO.write(png, "png", output)
  }
  def main(args: Array[String]) {
    run(Integer.parseInt(args(1)), Integer.parseInt(args(0)))
  }
}
