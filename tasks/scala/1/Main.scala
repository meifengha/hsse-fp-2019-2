package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = (c, r) match {
    case (0, _)           => 1
    case (x, y) if x == y => 1
    case (x, y)           => pascal(c, r - 1) + pascal(c - 1, r - 1)
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def helper (chars: List[Char], balance: Int): Boolean = (chars, balance) match {
      case (Nil, 0)           => true
      case (Nil, _)           => false
      case (_, b) if b < 0    => false
      case ('(' :: tail, b)   => helper(tail, b + 1)
      case (')' :: tail, b)   => helper(tail, b - 1)
      case (_ :: tail, b)     => helper(tail, balance)
    }
    helper(chars, 0)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = (money, coins) match {
    case (0, _)          => 1
    case (x, _) if x < 0 => 0
    case (_, Nil)        => 0
    case (x, h :: t)     => countChange(x - h, h :: t) + countChange(x, t)
  }
}
