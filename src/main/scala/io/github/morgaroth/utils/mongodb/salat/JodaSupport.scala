package io.github.morgaroth.utils.mongodb.salat

import com.mongodb.casbah.commons.conversions.scala.{RegisterJodaLocalDateTimeConversionHelpers, RegisterJodaTimeConversionHelpers}
import com.novus.salat.conversions.RegisterJodaTimeZoneConversionHelpers
import com.novus.salat.dao.SalatDAO


trait JodaSupport {
  this: SalatDAO[_, _] =>
  RegisterJodaLocalDateTimeConversionHelpers()
  RegisterJodaTimeConversionHelpers()
  RegisterJodaTimeZoneConversionHelpers()
}
