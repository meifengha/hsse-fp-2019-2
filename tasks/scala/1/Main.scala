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
    if (c == 0 || c == r)
    {
      1
    }
    else
    {
      pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def balancing(position: Int, openBraces: Int): Boolean = {
      if (position == chars.length)
        openBraces == 0
      else if (chars(position) == '(')
        balancing(position + 1, openBraces + 1)
      else if  (chars(position) == ')')
        if (openBraces == 0)
          false
        else
          balancing(position + 1, openBraces - 1)
      else
        balancing(position + 1, openBraces)
    }
    
    balancing(0, 0)
  }

  /**
   * Exercise 3 Counting Change
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0)
    {
      1;
    }
    else if ((money < 0) || coins.isEmpty)
    {
      0;
    }
    else
    {
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }
  }
}
