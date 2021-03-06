package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    println("Are parentheses balanced?")
    var list: List[Char] = List('(', '(', ')', '(', ')', ')')
    println(balance(list))
    println("Coins change problem")
    var listOfCoins: List[Int] = List(2, 3, 1)
    var sum = 10
    print("Need get " + sum + " using ")
    listOfCoins.foreach(el => print(el + " "))
    println()
    println("Count of way is " + countChange(sum, listOfCoins))
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if ((c == r) || (c == 0))
    {
      1
    }
    else
    {
      pascal(c, r - 1) + pascal(c - 1, r - 1)
    }
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def countParentheses(chars: List[Char], count: Int): Boolean = {
      if (chars.isEmpty)
      {
        count == 0
      }
      else if (chars.head == '(')
      {
        countParentheses(chars.tail, count + 1)
      }
      else if (chars.head == ')')
      {
        (count > 0) && (countParentheses(chars.tail, count - 1))
      }
      else countParentheses(chars.tail, count)
    }
    countParentheses(chars, 0)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomination
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
   if (money == 0)
   {
     1
   }
   else if ((money < 0) || (coins.isEmpty))
   {
     0
   }
   else
   {
     countChange(money - coins.head, coins) + countChange(money, coins.tail)
   }
  }
}
