package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }
  val list_ex2:List[Char] = List('(', '(', ')', '!', ')', '(', ')')
  println(balance(list_ex2))
  val list_ex3:List[Int] = List(1, 2, 3)
  println(countChange(6, list_ex3))
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
    def balancing(chars: List[Char], open: Int): Boolean = {
      if (chars.isEmpty) {
        open == 0 
      } else if (chars.head == '(') {
        balancing(chars.tail, open + 1)
      } else if (chars.head == ')')
        (open > 0) && balancing(chars.tail, open - 1)
      else {
        balancing(chars.tail, open)
      }
    }
    balancing(chars, 0)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.*/

  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0)
      return 1
    if ((money < 0) || coins.isEmpty)
      return 0
    countChange(money - coins.head, coins.tail) + countChange(money, coins.tail)
  }
}
