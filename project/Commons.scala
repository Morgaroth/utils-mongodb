import sbt._

import scala.xml.transform.{RewriteRule, RuleTransformer}

object SbtSonatypeHelpers {
  def githubPom(projectName: String) = pom(s"https://github.com/Morgaroth/$projectName", s"git@github.com:Morgaroth/$projectName.git")

  def pom(projectUrl: String, developerUrl: String) = {
    <url>
      {projectUrl}
    </url>
      <licenses>
        <license>
          <name>BSD-style</name>
          <url>http://www.opensource.org/licenses/bsd-license.php</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <scm>
        <url>
          {developerUrl}
        </url>
        <connection>scm:git:
          {developerUrl}
        </connection>
      </scm>
      <developers>
        <developer>
          <id>Morgaroth</id>
          <name>Mateusz Jaje</name>
        </developer>
      </developers>
  }

  def publishToGen = (ver: String) => {
    val nexus = "https://oss.sonatype.org/"
    if (ver.endsWith("SNAPSHOT"))
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
  }
}

object PackagingHelpers {
  private def testIfRemove(dep: xml.Node) =
    ((dep \ "scope").text == "test") ||
      ((dep \ "classifier").text == "sources")

  val removeTestOrSourceDependencies: (xml.Node) => xml.Node = { (node: xml.Node) =>
    val rewriteRule = new RewriteRule {
      override def transform(node: xml.Node) = node.label match {
        case "dependency" if testIfRemove(node) => xml.NodeSeq.Empty
        case _ => node
      }
    }
    val transformer = new RuleTransformer(rewriteRule)
    transformer.transform(node)(0)
  }
}