name := """utils-mongodb"""

version := "1.2.6-SNAPSHOT"

scalacOptions ++= Seq("-feature")

organization := """io.github.morgaroth"""

//pomExtra := ThisProject.commonPomFile

//publishTo := ThisProject.publishTo

resolvers ++= Seq(
  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/"
)

val ficusDependency = scalaVersion {
  case "2.11.4" | "2.11.2" | "2.11.0" =>
    "net.ceedubs" %% "ficus" % "1.1.1"
  case _ =>
    "net.ceedubs" %% "ficus" % "1.0.1" exclude("com.typesafe", "config")
}

libraryDependencies ++= Seq(
  "com.novus" %% "salat" % "1.9.9",
  "com.typesafe" % "config" % "1.2.1"

)

libraryDependencies <+= ficusDependency
