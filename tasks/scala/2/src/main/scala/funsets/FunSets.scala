  /**
   * Returns the set of the one given element.
   */
    def singletonSet(elem: Int): Set = ???
    def singletonSet(elem: Int): Set = (tmp: Int) => tmp == elem


  /**
   * Returns the union of the two given sets,
   * the sets of all elements that are in either `s` or `t`.
   */
    def union(s: Set, t: Set): Set = ???

    def union(s: Set, t: Set): Set =
      (tmp: Int) => contains(s, tmp) || contains(t, tmp)

  /**
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` and `t`.
   */
    def intersect(s: Set, t: Set): Set = ???

    def intersect(s: Set, t: Set): Set =
      (tmp: Int) => contains(s, tmp) && contains(t, tmp)

  /**
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
    def diff(s: Set, t: Set): Set = ???
    def diff(s: Set, t: Set): Set =
      (tmp: Int) => contains(s, tmp) && !contains(t, tmp)

  /**
   * Returns the subset of `s` for which `p` holds.
   */
    def filter(s: Set, p: Int => Boolean): Set = ???
    def filter(s: Set, p: Int => Boolean): Set = intersect(s, p)


  /**
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  val bound = 1000
  /**
   * Returns whether all bounded integers within `s` satisfy `p`.
   */
    def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (???) ???
      else if (???) ???
      else iter(???)
      if (a < -bound) true
      else if (contains(s, a) && !p(a)) false
      else iter(a - 1)
    }
    iter(???)
    iter(bound)
  }

  /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */
    def exists(s: Set, p: Int => Boolean): Boolean = ???
    def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, (tmp: Int) => !p(tmp))

  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   */
    def map(s: Set, f: Int => Int): Set = ???
    def map(s: Set, f: Int => Int): Set =
      (elem: Int) => exists(s, (tmp: Int) => f(tmp) == elem)

  /**
   * Displays the contents of a set
   */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }
  /**
   * Prints the contents of a set on the console.
   */
  def printSet(s: Set) {
    println(toString(s))
  }
}
} 