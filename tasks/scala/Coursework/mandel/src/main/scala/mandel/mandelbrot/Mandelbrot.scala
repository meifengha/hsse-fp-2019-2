package mandel

import java.awt.Color

import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import complex._
import bitmap._

package object mandelbrot {
  def compute(width: Int, height: Int, xMin: Double, xMax: Double, yMin: Double, yMax: Double): Bitmap = {
    val maxIter = 500
    val bm = new Bitmap(width, height)

    val cx = (xMax - xMin) / width
    val cy = (yMax - yMin) / height

    // Догадываюсь, что решение кривое, но вычисления действительно ускоряются

    val futures = {
      for {y <- 0 until bm.height} yield Future {
        for (x <- 0 until bm.width) {
          val c = Complex(xMin + x * cx, yMin + y * cy)
          val iter = itMandel(c, maxIter, 4)
          bm.setColor(x, y, getColor(iter, maxIter))
        }
      }
    }.map(Await.result(_, 10.second))
    bm
  }

  private def itMandel(c: Complex, iMax: Int, bailout: Int): Int = {
    var z = Complex(0, 0)
    for(i <- 0 until iMax){
      z = z * z + c
      if(z.abs > bailout) return i
    }
    iMax;
  }

  private def getColor(iter: Int, max: Int): Color = {
    if (iter == max) return Color.BLACK
    val c = 3 * math.log(iter) / math.log(max - 1.0)
    if (c < 1) new Color(0, 0, (255 * c).toInt)
    else if (c < 2) new Color(0, (255 * (c - 1)).toInt, 255)
    else new Color((255 * (c - 2)).toInt, 255, 255)
  }

}
