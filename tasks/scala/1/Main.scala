package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    println(balance(List('(', '(', 'c', 'o', 'r', 'r', 'e', 'c', 't', ')', 'l', 'i', 's', 't', ')')))
    println(balance(List('(', ')', 'w', 'r', 'o', 'n', 'g', '(')
    println(countChange(6, List(1, 2)))
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      def rowCount(x: Int, k: Int): Int =
        if (k == c + 1) x else rowCount(x*(r + 1 - k)/k, k - 1)
      rowCount(1, 1)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def countBrackets(chars: List[Char], count: Int): Boolean = {
        if (chars.isEmpty) count == 0 else {
          if (chars.head == '(')
            countBrackets(chars.tail, count + 1)
          else if (chars.head == ')')
            (count >= 0) && (countBrackets(chars.tail, count - 1))
          else
            countBrackets(chars.tail, count)
         }
      }
      countBrackets(chars, 0)
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      if (money == 0) {
        1
      } else if ((money < 0) || coins.isEmpty) {
        0
      } else {
        countChange(money - coins.head, coins) + countChange(money, coins.tail)
      }
    }
  }
