package recfun
import common._

object Main {
  def main(args: Array[String]) {
    try {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    
      println("Parentheses balancing")
      println("Enter string: ")
      val string: String = scala.io.StdIn.readLine()
      println(balance(string.toList));

      println("Counting Change")
      println("Enter amount of money: ")
      val money: Int = scala.io.StdIn.readInt()

      println("Enter combination of coins: ")
      val stringCoins: String = scala.io.StdIn.readLine()
      val listCoins: List[Int] = stringCoins.filter(_ != '\n').split(' ').map(_.toInt).toList
      println(countChange(money, listCoins));

    } catch {
      case e: Exception => println("Exception caught: " + e)
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if ((c < 0) || (r < 0)) {
      throw new IllegalArgumentException("Invalid arguments")
    }
    if ((c == 0) || (c == r)) {
      1
    } else {
      pascal(c, r - 1) + pascal(c - 1, r - 1)
    }
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def loop(chars: List[Char], balance: Int): Boolean = {
      if (chars.isEmpty) {
        balance == 0
      } else if (chars.head == '(') {
        loop(chars.tail, balance + 1)
      } else if (chars.head == ')') {
        (balance > 0) && loop(chars.tail, balance - 1)
      } else {
        loop(chars.tail, balance)
      }
    }

    loop(chars, 0)   
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    case (_, x::_) if (x == 0)  => throw new IllegalArgumentException("Coin can not be equal 0");
    case (_, Nil)               => 0
    case (tmp, _) if tmp < 0    => 0
    case (0, _)                 => 1
    case (tmp, _)               => countChange(tmp, coins.tail) + countChange(tmp - coins.head, coins)
  }
}
