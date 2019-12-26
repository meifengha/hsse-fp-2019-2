// Reverse Polish notation calculator without parenthesis priority

// How to use:
// run "1.7 6.9 +"
// run "2.1 2.9 + 6.6 -"

object Main
{
  def main (args: Array[String])
  {
    if (args(0) != "")
    {
      Calculator.apply(args(0).split(" "))
    }
    else
    {
      println("Error. No arguments")
    }
  }
}