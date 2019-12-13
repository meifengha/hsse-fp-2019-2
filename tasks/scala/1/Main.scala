package recfun

object Main {
  def main(args: Array[String]) {
    println("pascal")
    println("for 10 rows")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }

    println("--------------")
    println("balance")
    println("((foo))((bar)(baz))")
    println(balance("((foo))((bar)(baz))".toCharArray.toList))

    println("--------------")
    println("countChange")
    println("money: 5, coins: [2, 3]")
    println(countChange(5, List(2, 3)))
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
    def loop(chars: List[Char], brackets: Int): Boolean = {
      if (chars.isEmpty) {
        brackets == 0
      } else if (chars.head == '(') {
        loop(chars.tail, brackets + 1)
      } else if (chars.head == ')') {
        loop(chars.tail, brackets - 1)
      } else {
        loop(chars.tail, brackets)
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
    if (money == 0) {
      1
    } else if ((money < 0) || (coins.isEmpty)) {
      0
    } else {
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }
  }
}
