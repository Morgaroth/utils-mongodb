package io.github.morgaroth.utils.mongodb

import com.novus.salat.Context
import com.typesafe.config.{Config, ConfigFactory}
import org.bson.types.ObjectId

package object salat {


  // key type specified
  class MongoDAOObjectIdKey[ObjectType <: AnyRef](config: Config, collectionName: String)
                                                 (implicit mot: Manifest[ObjectType], ctx: Context)
    extends MongoDAOAnyKey[ObjectType, ObjectId](config, collectionName)

  class MongoDAO[ObjectType <: AnyRef](config: Config, collectionName: String)
                                      (implicit mot: Manifest[ObjectType], ctx: Context)
    extends MongoDAOAnyKey[ObjectType, String](config, collectionName)


  // with joda support

  class MongoDAOJodaSupport[ObjectType <: AnyRef](config: Config, collectionName: String)
                                                 (implicit mot: Manifest[ObjectType], ctx: Context)
    extends MongoDAO[ObjectType](config, collectionName)
    with JodaSupport

  class MongoDAOAnyKeyJodaSupport[ObjectType <: AnyRef, IDType <: Any](config: Config, collectionName: String)
                                                                      (implicit mot: Manifest[ObjectType], mid: Manifest[IDType], ctx: Context)
    extends MongoDAOAnyKey[ObjectType, IDType](config, collectionName)
    with JodaSupport

  class MongoDAOMongoDAOObjectIdKeyJodaSupport[ObjectType <: AnyRef, IDType <: Any](config: Config, collectionName: String)
                                                                                   (implicit mot: Manifest[ObjectType], mid: Manifest[IDType], ctx: Context)
    extends MongoDAOObjectIdKey[ObjectType](config, collectionName)
    with JodaSupport


  // from resource

  class MongoDAOFromResource[ObjectType <: AnyRef](databaseUriConfigPath: String, collectionName: String)
                                                  (implicit mot: Manifest[ObjectType], ctx: Context)
    extends MongoDAO[ObjectType](ConfigFactory.load().getConfig(databaseUriConfigPath), collectionName)

  class MongoDAOAnyKeyFromResource[ObjectType <: AnyRef, IDType <: Any](databaseUriConfigPath: String, collectionName: String)
                                                                       (implicit mot: Manifest[ObjectType], mid: Manifest[IDType], ctx: Context)
    extends MongoDAOAnyKey[ObjectType, IDType](ConfigFactory.load().getConfig(databaseUriConfigPath), collectionName)

  class MongoDAOObjectIdKeyFromResource[ObjectType <: AnyRef, IDType <: Any](databaseUriConfigPath: String, collectionName: String)
                                                                            (implicit mot: Manifest[ObjectType], mid: Manifest[IDType], ctx: Context)
    extends MongoDAOObjectIdKey[ObjectType](ConfigFactory.load().getConfig(databaseUriConfigPath), collectionName)


  // with joda from resource

  class MongoDAOFromResourceJodaSupport[ObjectType <: AnyRef](databaseUriConfigPath: String, collectionName: String)
                                                             (implicit mot: Manifest[ObjectType], ctx: Context)
    extends MongoDAO[ObjectType](ConfigFactory.load().getConfig(databaseUriConfigPath), collectionName)
    with JodaSupport

  class MongoDAOAnyKeyFromResourceJodaSupport[ObjectType <: AnyRef, IDType <: Any](databaseUriConfigPath: String, collectionName: String)
                                                                                  (implicit mot: Manifest[ObjectType], mid: Manifest[IDType], ctx: Context)
    extends MongoDAOAnyKey[ObjectType, IDType](ConfigFactory.load().getConfig(databaseUriConfigPath), collectionName)
    with JodaSupport

  class MongoDAOObjectIdKeyFromResourceJodaSupport[ObjectType <: AnyRef, IDType <: Any](databaseUriConfigPath: String, collectionName: String)
                                                                                       (implicit mot: Manifest[ObjectType], mid: Manifest[IDType], ctx: Context)
    extends MongoDAOObjectIdKey[ObjectType](ConfigFactory.load().getConfig(databaseUriConfigPath), collectionName)
    with JodaSupport

}
