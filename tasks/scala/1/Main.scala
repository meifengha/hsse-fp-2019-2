package recfun

object Main {
  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (y <- 0 to 10) {
      for (x <- 0 to y)
        print(pascal(x, y) + " ")
      println()
    }
    
    println("\nParentheses Balancing ")
    println(balance("()(())()".toCharArray.toList))
    println(balance("()(()()".toCharArray.toList))
    
    println("\nCoins change problem")
    println(countChange(5, List(1, 2, 3)))
    
  }

  /**
   * Exercise 1
   */ 
  def pascal(x: Int, y: Int): Int = {
    if ((x == 0) || (x == y))  1; 
    else 
    {
      pascal(x, y - 1) + pascal(x - 1, y - 1);
    }
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def check(chars: List[Char], count: Int): Boolean = {
      if (chars.isEmpty) 
      {
        count == 0
      } 
      else if (chars.head == '(') 
      {
        check(chars.tail, count + 1)
      } 
      else if (chars.head == ')') 
      {
        (count > 0) && check(chars.tail, count - 1)
      } 
      else 
      {
        check(chars.tail, count)
      }
    }

    check(chars, 0)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0)  1;
    else 
    if ((money < 0) || coins.isEmpty) 0;
    else 
    {
      countChange(money - coins.head, coins) + countChange(money, coins.tail);
    }
  }
}