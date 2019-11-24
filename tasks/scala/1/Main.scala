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
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 | c == r)
    {
      1
    } else {
      pascal(c-1, r-1) + pascal(c, r-1)
    }
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def subFunc(chars: List[Char], opens: Int): Boolean = {
      if (chars.isEmpty) {
        opens == 0
      }
      else if (chars.head == '(') {
        subFunc(chars.tail, opens + 1)
      }
      else if (chars.head == ')' && opens > 1) {
        subFunc(chars.tail, opens - 1)
      }
      else if (opens >= 0) {
        subFunc(chars.tail, opens)
      }
      else {
        false
      }
    }

    subFunc(chars, 0)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    var amount = 0;
    def subFunc(money: Int, coins: List[Int]): Any = {
      if (!coins.isEmpty) {
        if (money > coins.head) {
          subFunc(money - coins.head, coins)
          subFunc(money, coins.tail)
        }
        else if (money < coins.head) {
          subFunc(money, coins.tail)
        }
        else if (money-coins.head == 0) {
          amount += 1
        }
      }
    }

    subFunc(money, coins.sorted)
    amount
  }
}
