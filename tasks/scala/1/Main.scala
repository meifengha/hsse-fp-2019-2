//Tolstikov Grigoriy

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

  /**
   * Exercise 1
   */
  // C(k/n) = C((k-1)/n) + C((k-1)/(n-1))
  def pascal(c: Int, r: Int): Int = if ((c == 0) || (c == r)) 1 else pascal(c, r - 1) + pascal(c - 1, r - 1)

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {

  }

  /**
   * Exercise 3 Counting Change
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) {
      1
    } else if (coins.isEmpty || (money < 0)) {
      0
    } else {
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }
  }
}
