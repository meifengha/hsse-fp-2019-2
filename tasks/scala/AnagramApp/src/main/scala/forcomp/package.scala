import java.io.{BufferedWriter, FileWriter}

import forcomp.Anagrams.Word

package object forcomp {
  val dictionaryPath = List("forcomp", "linuxwords.txt")

  def loadDictionary = {
    val wordstream = Option {
      getClass.getClassLoader.getResourceAsStream(dictionaryPath.mkString("/"))
    } orElse {
      common.resourceAsStreamFromSrc(dictionaryPath)
    } getOrElse {
      sys.error("Could not load word list, dictionary file not found")
    }
    try {
      val s = io.Source.fromInputStream(wordstream)
      s.getLines.toList
    } catch {
      case e: Exception =>
        println("Could not load word list: " + e)
        throw e
    } finally {
      wordstream.close()
    }
  }

  def addWord(word: Word): Unit = {
    val writer : BufferedWriter = new BufferedWriter(new FileWriter("/home/l0gark/Desktop/hsse-fp-2019-2/hsse-fp-2019-2/tasks/scala/AnagramApp/src/main/resources/forcomp/linuxwords.txt", true))
    writer.append(word + "\n")
    writer.close()
  }
}

import java.io.File


package object common {

  /** An alias for the `Nothing` type.
   * Denotes that the type should be filled in.
   */
  type ??? = Nothing

  /** An alias for the `Any` type.
   * Denotes that the type should be filled in.
   */
  type *** = Any


  /**
   * Get a child of a file. For example,
   *
   * subFile(homeDir, "b", "c")
   *
   * corresponds to ~/b/c
   */
  def subFile(file: File, children: String*) = {
    children.foldLeft(file)((file, child) => new File(file, child))
  }

  /**
   * Get a resource from the `src/main/resources` directory. Eclipse does not copy
   * resources to the output directory, then the class loader cannot find them.
   */
  def resourceAsStreamFromSrc(resourcePath: List[String]): Option[java.io.InputStream] = {
    val classesDir = new File(getClass.getResource(".").toURI)
    val projectDir = classesDir.getParentFile.getParentFile.getParentFile.getParentFile
    val resourceFile = subFile(projectDir, ("src" :: "main" :: "resources" :: resourcePath): _*)
    if (resourceFile.exists)
      Some(new java.io.FileInputStream(resourceFile))
    else
      None
  }
}