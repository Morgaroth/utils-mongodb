package io.github.morgaroth.utils.mongodb.salat

import com.mongodb.casbah.Implicits._
import com.mongodb.casbah.{WriteConcern, MongoClient, MongoClientURI}
import com.novus.salat.Context
import com.novus.salat.dao.SalatDAO
import com.typesafe.config.ConfigFactory
import net.ceedubs.ficus.Ficus._

/**
 * Helper class with SalatDAO configuration read from application configuration file
 *
 * Usage:
 * case class Foo(@Key("_id") someID:String, someInt:Integer, someObject: anotherObject)
 *
 * object FooDAO extends SalatDAOWithCfg[Foo,String]("app.db.uri","foos")
 *
 */
abstract class SalatDAOWithCfg[ObjectType <: AnyRef, IDType <: Any](databaseUriConfigPath: String, collectionName: String)
                                                                   (implicit mot: Manifest[ObjectType], mid: Manifest[IDType], ctx: Context)
  extends SalatDAO[ObjectType, IDType](
    collection = {
      val clientURI = MongoClientURI(ConfigFactory.load().as[String](databaseUriConfigPath))
      val dbName = clientURI.database.getOrElse {
        throw new IllegalArgumentException(s"You must provide database name in connection uri in path $databaseUriConfigPath!")
      }
      import scala.language.reflectiveCalls
      MongoClient(clientURI)(dbName).getCollection(collectionName).asScala
    })(mot, mid, ctx) {
  override def defaultWriteConcern = WriteConcern.Acknowledged
}