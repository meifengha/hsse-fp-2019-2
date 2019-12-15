import scala.util.Random

case object Clusters {
  val cluster1: Point = Point(0, Random.nextInt(800))
  val cluster2: Point = Point(300, Random.nextInt(700))
  val cluster3: Point = Point(550, Random.nextInt(500))
  val cluster4: Point = Point(700, 300 + Random.nextInt(500))
}
