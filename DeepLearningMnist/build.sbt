name := "DeepLearningMnist"

version := "0.1"

libraryDependencies += "org.nd4j" %% "nd4s" % "0.8.0"

// All DeepLearning.scala built-in plugins.
libraryDependencies += "com.thoughtworks.deeplearning" %% "plugins-builtins" % "2.0.1"
// The magic import compiler plugin, which may be used to import DeepLearning.scala distributed in source format.
addCompilerPlugin("com.thoughtworks.import" %% "import" % "latest.release")

// The ThoughtWorks Each library, which provides the `monadic`/`each` syntax.
libraryDependencies += "com.thoughtworks.each" %% "each" % "3.3.1"
libraryDependencies += "com.thoughtworks.each" % "each_2.11" % "3.3.1"
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

libraryDependencies += "org.deeplearning4j" % "deeplearning4j-core" % "0.8.0"

libraryDependencies += "org.nd4j" % "nd4j-native-platform" % "0.8.0"

libraryDependencies += "org.nd4j" % "nd4j-cuda-8.0-platform" % "0.8.0"

fork := true

scalaVersion := "2.11.12"
