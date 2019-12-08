import java.io._
import java.lang.Math

object Mandelbrot {
  class Complex(val a: Double, val b: Double){
    // represents the complex number a + b*i
    def +(that: Complex) = new Complex(this.a+that.a,this.b+that.b)
    def *(that: Complex) = new Complex(this.a*that.a-this.b*that.b,this.a*that.b+that.a*this.b)
    def abs() = Math.sqrt(this.a*this.a + this.b*this.b)
  }

  def run(n: Int, level: Int) : Unit = {
    val out = new FileOutputStream("MandelbrotImg.pgm")
    out.write(("P5\n"+n+" "+n+"\n255\n").getBytes())

    for {y0 <- 0 until n
	 x0 <- 0 until n }{
	   // y0 and x0 are in pixel integers
	   // x  and y  are real number coordinates
	   val x = -2.0 + x0*3.0/n
	   val y = -1.5 + y0*3.0/n

	   var z = new Complex(0,0)
	   var c = new Complex(x,y)
	   var i = 0
           do {
             //the main formula
	     z = z*z + c 
             i += 1
	   } while (z.abs < 2 && i < level)

           if (z.abs < 2)
	     out.write(0);
	   else
	     out.write((i*255.0/level).toInt);
	 }
    out.close()
  }

  def main(args: Array[String]) {
    run(Integer.parseInt(args(0)), Integer.parseInt(args(1)))
  }
}
