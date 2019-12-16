import scalax.chart.api._
import scala.collection.mutable.ListBuffer
import java.io._

object Main {
  def main(args: Array[String]) {
    def bound(z: (Double, Double), c: (Double, Double), count: Int): Boolean = {
      if (z._1 * z._1 + z._2 * z._2 < 4 && count < 200)
        bound((z._1 * z._1 - z._2 * z._2 + c._1, z._2 * z._1 + z._1 * z._2 + c._2), c, count + 1)
      else if (count == 200)
        true
      else
       false
    }
    def mandelbort(container: ListBuffer[(Double, Double)]): List[(Double, Double)] = {
      for (real <- -2.0 until 2.0 by 0.001)
        for (image <- 2.0 until -2.0 by -0.001)
          if (bound((0.0, 0.0), (real, image), 0))
            container += ((real, image))
      container.toList
    }
    val chart = XYLineChart(mandelbort(ListBuffer()))
    chart.plot.setRenderer(new org.jfree.chart.renderer.xy.XYLineAndShapeRenderer(false, true))
    chart.show()
  }
}
