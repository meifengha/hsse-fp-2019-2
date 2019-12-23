name := "untitled5"

version := "0.1"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.2"

parallelExecution in Test := false