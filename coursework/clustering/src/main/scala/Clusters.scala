import scala.util.Random

case object Clusters {
  val cluster1: Point = Point(80 + Random.nextInt(490), 70 + Random.nextInt(210))
  val cluster2: Point = Point(80 + Random.nextInt(490), 570 + Random.nextInt(200))
  val cluster3: Point = Point(690 + Random.nextInt(490), 70 + Random.nextInt(210))
  val cluster4: Point = Point(690 + Random.nextInt(490), 570 + Random.nextInt(200))
}
