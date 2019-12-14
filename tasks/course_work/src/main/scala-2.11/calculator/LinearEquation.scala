package calculator

import scala.util.Try

object LinearEquation {


  def solver(equation : String): String = {

    val equalsSign = equation.indexOf("=")

    val leftPart = equation.substring(0,equalsSign)

    val rightPart = equation.substring(equalsSign+1)

    val leftTokens = TokenParser.getTokens(leftPart)

    val rightTokens = TokenParser.getTokens(rightPart)

    val leftPostfix = ShuntingYard.infixToPostfix(leftTokens)

    val rightPostfix = ShuntingYard.infixToPostfix(rightTokens)


    val leftParams = evaluator(leftPostfix)

    if(leftParams(0) == -1 & leftParams(1) == -1){
      return "Bad expression"
    }

    val rightParams = evaluator(rightPostfix)


    if (rightParams(0) == -1 & rightParams(1) == -1){
      return "Bad expression"
    }

    val resp =  (rightParams(1) - leftParams(1)) / (leftParams(0) - rightParams(0))

    resp.toString

  }


  def evaluator(str: String) :Array[Double]= {


    val stack = new scala.collection.mutable.Stack[Array[Double]]


    val err = Try(str.split(' ').foreach(token =>

      stack.push(


        if(token == "x"){
          Array(1,0)

        }else {

          if (token != "x" & token!="+" & token != "*") {
            Array(0,token.toDouble)
          }else {

            if(token == "+"){


              val first = stack.pop
              val second = stack.pop

              Array(first(0) + second(0),first(1) + second(1))
            }else{

              val first = stack.pop
              val second = stack.pop


              if (first(0) == 0 && second(0) == 0) {
                Array(0,first(1) * second(1))
              }else {
                if (first(0) > 0) {
                  Array(first(0) * second(1),first(1) * second(1))
                }else {
                  Array(first(1) * second(0),first(1) * second(1))
                }
              }

            }

          }
        }
      ))
    )

    if(err.isFailure){
      return Array(-1d,-1d)
    }

    val res = stack.pop

    res


  }


}

