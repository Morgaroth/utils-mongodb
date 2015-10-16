package io.github.morgaroth.utils.mongodb.salat

import com.mongodb.casbah.Implicits._
import com.mongodb.casbah.{MongoClient, MongoClientURI, WriteConcern}
import com.novus.salat.Context
import com.novus.salat.dao.SalatDAO
import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._

import scala.language.reflectiveCalls

/**
 * Helper class for constructing MongoDB DAO, uses Typesafe configuration and collection name as
 * constructor arguments, prepared especially for objects with key of type String
 *
 * Usage:
 * case class Foo(@Key("_id") someID:String, someInt:Integer, someObject: anotherObject)
 *
 * application.conf:
 * ...
 * this.app.Foo.Database{
 * uri = "mongodb://localhost/FooDatabase"
 * }
 * ...
 *
 * val config = ConfigFactory.load().getConfig("this.app.Foo.Database") // reads config with "uri" element
 * object FooDAO extends MongoDAOStringKey[Foo](config,"foos")
 *
 */
abstract class MongoDAOAnyKey[ObjectType <: AnyRef, KeyType <: Any](config: Config, collectionName: String)
                                                                   (implicit mot: Manifest[ObjectType], mkt: Manifest[KeyType], ctx: Context)
  extends SalatDAO[ObjectType, KeyType](
    collection = {
      val clientURI = MongoClientURI(config.as[String]("uri"))
      val dbName = clientURI.database.getOrElse {
        throw new IllegalArgumentException(s"You must provide database name in connection uri")
      }
      MongoClient(clientURI)(dbName).getCollection(collectionName).asScala
    }) {
  override def defaultWriteConcern = WriteConcern.Acknowledged
}