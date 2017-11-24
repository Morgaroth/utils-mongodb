package io.github.morgaroth.utils

import io.github.morgaroth.utils.mongodb.salat.HasGenericSerializer
import salat.Context


package object mongodb extends HasGenericSerializer {
  type SalatContext = Context
}
