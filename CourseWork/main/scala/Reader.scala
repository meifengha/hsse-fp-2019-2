import scala.io.Source

object Reader {
    def read(): List[(Int, Int)] = {
        var lines = Source.fromFile("res/Data.txt").getLines().toList
        lines = lines.map(line => {
            line.replace(")", "").replace("(", "")
        })
        lines.map(line => {
            (line.substring(0, line.indexOf(',')).toInt,
              line.substring(line.indexOf(',') + 1, line.length).toInt)
        })
    }
}
