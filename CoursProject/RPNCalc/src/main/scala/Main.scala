// Reverse Polish notation calculator(Without parenthesis priority)
// Usage examples: 
// run "2 3 +"
// run "5 7 + 8 -"
object Main {
  def main (args: Array[String])  {
    if (args(0) != "") {
      Calculator.apply(args(0).split(" "))
    }
    else {
      println("No arguments")
    }
  }
}
