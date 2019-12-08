package forcomp

object Anagrams {

  type Word = String

  type Sentence = List[Word]

  type Occurrences = List[(Char, Int)]

  val dictionary: List[Word] = loadDictionary

  def wordOccurrences(w: Word): Occurrences =
      w.toLowerCase.toList.groupBy(identity).map{ case (ch, seq) => (ch, seq.length) }.toList.sorted

  def sentenceOccurrences(s: Sentence): Occurrences = wordOccurrences(s.mkString)

  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = dictionary.groupBy(wordOccurrences)

  def wordAnagrams(word: Word): List[Word] = dictionaryByOccurrences(wordOccurrences(word))

  def combinations(occurrences: Occurrences): List[Occurrences] = occurrences match {
    case Nil => List(List())
    case x :: xs => for {y <- combinations(xs); n <- 0 to x._2}
      yield (if (n == 0) y else (x._1, n) :: y)
  }

  def subtract(x: Occurrences, y: Occurrences): Occurrences = x.filterNot(y.contains)

  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
    def sentenceIter(occurrences: Occurrences): List[Sentence] = occurrences match {
      case List() => List(List())
      case _ => {
        for {
          c <- combinations(occurrences) if dictionaryByOccurrences.contains(c)
          w <- dictionaryByOccurrences(c)
          y <- sentenceIter(subtract(occurrences, c))
        } yield w :: y
      }
    }

    sentenceIter(sentenceOccurrences(sentence))
  }
}
