lazy val commonSettings = Seq(
  organization := "com.basdek",
  name := "mailchimp_v3",
  version := "0.1.0"
)

scalaVersion := "2.11.7"
//HTTP.
libraryDependencies += "net.databinder.dispatch" %% "dispatch-core" % "0.11.2"

//JSON parsing.
libraryDependencies += "org.json4s" %% "json4s-native" % "3.3.0"

//Logging.
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.9" % "test"

//Testing.
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

//Testing config.
libraryDependencies += "com.typesafe" % "config" % "1.3.0" % "test"
