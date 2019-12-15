package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    println("---------------2-------------")
    println("Input string")
    val str:String = scala.io.StdIn.readLine()
    val charList:List[Char] = str.toList
    println(balance(charList))

    println("---------------3-------------")
    println("Input money")
    val money = scala.io.StdIn.readLine().toInt
    println("Input coins")
    val input = scala.io.StdIn.readLine()
    val moneyList = input.filter(_!='\n').split(' ').map(_.toInt).toList
    println(countChange(money, moneyList))
  }

  /**
   * Exercise 1
   */
  def factorial(n: Int): Int = {
    if (n == 0){
      return 1
    }
    else{
      return n * factorial(n - 1)
    }
  }

  def pascal(c: Int, r: Int): Int = {
    return (factorial(r)/(factorial(c)*factorial(r-c)))
  }

  /**
   * Exercise 2 Parentheses Balancing
   */

  def recBalance(chars: List[Char], n:Int): Boolean = {
    if (chars.isEmpty){
      if (n == 0) return true
      else return false
    }
    else{
      if (chars.head == '(') {
        return recBalance(chars.tail, n+1)
      }
      else if (chars.head == ')') {
        if (n == 0){
          return false
        }
        else{
          return recBalance(chars.tail, n-1)
        }
      }
      else {
        return recBalance(chars.tail, n)
      }
    }
  }

  def balance(chars: List[Char]): Boolean = {
    return recBalance(chars, 0)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (coins.isEmpty) return 0
    else if (money == 0) return 1
    else if (money < 0) return 0
    else return countChange(money, coins.tail) + countChange(money - coins.head, coins)
  }
}