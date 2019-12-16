import java.io.{DataInputStream, DataOutputStream, File, FileInputStream, FileOutputStream}
import java.util.concurrent.{ExecutorService, Executors}

import org.nd4j.linalg.dataset.DataSet

import scala.collection.mutable
import scala.concurrent.ExecutionContext
import scala.util.Random

object NeuralNetwork extends{

  import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator
  import org.nd4j.linalg.factory.Nd4j
  import org.nd4j.linalg.api.ndarray.INDArray
  import com.thoughtworks.deeplearning.plugins._
  import com.thoughtworks.feature.Factory
  import com.thoughtworks.future._
  import scala.concurrent.Await
  import scala.concurrent.duration.Duration
  import com.thoughtworks.each.Monadic._

  val singleThreadExecutor: ExecutorService = Executors.newSingleThreadExecutor()
  implicit val singleThreadExecutionContext: ExecutionContext = ExecutionContext.fromExecutor(singleThreadExecutor)

  import $exec.`https://gist.githubusercontent.com/Rabenda/f06279e648e45bd574dc382abb4c44ac/raw/7bd7a871030988c58524108c5985f71002f82012/INDArrayLearningRate.sc`
  import $exec.`https://gist.github.com/Atry/15b7d9a4c63d95ad3d67e94bf20b4f69/raw/59f7ee4dff0dde3753f560633574265e950edc93/CNN.sc`
  import $exec.`https://gist.githubusercontent.com/TerrorJack/a60ff752270c40a6485ee787837390aa/raw/119cbacb29dc12d74ae676b4b02687a8f38b02e4/L2Regularization.sc`
  import $exec.`https://gist.githubusercontent.com/Rabenda/0c2fc6ba4cfa536e4788112a94200b50/raw/233cbc83932dad659519c80717d145a3983f57e1/Adam.sc`

  val hyperparameters = Factory[Builtins with CNNs with L2Regularization with Adam with INDArrayLearningRate].
    newInstance(learningRate = 1e-4, l2Regularization = 0.000001)

  import hyperparameters.implicits._
  import hyperparameters.INDArrayLayer
  import hyperparameters.INDArrayWeight
  import hyperparameters.DoubleLayer

  val trainBatchSize = 64
  val random: Random = Random

  val NumberOfClasses: Int = 10
  val NumberOfChannels: Int = 1
  val PixelHeight: Int = 28
  val PixelWidth: Int = 28
  val NumberOfPixels: Int = PixelHeight * PixelWidth
  val KernelSize: Int = 5
  val KernelWidth: Int = KernelSize
  val KernelHeight: Int = KernelSize
  val NumFilters: Int = 32
  val HiddenDim: Int = 500 //define hidden_layer->affineRuleOfCnnLayer
  val WeightScale = 1e-2


  val Padding: Int = (KernelSize - 1) / 2
  val Stride: Int = 1
  val PoolSize: Int = 2

  import org.nd4j.linalg.factory.Nd4j

  object weightsAndBiases {

    import org.nd4s.Implicits._

    var loaded = false
    var cnnWeight: INDArrayWeight = INDArrayWeight(Nd4j.randn(Array(NumFilters, NumberOfChannels, KernelHeight, KernelWidth)) * WeightScale)
    var cnnBias: INDArrayWeight = INDArrayWeight(Nd4j.zeros(NumFilters))
    var affineWeight: INDArrayWeight = INDArrayWeight(Nd4j.randn(Array(NumFilters * (PixelHeight / PoolSize) * (PixelWidth / PoolSize), HiddenDim)) * WeightScale)
    var affineBias: INDArrayWeight = INDArrayWeight(Nd4j.zeros(HiddenDim))
    var affineLastWeight:INDArrayWeight = INDArrayWeight(Nd4j.randn(Array(HiddenDim, NumberOfClasses)) * WeightScale)
    var affineLastBias: INDArrayWeight = INDArrayWeight(Nd4j.zeros(NumberOfClasses))
  }

  import weightsAndBiases._

  def softmax(scores: INDArrayLayer): INDArrayLayer = {
    val expScores = hyperparameters.exp(scores)
    (expScores + 1e-8) / (expScores.sum(1) + 1e-8)
  }

  def affine(input: INDArrayLayer, weight: INDArrayWeight, bias: INDArrayWeight): INDArrayLayer = {
    input dot weight + bias
  }

  def relu(input: INDArrayLayer): INDArrayLayer = {
    import hyperparameters.max
    max(input, 0.0)
  }

  def myNeuralNetwork(input: INDArray): INDArrayLayer = {
    import hyperparameters.max
    import hyperparameters.maxPool
    import hyperparameters.conv2d

    val cnnLayer = maxPool(relu(conv2d(input.reshape(input.shape()(0), NumberOfChannels, PixelHeight, PixelWidth), cnnWeight, cnnBias, (KernelHeight, KernelWidth), (Stride, Stride), (Padding, Padding))), (PoolSize, PoolSize))

    val affineRuleOfCnnLayer = relu(affine(cnnLayer.reshape(input.shape()(0), NumFilters * (PixelHeight / PoolSize) * (PixelWidth / PoolSize)), affineWeight, affineBias))

    val affineOfAffineRuleOfCnnLayer = affine(affineRuleOfCnnLayer.reshape(input.shape()(0), HiddenDim), affineLastWeight, affineLastBias)

    val softmaxValue = softmax(affineOfAffineRuleOfCnnLayer)

    softmaxValue
  }

  def lossFunction(input: INDArray, expectOutput: INDArray): DoubleLayer = {
    val probabilities = myNeuralNetwork(input)
    -(hyperparameters.log(probabilities) * expectOutput).mean
  }

  class Trainer(batchSize: Int, numberOfEpochs: Int = 5) {

    @volatile
    private var isShuttingDown: Boolean = false

    val lossBuffer: mutable.Buffer[Double] = mutable.Buffer.empty[Double]

    def interrupt(): Unit = isShuttingDown = true

    def startTrain(): Unit = {

      @monadic[Future]
      def trainTask(next: DataSet, epoch: Int, iteration: Int): Future[Unit] = {
          var loss = lossFunction(next.getFeatures, next.getLabels).train.each
          lossBuffer += loss
          hyperparameters.logger.info(s"epoch=$epoch iteration=$iteration batchSize=$batchSize loss=$loss")
      }

      var epoch = 0

      while (epoch < numberOfEpochs) {
        var i = 0
        val iterator = new MnistDataSetIterator(batchSize, true, random.nextInt)
        while (iterator.hasNext) {
          Await.result(trainTask(iterator.next, epoch, i).toScalaFuture, Duration.Inf)
          i += 1
        }
        epoch += 1
      }

      hyperparameters.logger.info("Done")
      saveWeights()
    }
  }

  def getAccuracyResult: String = {
    def findMaxItemIndex(iNDArray: INDArray): INDArray = {
      Nd4j.argMax(iNDArray, 1)
    }

    def getAccuracy(score: INDArray, testExpectLabel: INDArray): Double = {
      import org.nd4s.Implicits._
      val scoreIndex = findMaxItemIndex(score)
      val expectResultIndex = findMaxItemIndex(testExpectLabel)
      val accINDArray = scoreIndex.eq(expectResultIndex)
      accINDArray.sumT / score.shape()(0)
    }


    val accuracyResultBuffer = scala.collection.mutable.Buffer.empty[Double]
    val iterator = new MnistDataSetIterator(trainBatchSize, false, random.nextInt)
    while (iterator.hasNext) {
      val next = iterator.next
      val testDataBatch = next.getFeatures
      val testDataLabels = next.getLabels
      val predictResult = Await.result(myNeuralNetwork(testDataBatch).predict.toScalaFuture, Duration.Inf)
      val accuracyResult = getAccuracy(predictResult, testDataLabels)
      accuracyResultBuffer += accuracyResult
    }

    val accuracy = accuracyResultBuffer.sum / accuracyResultBuffer.length

    s"${accuracy * 100.0}%"
  }

  def saveWeights(): Unit = {
    val cnnWeightStream = new DataOutputStream(new FileOutputStream(new File("data/CnnWeight.bin")))
    val cnnBiasStream = new DataOutputStream(new FileOutputStream(new File("data/CnnBias.bin")))
    val affineWeightStream = new DataOutputStream(new FileOutputStream(new File("data/AffineWeight.bin")))
    val affineBiasStream = new DataOutputStream(new FileOutputStream(new File("data/AffineBias.bin")))
    val affineLastWeightStream = new DataOutputStream(new FileOutputStream(new File("data/AffineLastWeight.bin")))
    val affineLastBiasStream = new DataOutputStream(new FileOutputStream(new File("data/AffineLastBias.bin")))
    try {
      Nd4j.write(cnnWeight.data, cnnWeightStream)
      Nd4j.write(cnnBias.data, cnnBiasStream)
      Nd4j.write(affineWeight.data, affineWeightStream)
      Nd4j.write(affineBias.data, affineBiasStream)
      Nd4j.write(affineLastWeight.data, affineLastWeightStream)
      Nd4j.write(affineLastBias.data, affineLastBiasStream)
    } finally {
      cnnWeightStream.close()
      cnnBiasStream.close()
      affineWeightStream.close()
      affineBiasStream.close()
      affineLastWeightStream.close()
      affineLastBiasStream.close()
    }
  }

  def loadWeights(): Unit = {
    if (loaded) return
    val cnnWeightStream = new DataInputStream(new FileInputStream(new File("data/CnnWeight.bin")))
    val cnnBiasStream = new DataInputStream(new FileInputStream(new File("data/CnnBias.bin")))
    val affineWeightStream = new DataInputStream(new FileInputStream(new File("data/AffineWeight.bin")))
    val affineBiasStream = new DataInputStream(new FileInputStream(new File("data/AffineBias.bin")))
    val affineLastWeightStream = new DataInputStream(new FileInputStream(new File("data/AffineLastWeight.bin")))
    val affineLastBiasStream = new DataInputStream(new FileInputStream(new File("data/AffineLastBias.bin")))
    try {
      cnnWeight = INDArrayWeight(Nd4j.read(cnnWeightStream))
      cnnBias = INDArrayWeight(Nd4j.read(cnnBiasStream))
      affineWeight = INDArrayWeight(Nd4j.read(affineWeightStream))
      affineBias = INDArrayWeight(Nd4j.read(affineBiasStream))
      affineLastWeight = INDArrayWeight(Nd4j.read(affineLastWeightStream))
      affineLastBias = INDArrayWeight(Nd4j.read(affineLastBiasStream))
      loaded = true

    } finally {
      cnnWeightStream.close()
      cnnBiasStream.close()
      affineWeightStream.close()
      affineBiasStream.close()
      affineLastWeightStream.close()
      affineLastBiasStream.close()
    }
  }

  def predict(input: INDArray): Int = {
    loadWeights()
    val predictResult = Await.result(myNeuralNetwork(input).predict.toScalaFuture, Duration.Inf)
    Nd4j.argMax(predictResult).getInt(0)
  }

  def train(): Unit = {
    import org.nd4s.Implicits._

    cnnWeight = INDArrayWeight(Nd4j.randn(Array(NumFilters, NumberOfChannels, KernelHeight, KernelWidth)) * WeightScale)
    cnnBias = INDArrayWeight(Nd4j.zeros(NumFilters))
    affineWeight = INDArrayWeight(Nd4j.randn(Array(NumFilters * (PixelHeight / PoolSize) * (PixelWidth / PoolSize), HiddenDim)) * WeightScale)
    affineBias = INDArrayWeight(Nd4j.zeros(HiddenDim))
    affineLastWeight = INDArrayWeight(Nd4j.randn(Array(HiddenDim, NumberOfClasses)) * WeightScale)
    affineLastBias = INDArrayWeight(Nd4j.zeros(NumberOfClasses))

    val trainer = new Trainer(trainBatchSize, 10)
    trainer.startTrain()
    println(trainer.lossBuffer)

    println(s"The accuracy is $getAccuracyResult")
  }

  def test(): Unit = {
    loadWeights()

    println(s"The accuracy is $getAccuracyResult")
  }
}


