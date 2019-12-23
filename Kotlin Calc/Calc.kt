import java.util.*

fun Summ(first: Double = 0.0, second: Double = 0.0): Double{
    return first+second
}

fun Substract(first: Double = 0.0, second: Double = 0.0): Double{
    return first-second
}

fun Mult(first: Double = 0.0, second: Double = 0.0): Double{
    return first*second
}
fun Divide(first: Double = 0.0, second: Double = 1.0): Double{
    if (second == 0.0)
        return 0.0
    return first/second
}

fun main(args: Array<String>) {
    val reader = Scanner(System.`in`)
    println("Enter the following sequence: number1 action number2")
    println("Possible actions: +, -, *, /")

    val first = reader.nextDouble()
    val operator = reader.next()[0]
    val second = reader.nextDouble()
    val result: Double
    when (operator) {
        '+' -> {
            result = Summ(first, second)
        }
        '-' -> {
            result = Substract(first, second)
        }
        '*' -> {
            result = Mult(first, second)
        }
        '/' -> {
            result = Divide(first, second)
        }
        else -> {
            print("Error! operator is not correct")
            return
        }
    }
    println("$first $operator $second = $result")

}