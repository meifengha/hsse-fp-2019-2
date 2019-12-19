package course
import scala.math._

object Complex {
  case class Complex(var re: Double, var im: Double) {
  def modulus: Double = sqrt(pow(re, 2) + pow(im, 2))
  def +(c: Complex) = new Complex(re + c.re, im + c.im)
  def *(c: Complex) = new Complex(re * c.re - im * c.im, im * c.re + re * c.im)
  }
}