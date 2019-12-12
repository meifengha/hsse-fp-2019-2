class ComplexNumber (val real : Double, val imaginary : Double) { // Объявление класса комплексного числа
  def +(c : ComplexNumber) = new ComplexNumber(real + c.real, imaginary + c.imaginary) // Определение действий с константными числами
  def -(c : ComplexNumber) = new ComplexNumber(real - c.real, imaginary - c.imaginary)
  def *(c : ComplexNumber) = new ComplexNumber(real * c.real - imaginary * c.imaginary, real * c.imaginary + imaginary * c.real)
  def abs() = real*real + imaginary*imaginary
}
