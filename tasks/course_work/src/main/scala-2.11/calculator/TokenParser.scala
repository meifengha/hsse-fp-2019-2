package calculator

import scala.util.matching.Regex

object TokenParser {



  def getTokens(str: String): Array[String] = {


    var replacedStr = str.replaceAll("log","L")


    replacedStr = replacedStr.replaceAll("\\(-","\\(0-")


    val reg = new Regex("[+()*\\/-]|L|x|=|[0-9]*\\.?[0-9]+")

    var resp = reg.findAllIn(replacedStr).toArray


    if(resp.length==0){
      return Array()
    }


    if(resp(0)=="-"){
      resp = "0"+:resp
    }


    resp

  }

}
