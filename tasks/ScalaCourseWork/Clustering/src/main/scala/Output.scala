import scala.io.Source

object Output {
  def readValues(filePath: String) {
    val bufX = scala.collection.mutable.ArrayBuffer.empty[Int]
    val bufY = scala.collection.mutable.ArrayBuffer.empty[Int]
    val fSource = Source.fromFile(filePath)
    for (line <- fSource.getLines) {
      println(line)
      val opBr = line.indexOf("(")
      val cm = line.indexOf(",")
      val clBr = line.indexOf(")")
      val first = line.substring(opBr + 1, cm).toInt
      val second = line.substring(cm + 2, clBr).toInt
      bufX += first
      bufY += second
    }
    fSource.close
  }
}
