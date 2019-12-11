import org.apache.spark.SparkConf
import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.sql._
import swiftvis2.plotting._
import swiftvis2.plotting.renderer._
import swiftvis2.plotting.styles._
import scala.util.Try

object program {
  System.setProperty("hadoop.home.dir", "E:\\rec\\hadoop")
  val conf = new SparkConf().setAppName("Baho").setMaster("local[*]").set("spark.executor.memory", "2g")
    .set("spark.sql.warehouse.dir", "file:///c:/tmp/spark-warehouse")
  val spark = SparkSession.builder().config(conf).getOrCreate()
  val sc = spark.sparkContext

  def main(args: Array[String]) {

    val pointsRow = sc.textFile("resources/points.txt")

    val pointsRDD = pointsRow.map(_.split(",")).persist()

    val pointsVector = pointsRDD.map { row =>
      Try(Vectors.dense(row(0).toDouble, row(1).toDouble)).toOption
    }.filter(_.isDefined).map(_.get)
    // Cluster the data into two classes using KMeans
    val numClusters = 50
    val numIterations = 20
    val clusters = KMeans.train(pointsVector, numClusters, numIterations)
    val WSSSE = clusters.computeCost(pointsVector)
    println("Within Set Sum of Squared Errors = " + WSSSE)

    clusters.save(sc, "target/baho/KMeansModel")
    val sameModel = KMeansModel.load(sc, "target/baho/KMeansModel")

    import spark.implicits._
    import javafx.embed.swing.JFXPanel
    val jfxPanel = new JFXPanel
    val x = clusters.clusterCenters.map(Vector => Vector.toArray.apply(0))
    val y = clusters.clusterCenters.map(Vector => Vector.toArray.apply(1))
    val font = new Renderer.FontData("Ariel", Renderer.FontStyle.Plain)
    val style =ScatterStyle(x, y)
    val p2d =Plot2D(style, "x", "y")
    val xAxis =NumericAxis("x",tickLabelInfo =Some(Axis.LabelSettings(90, font, "%1.1f")), name =Some(Axis.NameSettings("X", font)))
    val yAxis =NumericAxis("y",tickLabelInfo =Some(Axis.LabelSettings(0, font, "%1.1f")), name =Some(Axis.NameSettings("Y", font)))
    val grid =PlotGrid(Seq(Seq(Seq(p2d))), Map("x" ->xAxis, "y" -> yAxis), Seq(1.0), Seq(1.0))
    val plot =Plot(grids =Map("main" -> Plot.GridData(grid, Bounds(0.0, 0.05, 0.95, 0.95))))
    FXRenderer(plot, 600, 600)
  }
}