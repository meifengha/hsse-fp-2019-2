import forcomp.Anagrams.{Sentence, Word}
import javafx.beans.property.DoubleProperty
import javafx.event.EventHandler
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.event.ActionEvent
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TextField}
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{HBox, StackPane, VBox}
import scalafx.scene.paint.Color.{Black, Cyan, DodgerBlue, PaleGreen, SeaGreen}
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text
import scalafx.Includes._
import forcomp._
import scalafx.beans.property.StringProperty

object App extends JFXApp {
  val SCREEN_WIDTH = 800
  val DEF_PADDING = 20
  val SCREEN_HEIGHT: Double = SCREEN_WIDTH / 2


  stage = new JFXApp.PrimaryStage {
    title = "Test-Program"

    scene = new Scene(SCREEN_WIDTH, SCREEN_HEIGHT) {

      val fieldSentence: TextField = new TextField {
        minWidth = SCREEN_WIDTH / 2 - DEF_PADDING * 2
      }

      val labelAnswer: Label = new Label("Answers will be here.") {
        minWidth = SCREEN_WIDTH / 2 - DEF_PADDING * 2
      }

      val buttonSubmitSentence: Button = new Button("Enter") {

      }

      buttonSubmitSentence.onAction = handle {
        val sentence: String = fieldSentence.text.toString()
        val words: Array[Word] = sentence.split(" ")
        //        val answerLines: List[Sentence] = Anagrams.sentenceAnagrams(words.toList)
        //        val sb : StringBuilder = new StringBuilder
        //        answerLines.foreach(v => sb.append(v).append("\n"))
        println(sentence)
        //        labelAnswer.text = sb.toString()
      }

      fill = SeaGreen

      content = new HBox(DEF_PADDING) {
        padding = Insets(DEF_PADDING)

        children = Seq(
          new VBox(DEF_PADDING) {
            minWidth = SCREEN_WIDTH / 2

            children = Seq(
              fieldSentence,
              labelAnswer
            )
          },
          new VBox() {
            minWidth = SCREEN_WIDTH / 2

            children = buttonSubmitSentence
          }

        )

      }
    }
  }
}
