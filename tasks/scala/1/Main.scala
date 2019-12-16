package recfun
import common._

object Main {
  def main(args: Array[String]): Unit = {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }

    println("Parentheses Balancing")
    val inputChars: List[Char] = scala.io.StdIn.readLine().toList
    println(balance(inputChars))
    println()

    println("Counting Change")
    println("Enter money")
    val money: Int = scala.io.StdIn.readInt()
    println("Enter coins")
    val coins: List[Int] = scala.io.StdIn.readLine().filter(_!='\n').split(' ').map(_.toInt).toList
    println(countChange(money, coins))
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if ((c == r) || (c == 0)) {
      1
    } else {
      pascal(c, r - 1) + pascal(c - 1, r - 1)
    }
    if ((c == 0) || (r == 0) || (c == r)) { 1 }
    else { pascal(c - 1, r - 1) + pascal(c, r - 1) }
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def check(chars: List[Char], count: Int): Boolean = {
      if (chars.isEmpty) {
        count == 0
      } else if (chars.head == '(') {
        check(chars.tail, count + 1)
      } else if (chars.head == ')') {
        (count > 0) && check(chars.tail, count - 1)
      } else {
        check(chars.tail, count)
      }
    var countOpenParenthesis: Int = 0
    var countCloseParenthesis: Int = 0
    for (i <- 0 until chars.length) {
      if (chars(i) == '(') { countOpenParenthesis += 1 }
      if (chars(i) == ')') { countCloseParenthesis += 1 }
    }

    check(chars, 0)
    if (countOpenParenthesis == countCloseParenthesis) { true }
    else { false }
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) {
      1;
    } else if ((money < 0) || coins.isEmpty) {
      0;
    } else {
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }
    if ((coins.isEmpty) || (money < 0)) { 0 }
    else if (money == 0) { 1 }
    else { countChange(money, coins.tail) + countChange(money - coins.head, coins) }
  }
}
