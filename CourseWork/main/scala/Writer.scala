import java.io.{BufferedWriter, File, FileWriter}

object Writer {
  def write(data: List[(Int, Int)]): Unit = {
    val file = new File("res/Data.txt")
    val bw = new BufferedWriter(new FileWriter(file))
    data.foreach(line => {
      bw.write(line.toString() + '\n')
    })
    bw.close()
  }
}
