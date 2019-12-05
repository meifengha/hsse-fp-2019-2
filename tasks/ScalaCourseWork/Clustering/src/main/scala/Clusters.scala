object Clusters {

  object Cluster1 {
    val x: Int = scala.util.Random.nextInt(900)
    val y: Int = scala.util.Random.nextInt(900)
    val rad: Int = scala.util.Random.nextInt(100)
  }

  object Cluster2 {
    val x: Int = scala.util.Random.nextInt(900)
    val y: Int = scala.util.Random.nextInt(900)
    val rad: Int = scala.util.Random.nextInt(100)
  }

  object Cluster3 {
    val x: Int = scala.util.Random.nextInt(900)
    val y: Int = scala.util.Random.nextInt(900)
    val rad: Int = scala.util.Random.nextInt(100)
  }

  def checkDistance(x: Int, y: Int): Boolean = {
    val dis1 = Math.pow(x - Cluster1.x, 2) + Math.pow(y - Cluster1.y, 2)
    val dis2 = Math.pow(x - Cluster2.x, 2) + Math.pow(y - Cluster2.y, 2)
    val dis3 = Math.pow(x - Cluster3.x, 2) + Math.pow(y - Cluster3.y, 2)
    if (dis1 <= Math.pow(Cluster1.rad, 2)) return true
    if (dis2 <= Math.pow(Cluster2.rad, 2)) return true
    if (dis3 <= Math.pow(Cluster3.rad, 2)) return true
    false
  }

}
