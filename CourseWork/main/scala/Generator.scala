import scala.collection.mutable.ListBuffer

object Generator {
  def generate(): List[(Int, Int)] = {
    val r = scala.util.Random
    var (x, y) = (0, 0)
    val output = new ListBuffer[(Int, Int)]
    for (_ <- 0 to 1000) {
      x = r.nextInt(1000)
      y = r.nextInt(1000)
      output.append((x, y))
    }
    output.toList
  }
}
