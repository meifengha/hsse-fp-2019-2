import forcomp.Anagrams
import forcomp.Anagrams.{Sentence, Word}
import scalafx.Includes.handle
import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.ScrollPane.ScrollBarPolicy
import scalafx.scene.control.{Button, Label, ScrollPane, TextField}
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color.SeaGreen

object App extends JFXApp {
  val SCREEN_WIDTH = 800
  val DEF_PADDING = 20
  val SCREEN_HEIGHT: Double = SCREEN_WIDTH / 2
  val ANSWER_PLACEHOLDER: String = "Answers will be here."


  stage = new JFXApp.PrimaryStage {
    title = "Anagram Application"

    scene = new Scene(SCREEN_WIDTH, SCREEN_HEIGHT) {

      val fieldSentence: TextField = new TextField {
        promptText = "Type sentence here"
        minWidth = SCREEN_WIDTH / 2 - DEF_PADDING * 2
      }


      val labelAnswer: Label = new Label(ANSWER_PLACEHOLDER) {
        minWidth = SCREEN_WIDTH / 2 - DEF_PADDING * 2
      }

      val scrollPane: ScrollPane = new ScrollPane {
        content = labelAnswer
        vbarPolicy = ScrollBarPolicy.Always
        maxHeight = SCREEN_HEIGHT / 2
        minHeight = SCREEN_HEIGHT / 2
      }

      val buttonClearField: Button = new Button("Clear") {
        onAction = handle {
          fieldSentence.text = ""
          labelAnswer.text = ANSWER_PLACEHOLDER
          visible = false
        }

        visible = false
      }

      val buttonSubmitSentence: Button = new Button("Enter") {
        onAction = handle {
          val sentence: String = fieldSentence.text.value
          val words: Array[Word] = sentence.split(" ")
          val answerLines: List[Sentence] = Anagrams.sentenceAnagrams(words.toList)
          val sb: StringBuilder = new StringBuilder
          answerLines.foreach(line => {
            line.foreach(word => {
              sb.append(word).append(" ")
            })
            sb.append("\n")
          })
          labelAnswer.text = sb.toString()

          buttonClearField.setVisible(true)
        }
      }

      val dictionaryField: TextField = new TextField {
        minWidth = 250
        promptText = "Type word to add to dictionary"
      }

      val buttonAddDictionary: Button = new Button("Edit Dictionary") {
        onAction = handle {
          val mytext = dictionaryField.text.value
          forcomp.addWord(mytext)
        }
      }

      fill = SeaGreen

      content = new HBox(DEF_PADDING) {
        padding = Insets(DEF_PADDING)

        children = Seq(
          new VBox(DEF_PADDING) {
            minWidth = SCREEN_WIDTH / 2

            children = Seq(
              fieldSentence,
              scrollPane,
              buttonClearField
            )
          },
          buttonSubmitSentence,
          new VBox(DEF_PADDING) {
            minWidth = 200

            children = Seq(
              dictionaryField,
              buttonAddDictionary
            )
          }
        )
      }
    }
  }
}
