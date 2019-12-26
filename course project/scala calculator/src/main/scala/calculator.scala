import stackQueue.ArrayStack

object Calculator
{
  def apply(args: Seq[String]): Unit =
  {
    val stack = new ArrayStack[Double]{}
    for(arg <- args; if arg.nonEmpty) arg match
    {
      case "+" => stack.push(stack.pop() + stack.pop())
      case "*" => stack.push(stack.pop() * stack.pop())
      case "-" =>
        val tmp = stack.pop()
        stack.push(stack.pop() - tmp)
      case "/" =>
        val tmp = stack.pop()
        stack.push(stack.pop() / tmp)
      case x =>
        stack.push(x.toDouble)
    }
    println(stack.pop())
  }
}