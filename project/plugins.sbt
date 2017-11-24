logLevel := Level.Warn

resolvers ++= Seq(
//  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
//  Resolver.sonatypeRepo("snapshots")
)

addSbtPlugin("io.github.morgaroth" %% "sbt-commons" % "0.17")

addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.1")

addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.6")

