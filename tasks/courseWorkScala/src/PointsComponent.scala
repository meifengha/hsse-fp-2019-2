import java.awt.{Color, Graphics}
import javax.swing.JComponent
import java.io.Serializable

case class Point(x: Int, y: Int) extends Serializable

class PointsComponent(private val points: List[Point]) extends JComponent
{

    override def paintComponent(g: Graphics): Unit = {

    super.paintComponent(g)

    def getDistance(point1: Point, point2: Point): Double = math.sqrt(math.pow(point2.x - point1.x, 2) + math.pow(point2.y - point1.y, 2))

    for (it <- points)
    {
      if (getDistance(it, Clusters.cluster1) < getDistance(it, Clusters.cluster2))
      {
        g.setColor(Color.RED)
      }
      else if ((getDistance(it, Clusters.cluster2) < getDistance(it, Clusters.cluster1))
        && (getDistance(it, Clusters.cluster2) < getDistance(it, Clusters.cluster3)))
      {
        g.setColor(Color.BLUE)
      }
      else if (getDistance(it, Clusters.cluster3) < getDistance(it, Clusters.cluster2))
      {
        g.setColor(Color.GREEN)
      }
      g.fillOval(it.x, it.y, 2, 2)
    }
  }
}
