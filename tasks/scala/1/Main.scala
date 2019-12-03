package recfun
import common._

object Main {
  def main(args: Array[String]): Unit = {

    /* Task 1 */
    println("Pascal's Triangle")
    for {
      row <- 0 to 10
      col <- 0 to row
    }
      yield {
        if (col == 0) println()
        print(pascal(col, row) + " ")
      }
  }

  /* Task 2 */
  println("Parentheses Balancing")
  val inputChars: List[Char] = scala.io.StdIn.readLine().toList
  println(balance(inputChars))
  println()

  /* Task 3 */
  println("Enter amount of money")
  val money = scala.io.StdIn.readLine().toInt
  println("Enter available coins")
  val input = scala.io.StdIn.readLine()
  val moneyList = input.filter(_ != '$').split(' ').map(_.toInt).toList
  println(countChange(money, moneyList))


  /**
   * Exercise 1
   */

  def pascal(c: Int, r: Int): Int = {
    if ((c == 0) || (c == r)) 1
    else {
      pascal(c, r - 1) + pascal(c - 1, r - 1)
    }
  }

  /**
   * Exercise 2 Parentheses Balancing
   */

  def balance(chars: List[Char]): Boolean = {
    def balanceCheck(chars: List[Char], count : Int): Boolean = {
      if (chars.isEmpty) count == 0
      else if (chars.head == '(') balanceCheck(chars.tail, count + 1)
      else if (chars.head == ')') {
       if (count <= 0) false else balanceCheck(chars.tail, count - 1)
      } else balanceCheck(chars.tail, count)
    }

    balanceCheck(chars, 0)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */

  def countChange(money: Int, coins: List[Int]): Int = {
    if (money < 0 || coins.isEmpty) 0
    else if (money == 0) 1
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}
