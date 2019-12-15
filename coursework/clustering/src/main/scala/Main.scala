import java.io.{FileInputStream, FileOutputStream, IOException, ObjectInputStream, ObjectOutputStream}
import java.awt.Dimension

import javax.swing.JFrame

import scala.util.Random

object Main {
  def main(args: Array[String]): Unit = {     //input file required as main parameter

    def readFromFile(file: String): List[Point] =
        new ObjectInputStream(new FileInputStream(file)).readObject().asInstanceOf[List[Point]]

    def writeToFile(file: String, list: List[Point]): Unit =
        new ObjectOutputStream(new FileOutputStream(file)).writeObject(list)

    if (args.isEmpty) {
      throw new IOException("Invalid number of arguments. Input file required\n")
    }

    writeToFile(args(0), generateListOfPoints(2000000, 95))

    val component = new PointsComponent(readFromFile(args(0)))
    component.setPreferredSize(new Dimension(1300, 1200))

    val frame = new JFrame()
    frame.setDefaultCloseOperation(2)

    frame.setTitle("Points Clustering")
    frame.setPreferredSize(new Dimension(1400, 1500))
    frame.getContentPane.add(component)

    frame.pack()
    frame.setVisible(true)
  }

  private def generateListOfPoints(size: Int, radius: Int) = for (i <- List.range(1, size)) yield i match {
    case a if 1 to size / 4 contains a => Point((Random.nextGaussian() * radius).toInt + Clusters.cluster1.x,
      (Random.nextGaussian() * radius).toInt + Clusters.cluster1.y)
    case b if size / 4 to size * 2 / 4 contains b => Point((Random.nextGaussian() * radius).toInt + Clusters.cluster2.x,
      (Random.nextGaussian() * radius).toInt + Clusters.cluster2.y)
    case c if size / 4 to size * 3 / 4 contains c  => Point((Random.nextGaussian() * radius).toInt + Clusters.cluster3.x,
      (Random.nextGaussian() * radius).toInt + Clusters.cluster3.y)
    case _ => Point((Random.nextGaussian() * radius).toInt + Clusters.cluster4.x,
      (Random.nextGaussian() * radius).toInt + Clusters.cluster4.y)
  }
}
