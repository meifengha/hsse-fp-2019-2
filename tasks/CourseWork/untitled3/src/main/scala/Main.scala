import org.sameersingh.scalaplot.Implicits._

import scala.io._


object Main {

  def main(args: Array[String]): Unit ={
    val data = Source.fromFile("data.txt").getLines().toList
    var x = data.map(line => line.split(" ")(0).toDouble).toList
    var y = data.map(line => line.split(" ")(1).toDouble).toList

    output(GUI, xyChart(x ->(y)))
  }
}
