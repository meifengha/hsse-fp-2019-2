import java.io.{File, PrintWriter}

object Input {
  val filePath = "data.txt"

  def writeValues() {
    val pw = new PrintWriter(new File(filePath))
    for (_ <- 0 to 1000000) {
      val x = scala.util.Random.nextInt(10000)
      val y = scala.util.Random.nextInt(10000)
      pw.write("(" + x.toString + ", " + y.toString + ")\n")
    }
    pw.close()
  }
}
