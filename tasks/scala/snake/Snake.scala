//The game of Snake in Scala
package snake

import javax.swing.{JFrame,JPanel,Timer}
import java.awt.Graphics
import java.awt.event.{ActionEvent,ActionListener,KeyListener,KeyEvent}
import scala.actors.Actor
import scala.actors.Actor._

case class Listen(actor: Actor)
case class SnakeMoved()

object Game extends JFrame
{
  val Height = 30
  val Width = 30
  val Scale = 10

  def main(args: Array[String]) {
    //launch the program; start the timer, etc.
    val gameBoard = new SnakeDataModel
    val view = new View(gameBoard)
    add(view)
    val controller = new Controller(gameBoard)
    addKeyListener(controller)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    setVisible(true)
    this.setSize(Width * Scale + Scale / 2, Height * Scale + Scale / 2)
    controller.start
  }
}

class SnakeDataModel extends Actor {
  type Point = (Int, Int)
  type Vector = (Int, Int)
  type Snake = List[Point]
  private var handlers : List[Actor] = Nil

  var snake: Snake = List()
  var apple: Point = (0,0)

  //move the segment as specified by dir
  private def translate(seg: Point, dir: Vector) : Point = {
    (seg._1 + dir._1, seg._2 + dir._2)
  }

  private def randomInt(i:Int): Int = (Math.random * i).toInt

  def isGameOver = snake.isEmpty

  def isAppleInSnake = snake.contains(apple)

  //set the snake back to its start position
  def resetSnake {
    snake = (for (i <- List.range(1,10)) yield (i, 2)) reverse
  }

  //move the apple to a random spot on the board, not occupied by the snake
  def resetApple {
    do apple = (randomInt(Game.Width - 4) + 1, randomInt(Game.Height - 4) + 1)
    while (snake.intersect(List(apple)) != List.empty)
  }

  //code to handle listeners to SnakeMoved event
  def act = {
    loop {
      react {
        case Listen(actor) => handlers = actor :: handlers
      }
    }
  }

  //advance one unit in the specified direction; grow forward if the
  //apple is eaten.
  def move(dir: Vector) {
    type sCont = (Snake => Snake)
    
    val newSnake = translate(snake.head, dir) :: snake

    def buildSnake(snake: Snake, k: sCont) : Snake = {
      val break = k;
      def iter(snake: Snake, k: sCont) : Snake = {
        (snake: @unchecked) match {
          //loser
          case h :: t if t.contains(h) => break(List())
          //ate the apple
          case h :: t if h == apple => 
            iter(t, s => k(translate(h, dir) :: h :: s))
          //truncate the tail
          case h :: List() => k(List())
          //rest of body remains constant
          case h :: t => iter(t, s => k(h :: s))
        }
      }
      iter(snake, s => s)
    }
    snake = buildSnake(newSnake, s => s)
    handlers foreach (_ ! SnakeMoved)
  }

  //initialize game state
  resetSnake
  resetApple
}

class Controller(model: SnakeDataModel) extends ActionListener with KeyListener {
  import java.awt.event.KeyEvent._

  val timer = new Timer(100, this)
  var dir = (1,0)

  override def actionPerformed(e: ActionEvent) {
    if (model.isAppleInSnake)
      model.resetApple
    
    if (model.isGameOver) {
      dir = (1,0)
      model.resetSnake
      model.resetApple
    }

    model.move(dir)
  }

  override def keyPressed(e: KeyEvent) {
    //change direction
    dir = e.getKeyCode match {
      case VK_LEFT => (-1, 0)
      case VK_RIGHT => (1, 0)
      case VK_UP => (0, -1)
      case VK_DOWN => (0, 1)
      case _ => dir
    } 
  }
  
  override def keyReleased(e: KeyEvent) {}
  override def keyTyped(e: KeyEvent){}
  
  def start {
    timer.start
    model.start
  }  
}

class View(model: SnakeDataModel) extends JPanel with Actor
{
  override def paintComponent(g : Graphics) {
    super.paintComponent(g)

    val scale = Game.Scale
    val apple = model.apple

    //background
    g.setColor(java.awt.Color.LIGHT_GRAY)
    g.fillRect(Game.Width * scale, Game.Height * scale, 0, 0)

    //draw apple
    g.setColor(java.awt.Color.RED)
    g.fillRect(apple._1 * scale, apple._2 * scale, scale, scale)

    //draw snake
    g.setColor(java.awt.Color.GREEN)
    model.snake foreach (seg =>
      g.fillRect(seg._1 * scale, seg._2 * scale, scale, scale)
    )          
  }

  def act = {
    loop {
      react {
        case SnakeMoved => repaint()
      }
    }
  }

  model ! Listen(this)
  start
}