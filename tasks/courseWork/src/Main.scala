import java.io._
import java.lang.Math

object mandelbrot {
  class Complex(val a: Double, val b: Double){
    def +(that: Complex) = new Complex(this.a+that.a,this.b+that.b)
    def *(that: Complex) = new Complex(this.a*that.a-this.b*that.b,this.a*that.b+that.a*this.b)
    def abs(): Double = Math.sqrt(this.a*this.a + this.b*this.b)
  }

  def run(n: Int, level: Int) : Unit = {
    val out = new FileOutputStream("mandelbrot.pgm")
    out.write(("P5\n"+n+" "+n+"\n255\n").getBytes())

    for {y0 <- 0 until n
         x0 <- 0 until n }{

      val x = -2.0 + x0*3.0/n
      val y = -1.5 + y0*3.0/n

      var z = new Complex(0,0)
      val c = new Complex(x, y)
      for(i <- 0 until level)
        z = z*z + c

      if (z.abs < 2)
        out.write(0);
      else
        out.write(255);
    }
    out.close()
  }

  def main(args: Array[String]) {
    run(500, 10000)
  }
}