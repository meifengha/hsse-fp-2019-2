object Main {

  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (y <- 0 to 10) {
      for (x <- 0 to y)
        print(pascal(x, y) + " ")
      println()
    }

    println(balance("()(())()".toCharArray.toList))
    println(balance("()(()()".toCharArray.toList))

    println(countChange(7, List(1, 2, 3)))
  }

  /**
   * Exercise 1
   */
  def pascal(x: Int, y: Int): Int = {
    if (x == 0 || x == y) {
      return 1
    }

    pascal(x - 1, y - 1) + pascal(x, y - 1)
  }

  /**
   * Exercise 2 Parentheses Balancing
   */

  def balance(chars: List[Char]): Boolean = {
    @scala.annotation.tailrec
    def balanceIt(position: Int, count: Int): Boolean = {

      if (position == chars.length) {
        count == 0
      }
      else if (chars(position) == '(') {
        balanceIt(position + 1, count + 1)
      }
      else if (chars(position) == ')') {
        if (count == 0) {
          false
        }
        else {
          balanceIt(position + 1, count - 1)
        }
      }
      else {
        balanceIt(position + 1, count)
      }
    }

    balanceIt(0, 0)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */

  def countChange(money: Int, coins: List[Int]): Int = {
    if (coins.contains(0) || money < 0 || coins.isEmpty) {
      return 0
    }

    if (money == 0) {
      return 1
    }

    countChange(money, coins.tail) + countChange(money - coins.head, coins)
  }

}
