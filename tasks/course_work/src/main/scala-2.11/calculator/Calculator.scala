package calculator

import scala.util.Try

object Calculator  {




  def calculate(input : String) :String = {

    val tokens = TokenParser.getTokens(input)

    if(tokens.length==0){
      return ""
    }

    if(tokens.contains("x") && tokens.contains("=")) {

      val x  =  LinearEquation.solver(input)

      return(if(x == "Bad expression") "Bad expression" else "x="+x)
    }

    val postfix = ShuntingYard.infixToPostfix(tokens)

    if(postfix==""){
      return("Malformed")
    }

    val res =  rpn(postfix)

    if(res == "Malformed" | res == "error"){
      return (res)
    }

    val numericRes = res.toDouble

    if(numericRes == numericRes.floor){
      return numericRes.toString.replace(".0","")
    }


    numericRes.toString


  }

  def rpn(str: String) :String= {

    val ops2 = Map(
      "+" -> ((_: Double) + (_: Double)),
      "*" -> ((_: Double) * (_: Double)),
      "-" -> ((x: Double, y: Double) => y - x),
      "/" -> ((x: Double, y: Double) => y / x)
    )

    val ops1 = Map(
      "L" -> ((x: Double) =>math.log10(x))
    )

    val stack = new scala.collection.mutable.Stack[Double]


    val err = Try(str.split(' ').foreach(token =>
      stack.push(
        if (ops2.contains(token)) {
          ops2(token)(stack.pop, stack.pop)
        }
        else {
          if (ops1.contains(token)) {
            ops1(token)(stack.pop)
          }else  parse(token)

        }

      )))

    if(!err.isSuccess){

      return "Malformed"
    }

    val res = stack.pop

    if(res.isInfinite){

      return "error"
    }

    if(stack.nonEmpty){

      return "Malformed"
    }

    res.toString

  }
  def parse: String => Double = java.lang.Double.parseDouble


}
