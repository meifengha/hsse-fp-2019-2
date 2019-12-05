import Input._
import Output._
import Clusters._
import scala.collection.mutable.ArrayBuffer

object MyChartApp extends App with scalax.chart.module.Charting {
  writeValuesToFile()
  readValuesFromFile(filePath = filePath)
  val arrX: ArrayBuffer[Int] = scala.collection.mutable.ArrayBuffer.empty[Int]
  val arrY: ArrayBuffer[Int] = scala.collection.mutable.ArrayBuffer.empty[Int]
  for (i <- bufX.indices) {
    if (checkDistance(bufX(i), bufY(i))) {
      arrX += bufX(i)
      arrY += bufY(i)
    }
  }
  val data = for (i <- arrX.indices) yield (arrX(i), arrY(i))
  val chart = XYLineChart(data)
  chart.plot.setRenderer(new org.jfree.chart.renderer.xy.XYLineAndShapeRenderer(false, true))
  chart.saveAsPNG("clusters.png")
  chart.show("Chart", (800, 600), scrollable = false)
}