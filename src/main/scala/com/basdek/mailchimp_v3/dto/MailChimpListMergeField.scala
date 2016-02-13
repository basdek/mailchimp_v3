package com.basdek.mailchimp_v3.dto

import org.json4s.FieldSerializer._
import org.json4s.FieldSerializer

case class MailChimpListMergeField
  (tag : String,
   name: String,
   _type: String,
   required: NonRequiredField[Boolean],
   default_value: NonRequiredField[String],
   public: NonRequiredField[Boolean],
   display_order: NonRequiredField[Int],
   help_text: NonRequiredField[String]
  ) extends MailChimpSuccess

object MailChimpListMergeField {

  /**
    * This is a build method to create a MergeField more easily from Java.
    *
    * @param tag
    * @param name
    * @param _type
    * @param required Nullable
    * @param default_value Nullable
    * @param public Nullable
    * @param display_order Nullable
    * @param help_text Nullable
    * @return
    */
  def build
  (tag: String,
   name: String,
   _type: String,
   required: java.lang.Boolean,
   default_value: String,
   public : java.lang.Boolean,
   display_order: java.lang.Integer,
   help_text: String) : MailChimpListMergeField = {
    MailChimpListMergeField(
      tag = tag,
      name = name,
      _type = _type,
      required = if(required == null) None else Option(required),
      default_value = if(default_value == null) None else Option(default_value),
      public = if(public == null)  None else Option(public),
      display_order = if(display_order == null) None else Option(display_order),
      help_text = if(help_text == null) None else Option(help_text)
    )
  }

  /**
    * Custom serializer for MailChimpListMergeFields.
    * The term 'type' is reserved in Scala, so we have to rework to _type.
    */
  val serializer =
    FieldSerializer[MailChimpListMergeField](
      renameTo("_type", "type"),
      renameFrom("type", "_type")
    )
}

case class MailChimpListMergeFieldList
  (merge_fields: List[MailChimpListMergeField]) extends MailChimpSuccess