import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Output {
  val bufX: ArrayBuffer[Int] = scala.collection.mutable.ArrayBuffer.empty[Int]
  val bufY: ArrayBuffer[Int] = scala.collection.mutable.ArrayBuffer.empty[Int]

  def readValuesFromFile(filePath: String) {
    val fSource = Source.fromFile(filePath)
    for (line <- fSource.getLines) {
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