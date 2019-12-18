package forcomp

import common._
import scala.collection.immutable._

object Anagrams {

  type Word = String
  type Sentence = List[Word]
  type Occurrences = List[(Char, Int)]


  val dictionary: List[Word] = loadDictionary

  def wordOccurrences(w: Word): Occurrences = {
    val unsorted = (w.toLowerCase groupBy identity) map { case (c,cs) => (c, cs.length) }

    (SortedMap[Char,Int]() ++ unsorted) toList
  }

  def sentenceOccurrences(s: Sentence): Occurrences = wordOccurrences( s.mkString )

  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = dictionary groupBy wordOccurrences

  def wordAnagrams(word: Word): List[Word] = dictionaryByOccurrences getOrElse (wordOccurrences(word), Nil)

  def combinations(occurrences: Occurrences): List[Occurrences] =
    (occurrences foldRight List[Occurrences](Nil)) { case ((ch,tm), acc) => {
      acc ++ ( for { comb <- acc; n <- 1 to tm } yield (ch, n) :: comb )
    } }

  def subtract(x: Occurrences, y: Occurrences): Occurrences = {
    (y foldLeft SortedMap[Char,Int]() ++ x){ case (map, (ch, tm)) => {
      val newTm = map(ch) - tm
      if (newTm != 0) map updated (ch, newTm)
      else map - ch
    } }.toList
  }

  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
    def iter(occurrences: Occurrences): List[Sentence] = {
      if (occurrences.isEmpty) List(Nil)
      else for {
        combination <- combinations( occurrences )
        word <- dictionaryByOccurrences getOrElse (combination, Nil)
        sentence <- iter( subtract(occurrences,wordOccurrences(word)) )
        if !combination.isEmpty
      } yield word :: sentence
    }

    iter( sentenceOccurrences(sentence) )
  }

}
