package operators

abstract class Operator(f: (Double, Double) => Double) {

  def apply(left: Double, right: Double): Double = f.apply(left, right)

}



