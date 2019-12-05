import java.awt.{Color, Graphics}

import javax.swing.JComponent

class PointsComponent(private val points: List[Point]) extends JComponent {
  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)

    def getDistance(p1: Point, p2: Point): Double = math.sqrt(math.pow(p2.x - p1.x, 2) + math.pow(p2.y - p1.y, 2))

    for (it <- points) {
      if (getDistance(it, Clusters.cluster1) < getDistance(it, Clusters.cluster2)) {
        g.setColor(Color.BLUE)
      } else if ((getDistance(it, Clusters.cluster2) < getDistance(it, Clusters.cluster1)) && (getDistance(it, Clusters.cluster2) < getDistance(it, Clusters.cluster3))) {
        g.setColor(Color.RED)
      } else if (getDistance(it, Clusters.cluster3) < getDistance(it, Clusters.cluster2)) {
        g.setColor(Color.GREEN)
      }
      g.fillOval(it.x, it.y, 2, 2)
    }
  }
}
