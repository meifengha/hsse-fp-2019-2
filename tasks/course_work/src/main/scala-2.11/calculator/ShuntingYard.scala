package calculator

import scala.util.Try

object ShuntingYard {

  def infixToPostfix(infix: Array[String]): String = {

    val ops: String = "-+/*L"
    val sb: StringBuilder = new StringBuilder

    val s = new scala.collection.mutable.Stack[Integer]

    for (token <- infix) {

      if (!token.isEmpty){

        val c: Char = token.charAt(0)

        val idx: Int = ops.indexOf(c)


        if (idx != -1) {
          if (s.isEmpty) {

            s.push(idx)
          }
          else {

            var shouldContinue = true

            while (!s.isEmpty && shouldContinue) {

              val prec2: Int = s.top/2
              val prec1: Int = idx/2

              if (prec2 > prec1 || (prec2 == prec1 && c != 'L')) sb.append(ops.charAt(s.pop)).append(' ')
              else shouldContinue = false
            }
            s.push(idx)
          }
        }

        else if (c == '(') {
          s.push(-40)
        }
        else if (c == ')') {
          val err = Try {
            while (s.top != -40) sb.append(ops.charAt(s.pop)).append(' ')
            s.pop
          }

          if(err.isFailure) return ""
        }
        else {
          sb.append(token).append(' ')
        }
      }
    }

    while (s.nonEmpty) {
      val err = Try {sb.append(ops.charAt(s.pop)).append(' ')}
      if(err.isFailure) return ""
    }


    sb.toString
  }


}

