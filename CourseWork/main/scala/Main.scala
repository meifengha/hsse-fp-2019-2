import processing.core.PApplet

class Main extends PApplet {

  override def settings() = {
    size(1000, 1000)
  }

  override def setup() = {
    background(0)
  }

  override def draw() = {
    Writer.write(Generator.generate())
    val data = Reader.read()
    stroke(random(255), random(255), random(255))
    data.foreach(p => {
      point(p._1, p._2)
    })
  }
}

object Main {
  def main(args: Array[String]) = PApplet.main("Main")
}
