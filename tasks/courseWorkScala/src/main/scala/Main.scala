import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}
import java.awt.Dimension
import javax.swing.JFrame
import scala.util.Random

object Main
{

  def main(args: Array[String]): Unit = {

    def retrieveDataFromFile(filename: String): List[Point] = {
      new ObjectInputStream(new FileInputStream(filename)).readObject().asInstanceOf[List[Point]]
    }

    def writeListToFile(filename: String, list: List[Point]): Unit = {
      new ObjectOutputStream(new FileOutputStream(filename)).writeObject(list)
    }

    if (args.isEmpty)
    {
      throw new RuntimeException("Incorrect input file")
    }

    writeListToFile(args(0), newListOfPoints(1000000, 100))

    val frame = new JFrame()
    frame.setDefaultCloseOperation(2)
    val component = new PointsComponent(retrieveDataFromFile(args(0)))
    component.setPreferredSize(new Dimension(1000, 500))

    frame.setPreferredSize(new Dimension(1000, 500))
    frame.getContentPane.add(component)

    frame.pack()
    frame.setVisible(true)
  }

  private def newListOfPoints(size: Int, radius: Int) = {
    for (i <- List.range(1, size)) yield i match
    {
      case n if 1 to (size / 3) contains n => Point((Random.nextGaussian() * radius).toInt + Clusters.cluster1.x,
        (Random.nextGaussian() * radius).toInt + Clusters.cluster1.y)
      case n if (size / 3) to (size * 2 / 3) contains n => Point((Random.nextGaussian() * radius).toInt + Clusters.cluster2.x,
        (Random.nextGaussian() * radius).toInt + Clusters.cluster2.y)
      case _ => Point((Random.nextGaussian() * radius).toInt + Clusters.cluster3.x,
        (Random.nextGaussian() * radius).toInt + Clusters.cluster3.y)
    }
  }
}