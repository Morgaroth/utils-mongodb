package io.github.morgaroth.utils.mongodb.salat

import com.mongodb.casbah.Implicits._
import com.mongodb.casbah.{MongoClient, MongoClientURI, WriteConcern}
import com.novus.salat.Context
import com.novus.salat.dao.SalatDAO
import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._

/**
 * Helper class for constructing MongoDB DAO, uses Typesafe configuration and collection name as
 * constructor arguments, prepared especially for objects with key of type String
 *
 * Usage:
 * case class Foo(@Key("_id") someID:String, someInt:Integer, someObject: anotherObject)
 *
 * application.conf:
 *   ...
 *   this.app.Foo.Database{
 *     uri = "mongodb://localhost/FooDatabase"
 *   }
 *   ...
 *
 * val config = ConfigFactory.load().getConfig("this.app.Foo.Database") // reads config with "uri" element
 * object FooDAO extends MongoDAOStringKey[Foo](config,"foos")
 *
 */
abstract class MongoDAOStringKey[ObjectType <: AnyRef](config: Config, collectionName: String)
                                                      (implicit mot: Manifest[ObjectType], ctx: Context)
  extends SalatDAO[ObjectType, String](
    collection = {
      val clientURI = MongoClientURI(config.as[String]("uri"))
      val dbName = clientURI.database.getOrElse {
        throw new IllegalArgumentException(s"You must provide database name in connection uri")
      }
      import scala.language.reflectiveCalls
      MongoClient(clientURI)(dbName).getCollection(collectionName).asScala
    })(mot, implicitly[Manifest[String]], ctx) {
  override def defaultWriteConcern = WriteConcern.Acknowledged
}