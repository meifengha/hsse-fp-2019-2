package recfun
object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    println(countChange(10, List(3, 2, 5)));
    println(List(3, 2, 5).tail);
    println(balance("(a + (b * c + (n * k)))".toList));
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
      pascal(c - 1, r -1) + pascal(c, r - 1)
    }
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def balanceIterator(position: Int, count: Int): Boolean = {

      if (position == chars.length)
        count == 0
      else if (chars(position) == '(')
        balanceIterator(position + 1, count + 1)
      else if (chars(position) == ')')
        if (count == 0)
          false
        else
          balanceIterator(position + 1, count - 1)
      else
        balanceIterator(position + 1, count)
    }
    balanceIterator(0, 0)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (coins.contains(0))
    {
      0;
      // infinity loop
    }
    if (money == 0) {
      1;
    }
    else if (money < 0 || coins.isEmpty)
    {
      0;
    }
    else {
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }
  }
}
