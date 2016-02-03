lazy val commonSettings = Seq(
    organization := "com.basdek",
    name := "mailchimp_v3",
    version := "0.1.0",
    scalaVersion := "2.11.7"
)

libraryDependencies += "net.databinder.dispatch" %% "dispatch-core" % "0.11.2"