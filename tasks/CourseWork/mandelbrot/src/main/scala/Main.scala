import java.io._

object Main {

  def amountIter(c: Complex, out: FileOutputStream): Int = {
    var i: Int = 0
    var z = new Complex(0, 0)
    do {
      z = z * z + c
      i += 1
    } while (z.abs < 4 && i < 200)

    if (z.abs < 4)
      0;
    else
      i;
  }

  def main(args: Array[String]) {
    val weight = 1000
    val height = 1000

    val out = new FileOutputStream("Mandelbrot.pgm")
    out.write(("P5\n" + weight + " " + height + "\n255\n").getBytes())

    val x = -2.0 + 3.0 / weight
    val y = -1.5 + 3.0 / height

    for (y0 <- 0 until height) {
      for (x0 <- 0 until weight) {
        val real: Double = x * x0 - 2.0
        val imag: Double = y * y0 - 1.5
        val c = new Complex(real, imag)
        val i: Int = amountIter(c, out)
        out.write((i * 255.0 / 200).toInt)
      }
    }
    out.close()
  }
}