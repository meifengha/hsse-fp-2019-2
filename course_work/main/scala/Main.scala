import neural_network_lib.NeuralNetwork
import neural_network_lib.TrainingData
import processing.core.PApplet
import java.util.Random


object Main {
  def main(args: Array[String]): Unit = {
    PApplet.main("Main", args)
  }
}

class Main extends PApplet {
  val nn = new NeuralNetwork(2, 4, 1)
  val arr: Array[TrainingData] = Array[TrainingData](new TrainingData(Array[Double](0.0, 1.0), Array[Double](0.0)), new TrainingData(Array[Double](1.0, 0.0), Array[Double](0.0)), new TrainingData(Array[Double](0.0, 0.0), Array[Double](1.0)), new TrainingData(Array[Double](1.0, 1.0), Array[Double](1.0)))

  override def settings(): Unit = {
    size(400, 400)
  }

  override def draw(): Unit = {
    background(0)
    val r = new Random
    for (i <- 0 until 50000) {
      val randomIndex = r.nextInt(arr.length)
      nn.train(arr(randomIndex).getInputs, arr(randomIndex).getTargets)
    }
    val resolution = 5
    val cols = Math.floor(width / resolution).toFloat
    val rows = Math.floor(height / resolution).toFloat
    var i = 0
    while ( {
      i < cols
    }) {
      var j = 0
      while ( {
        j < rows
      }) {
        val x1 = i / cols
        val x2 = j / rows
        val inputs = Array[Double](x1, x2)
        val y = nn.predict(inputs)(0).toFloat
        fill(y * 255)
        rect(i * resolution, j * resolution, resolution, resolution)

        j += 1
      }

      i += 1
    }
  }
}
