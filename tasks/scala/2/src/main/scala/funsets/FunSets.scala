package funsets

import common._

object FunSets {
  type Set = Int => Boolean

  def contains(s: Set, elem: Int): Boolean = s(elem)

  def singletonSet(elem: Int): Set = {
    (x: Int) => x == elem
  }

  def union(s: Set, t: Set): Set = {
    (x: Int) => (s(x) || t(x))
  }

  def intersect(s: Set, t: Set): Set = {
    (x: Int) => (s(x) && t(x))
  }

  def diff(s: Set, t: Set): Set = {
    (x: Int) => (s(x) && !t(x))
  }

  def filter(s: Set, p: Int => Boolean): Set = {
    (x: Int) => (s(x) && p(x))
  }

  val bound = 1000

  def forall(s: Set, p: Int => Boolean): Boolean = {
    @scala.annotation.tailrec
    def iter(a: Int): Boolean = {
      if (a > bound) true
      else if (contains(s, a)) p(a) && iter(a+1)
      else iter(a + 1)
    }
    iter(-1000)
  }

  def exists(s: Set, p: Int => Boolean): Boolean = {
    !forall(s, x => !p(x))
  }

  def map(s: Set, f: Int => Int): Set = {
    (y: Int) => exists(s, x => f(x) == y)
  }

  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  def printSet(s: Set) {
    println(toString(s))
  }
}
