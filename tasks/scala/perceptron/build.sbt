name := "perceptron"

version := "0.1"

libraryDependencies += "com.thoughtworks.deeplearning" %% "plugins-builtins" % "latest.release"

// The native backend for nd4j.
libraryDependencies += "org.nd4j" % "nd4j-native-platform" % "0.8.0"

// Uncomment the following line to switch to the CUDA backend for nd4j.
// libraryDependencies += "org.nd4j" % "nd4j-cuda-8.0-platform" % "0.8.0"

// The magic import compiler plugin, which may be used to import DeepLearning.scala distributed in source format.
addCompilerPlugin("com.thoughtworks.import" %% "import" % "latest.release")

// The ThoughtWorks Each library, which provides the `monadic`/`each` syntax.
libraryDependencies += "com.thoughtworks.each" %% "each" % "latest.release"
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

fork := true

scalaVersion := "2.11.11"


