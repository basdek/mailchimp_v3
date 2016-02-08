package com.basdek.mailchimp_v3.dto

import org.json4s.FieldSerializer
import org.json4s.FieldSerializer._

case class MailChimpError(status: Int, _type : String,
  title : String, detail : String, instance : String) {

  /**
    * Computes a friendly representation of the error.
    * @return The friendly rep.
    */
  override def toString : String = s"Code: $status, $title: $detail"
}

object MailChimpError {

  /**
    * Custom serializer for the Error message bodies MailChimp returns.
    * The word 'type' is reserved in Scala, therefore we rework it to _type.
    */
  val serializer = FieldSerializer[MailChimpError](
    renameTo("_type", "type"),
    renameFrom("type", "_type")
  )
}


