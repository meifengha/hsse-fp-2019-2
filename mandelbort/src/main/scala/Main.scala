package main

import scalax.chart.api._
import scala.collection.mutable.ListBuffer
import complex._


object Main {

  def main(args: Array[String]) {
    
    val z = new Complex(0, 0)  
    val data = mandelbort(ListBuffer())
    
    
    val title: String = "Mandelbort"
    val chart = XYLineChart(data)
    chart.plot.setRenderer(new org.jfree.chart.renderer.xy.XYLineAndShapeRenderer(false, true))
    
    chart.saveAsPNG("mandelbort.png")
  }
  
 
  
  def mandelbort(container: ListBuffer[(Double, Double)]): List[(Double, Double)] = {
    for (re <- -2.0 until 2.0 by 0.001)
      for (im <- 2.0 until -2.0 by -0.001)
        if (haslim(new Complex(0.0, 0.0), new Complex(re, im), 0))
          container += ((re, im))
      
    container.toList
  }
  
  def haslim(z: Complex, c: Complex, count: Int): Boolean =
  {
     if (z.module < 4 && count < 100)
       haslim(z * z + c, c, count + 1)
     else if (count == 100)
       true
     else 
       false
       
          
  }
  
}



