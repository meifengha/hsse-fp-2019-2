//Tolstikov Grigoriy

package recfun

object Main {
  def main(args: Array[String]) {
    println("\n*****EXERCISE1*****\n")
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }


    println("\n*****EXERCISE2*****\n")
    val trueString1 = "sa1233(wq)(((aaczarq1)))cz"
    val trueString2 = "12431"
    val trueString3 = "(12341yadqz)"
    val falseString1 = ")("
    val falseString2 = "11(1.c1z2))z"
    val falseString3 = "11((1.c1z2)z"

    println("True:")
    println(balance(trueString1.toList), balance(trueString2.toList), balance(trueString3.toList))
    println("False:")
    println(balance(falseString1.toList), balance(falseString2.toList), balance(falseString3.toList))

    println("\n*****EXERCISE3*****\n")
    val coins1: List[Int] = List(1, 2, 5, 10)
    val coins2: List[Int] = List(2, 3)

    println("coins: " + coins1)
    var money = 5
    println("money: " + money + "\nways to change: " + countChange(money, coins1))
    money = 0  // we can exchange zero in one way - given nothing
    println("money: " + money + "\nways to change: " + countChange(money, coins1))
    money = 123
    println("money: " + money + "\nways to change: " + countChange(money, coins1) + "\n")

    println("coins: " + coins2)
    money = 5
    println("money: " + money + "\nways to change: " + countChange(money, coins2))
    money = 1
    println("money: " + money + "\nways to change: " + countChange(money, coins2))
    money = 11
    println("money: " + money + "\nways to change: " + countChange(money, coins2))
    money = 34
    println("money: " + money + "\nways to change: " + countChange(money, coins2) + "\n")
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
    def balanceCheck(chars: List[Char], count : Int): Boolean = {
      if (chars.isEmpty) count == 0
      else if (chars.head == '(') balanceCheck(chars.tail, count + 1)
      else if (chars.head == ')') {
       if (count <= 0) false else balanceCheck(chars.tail, count - 1)
      } else balanceCheck(chars.tail, count)
    }

    balanceCheck(chars, 0)
  }

  /**
   * Exercise 3 Counting Change
   */

  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (coins.isEmpty || (money < 0)) 0
    else countChange(money, coins.tail) + countChange(money - coins.head, coins)
  }
}
