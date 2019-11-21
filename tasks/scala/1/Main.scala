object Main extends App {

  println("Pascal's Triangle")
  for (row <- 0 to 10) {
    for (col <- 0 to row)
      print(pascal(col, row) + " ")
    println()
  }

  println("Balancing")
  val chars: List[Char] = List('a', 'b', '(', ')')
  println(balance(chars))

  println("Counting change")
  println(countChange(5, List(5)))

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
    else pascal(c, r - 1) + pascal(c - 1, r - 1)
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    chars.count(_ == '(') == chars.count(_ == ')')
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */

  def countChange(money: Int, coins: List[Int]): Int = {
    if (coins.isEmpty) 0
    else if (money - coins.head == 0) 1
    else if (money - coins.head < 0) 0
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}
