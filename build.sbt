import SbtReleaseHelpers._
import sbtbuildinfo.Plugin._
import sbtrelease.ReleasePlugin._
import sbtrelease.ReleaseStateTransformations._
import sbtrelease.ReleaseStep
import sbtrelease.ReleasePlugin.ReleaseKeys.crossBuild

name := """utils-mongodb"""

scalacOptions ++= Seq("-feature")

organization := """io.github.morgaroth"""

crossScalaVersions := Seq("2.10.4", "2.11.7")

resolvers ++= Seq(
  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/"
)

val ficusDependency = scalaVersion {
  case ver_2_11 if ver_2_11 startsWith "2.11." =>
    Ficus.Config.`1.1.2`
  case _ =>
    Ficus.Config.`1.0.1` exclude("com.typesafe", "config")
}

libraryDependencies ++= Seq(
  Salat.`1.9.9`,
  Typesafe.Config.`1.3.0`
)

libraryDependencies <+= ficusDependency

buildInfoSettings

buildInfoKeys := Seq[BuildInfoKey](
  name, version, scalaVersion, sbtVersion, libraryDependencies, resolvers
)

buildInfoPackage := "io.github.morgaroth.utils.mongodb.salat.build"

sourceGenerators in Compile <+= buildInfo

sonatypeSettings

releaseSettings

crossBuild := true

ReleaseKeys.releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies, // : ReleaseStep
  inquireVersions, // : ReleaseStep
  runClean,
  runTest, // : ReleaseStep
  setReleaseVersion, // : ReleaseStep
  publishArtifactsSigned,
  finishReleaseAtSonatype,
  commitReleaseVersion, // : ReleaseStep, performs the initial git checks
  tagRelease, // : ReleaseStep
  setNextVersion, // : ReleaseStep
  commitNextVersion, // : ReleaseStep
  pushChanges // : ReleaseStep, also checks that an upstream branch is properly configured
)

publishArtifact in Test := false

pomExtra := githubPom(name.value, "Mateusz Jaje", "Morgaroth")

publishTo := publishRepoForVersion(version.value)

// Do not include log4jdbc as a dependency.
pomPostProcess := PackagingHelpers.removeTestOrSourceDependencies