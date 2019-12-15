import java.awt.{Color, Graphics}
import javax.swing.JComponent
import java.io.Serializable

case class Point(x: Int, y: Int) extends Serializable

class PointsComponent(private val points: List[Point]) extends JComponent {
  override def paintComponent(graphic: Graphics): Unit = {
    super.paintComponent(graphic)

    def getDistance(point1: Point, point2: Point): Double = math.sqrt(math.pow(point2.x - point1.x, 2) + math.pow(point2.y - point1.y, 2))

    for (point <- points) {
      if (getDistance(point, Clusters.cluster1) < getDistance(point, Clusters.cluster2)) {
        graphic.setColor(Color.GREEN)
      } else if ((getDistance(point, Clusters.cluster2) < getDistance(point, Clusters.cluster1))
              && (getDistance(point, Clusters.cluster2) < getDistance(point, Clusters.cluster3))
              && (getDistance(point, Clusters.cluster2) < getDistance(point, Clusters.cluster4))) {

        graphic.setColor(Color.RED)

      } else if ((getDistance(point, Clusters.cluster3) < getDistance(point, Clusters.cluster2))
           && (getDistance(point, Clusters.cluster3) < getDistance(point, Clusters.cluster4))) {

        graphic.setColor(Color.MAGENTA)

      } else if ((getDistance(point, Clusters.cluster4) < getDistance(point, Clusters.cluster3))
             && (getDistance(point, Clusters.cluster4) < getDistance(point, Clusters.cluster2))) {

        graphic.setColor(Color.BLUE)
      }
      graphic.fillOval(point.x, point.y, 1, 1)
    }
  }
}