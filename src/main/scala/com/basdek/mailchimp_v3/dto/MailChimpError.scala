package com.basdek.mailchimp_v3.dto

import org.json4s.FieldSerializer
import org.json4s.FieldSerializer._

sealed case class Error(field : String, message : String) {
  override def toString : String = s"[Error detail: $field] $message"
}

case class MailChimpError(
  status: Int,
  _type : String,
  title : String,
  detail : String,
  instance : String,
  errors: Option[List[Error]]) {

  /**
    * Computes a friendly representation of the error.
    * @return The friendly rep.
    */
  override def toString : String = {

    //If we have detail msgs, make a nice string for them.
    val errorsStr = errors
      .map(e => e.toString)
      .foldLeft("") ((x: String, m: String) => m + x + "\n")

    s"Code: $status, $title: $detail $errorsStr"
  }
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


