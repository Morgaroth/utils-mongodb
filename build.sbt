name := """utils-mongodb"""

scalacOptions ++= Seq("-feature")

organization := """io.github.morgaroth"""

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.11.12", "2.12.4")

resolvers ++= Seq(
  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/"
)

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

bintrayVcsUrl := Some("https://github.com/Morgaroth/utils-mongodb")

libraryDependencies ++= Seq(
  "com.github.salat" %% "salat" % "1.11.2",
  Typesafe.Config.ver("1.3.2"),
)

releaseCrossBuild := true

publishArtifact in Test := false