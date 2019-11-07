package recfun

object Main {
  def main(args: Array[String]): Unit = {
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
    if ((c == 0) || (c == r)) {
      1
    } else {
      pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def isBalanced(chars: List[Char], num/*number of open brackets*/: Int): Boolean = {
      if (chars.isEmpty) num == 0
      else if (chars.head == '(')
        isBalanced(chars.tail, num + 1)
      else if (chars.head == ')')
        (num > 0) && isBalanced(chars.tail, num - 1)
      else isBalanced(chars.tail, num)
    }
    isBalanced(chars,0)
  }

  /**
   * Exercise 3 Counting Change
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) {
      1;
    } else if ((money < 0) || coins.isEmpty) {
      0;
    } else {
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }
  }
}