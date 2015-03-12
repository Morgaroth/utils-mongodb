package io.github.morgaroth.utils.mongodb

import com.novus.salat.Context
import com.typesafe.config.Config

package object salat {

  class MongoDAOStringKeyJodaSupport[ObjectType <: AnyRef](config: Config, collectionName: String)
                                                          (implicit mot: Manifest[ObjectType], ctx: Context)
    extends MongoDAOStringKey[ObjectType](config, collectionName)
    with JodaSupport

  class MongoDAOAnyKeyJodaSupport[ObjectType <: AnyRef, IDType <: Any](uriPath: String, collectionName: String)
                                                                      (implicit mot: Manifest[ObjectType], mid: Manifest[IDType], ctx: Context)
    extends SalatDAOWithCfg[ObjectType, IDType](uriPath, collectionName)
    with JodaSupport

  class MongoDAOAnyKey[ObjectType <: AnyRef, IDType <: Any](uriPath: String, collectionName: String)
                                                           (implicit mot: Manifest[ObjectType], mid: Manifest[IDType], ctx: Context)
    extends SalatDAOWithCfg[ObjectType, IDType](uriPath, collectionName)

}
