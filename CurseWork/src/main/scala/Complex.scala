package complex

class Complex(val real: Double, val image: Double) {
   override def toString = real + (if (image < 0) "-" + -image else "+" + image) + "*i"
   def +(rhs: Complex) = new Complex(rhs.real + real, rhs.image + image)
   def *(rhs: Complex) = new Complex(real * rhs.real - image * rhs.image, image * rhs.real + real * rhs.image)
   def module: Double = real * real + image * image
}
