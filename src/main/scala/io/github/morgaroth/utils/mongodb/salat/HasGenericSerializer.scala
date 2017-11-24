package io.github.morgaroth.utils.mongodb.salat

import io.github.morgaroth.utils.mongodb.SalatContext
import salat.grater

trait HasGenericSerializer {
  def getSerializerFor[T <: AnyRef](implicit manifest: Manifest[T], ctx: SalatContext) = grater[T]

}
