import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage

import javafx.application.{Application, Platform}
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.embed.swing.SwingFXUtils
import javafx.scene.image.WritableImage
import javafx.scene.SnapshotParameters
import javafx.scene.control.{Button, Label}
import javafx.scene.shape.Line
import javafx.scene.transform.Transform
import org.nd4j.linalg.factory.Nd4j

import scala.collection.mutable.ArrayBuffer


object MainApp {
  def main(args: Array[String]): Unit = {
    if (args.length != 0 && args(0).equals("--train")) NeuralNetwork.train()
    else Application.launch(classOf[MainApp], args: _*)
  }
}

class MainApp extends Application {
  override def start(primaryStage: Stage): Unit = {
    val loadingLabel = new Label("Network is loading")
    AnchorPane.setTopAnchor(loadingLabel, 175.0)
    AnchorPane.setLeftAnchor(loadingLabel, 125.0)

    val label = new Label("Prediction:")
    AnchorPane.setTopAnchor(label, 100.0)
    AnchorPane.setLeftAnchor(label, 450.0)

    val prediction = new Label
    AnchorPane.setTopAnchor(prediction, 100.0)
    AnchorPane.setLeftAnchor(prediction, 550.0)

    val line = new Line(400.0, 0.0, 400.0, 400.0)

    val canvas = new Canvas(400, 400)
    AnchorPane.setLeftAnchor(canvas, 0.0)
    val graphicsContext = canvas.getGraphicsContext2D
    initDraw(graphicsContext)
    canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler[MouseEvent]() {
      override def handle(event: MouseEvent): Unit = {
        graphicsContext.beginPath()
        graphicsContext.moveTo(event.getX, event.getY)
        graphicsContext.stroke()
      }
    })
    canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler[MouseEvent]() {
      override def handle(event: MouseEvent): Unit = {
        graphicsContext.lineTo(event.getX, event.getY)
        graphicsContext.stroke()
      }
    })
    canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler[MouseEvent]() {
      override def handle(event: MouseEvent): Unit = {
        val wi = new WritableImage(28, 28)
        val spa = new SnapshotParameters
        spa.setTransform(Transform.scale(28.0 / 400, 28.0 / 400))
        val bi = SwingFXUtils.fromFXImage(canvas.snapshot(spa, wi), null)
        val nbi = new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB)
        nbi.createGraphics().drawImage(bi, 0, 0, java.awt.Color.WHITE, null)
        prediction.setText(NeuralNetwork.predict(Nd4j.create(preProcess(nbi, 28))).toString)
      }
    })

    val clearButton = new Button("Clear")
    AnchorPane.setTopAnchor(clearButton, 200.0)
    AnchorPane.setLeftAnchor(clearButton, 450.0)

    clearButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler[MouseEvent] {
      override def handle(event: MouseEvent): Unit = {
        graphicsContext.clearRect(0.0, 0.0, 400, 400)
        prediction.setText("")
      }
    })

    val root = new AnchorPane
    root.getChildren.addAll(canvas, label, line, prediction, clearButton, loadingLabel)
    val scene = new Scene(root, 600, 400)
    primaryStage.setScene(scene)
    primaryStage.setTitle("Digit recognition")
    primaryStage.show()

    Platform.runLater(new Runnable {
      override def run(): Unit = {
        NeuralNetwork.loadWeights
        root.getChildren.remove(loadingLabel)
      }
    })
  }


  private def initDraw(gc: GraphicsContext): Unit = {
    gc.setFill(Color.WHITE)
    gc.setStroke(Color.BLACK)
    gc.setLineWidth(25)
  }

  private def preProcess(image: BufferedImage, size: Int): Array[Double] = {
    val imageArray = Array.ofDim[Double](size, size)

    val rotatedImage = new BufferedImage(size, size, image.getType)
    val g = rotatedImage.createGraphics()
    g.rotate(Math.toRadians(90), size / 2, size / 2)

    g.drawImage(image, 0, size, size, -size, null)

    val finalImage = new ArrayBuffer[Double]()

    var xtotal, ytotal = 0d
    var num = 0d

    for (x <- Range(0, size)) {
      for (y <- Range(0, size)) {
        val p = rotatedImage.getRGB(x, y)
        val a = 255 - ((p >> 24) & 0xff)
        val r = 255 - ((p >> 16) & 0xff)
        val g = 255 - ((p >> 8) & 0xff)
        val b = p & 0xff


        rotatedImage.setRGB(x, y, ((a << 24) | (r << 16) | (g << 8) | b))
        xtotal += x * r
        ytotal += y * r
        num += r
      }
    }

    val com_1 = (xtotal / num, ytotal / num)

    val translatedImage = new BufferedImage(size, size, image.getType)
    val g_translate = translatedImage.createGraphics()

    val translate = ((14 - com_1._1) / 2, (14 - com_1._2) / 2)

    val tx: AffineTransform = new AffineTransform
    tx.translate(translate._1, translate._2)
    g_translate.setTransform(tx)
    g_translate.drawImage(rotatedImage, tx, null)


    xtotal = 0d
    ytotal = 0d
    num = 0d

    for (x <- Range(0, size)) {
      for (y <- Range(0, size)) {
        val colour = translatedImage.getRGB(x, y)
        val r = ((colour & 0xff0000) >> 16) / 255d
        finalImage.append(
          if (r < 0.02) 0
          else r
        )
        xtotal += x * r
        ytotal += y * r
        num += r
      }
    }

    val com_2 = (Math.round(xtotal / num), Math.round(ytotal / num))

    finalImage.toArray
  }
}
