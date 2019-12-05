import java.io.{File, PrintWriter}

object Input {
  val filePath = "data.txt"

  def writeValuesToFile() {
    val pw = new PrintWriter(new File(filePath))
    for (_ <- 0 to 1000000) {
      val x = scala.util.Random.nextInt(1000)
      val y = scala.util.Random.nextInt(1000)
      pw.write("(" + x.toString + ", " + y.toString + ")\n")
    }
    pw.close()
  }
}