import java.awt.Dimension
import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

import javax.swing.JFrame

import scala.util.Random

object Main {

  def main(args: Array[String]): Unit = {

    def retrievePointsFromFile(filename: String): List[Point] =
      new ObjectInputStream(new FileInputStream(filename)).readObject().asInstanceOf[List[Point]]

    def writeListToFile(filename: String, list: List[Point]): Unit =
      new ObjectOutputStream(new FileOutputStream(filename)).writeObject(list)

    if (args.isEmpty)
      throw new RuntimeException("Input file was not passed as an argument")

    writeListToFile(args(0), generateListOfPoints(1000000, 80))

    val frame = new JFrame()
    frame.setDefaultCloseOperation(2)
    val component = new PointsComponent(retrievePointsFromFile(args(0)))
    component.setPreferredSize(new Dimension(1200, 700))

    frame.setTitle("Clustering")
    frame.setPreferredSize(new Dimension(1200, 700))
    frame.getContentPane.add(component)

    frame.pack()
    frame.setVisible(true)
  }

  private def generateListOfPoints(size: Int, radius: Int) = for (i <- List.range(1, size)) yield i match {
    case a if 1 to size / 3 contains a => Point((Random.nextGaussian() * radius).toInt + Clusters.cluster1.x,
      (Random.nextGaussian() * radius).toInt + Clusters.cluster1.y)
    case a if size / 3 to size * 2 / 3 contains a => Point((Random.nextGaussian() * radius).toInt + Clusters.cluster2.x,
      (Random.nextGaussian() * radius).toInt + Clusters.cluster2.y)
    case _ => Point((Random.nextGaussian() * radius).toInt + Clusters.cluster3.x,
      (Random.nextGaussian() * radius).toInt + Clusters.cluster3.y)
  }
}
