package com.basdek.mailchimp_v3.dto

import org.json4s.JsonAST.{JField, JString, JObject, JValue}
import org.json4s.{DefaultFormats, Extraction, CustomSerializer, FieldSerializer}
import org.json4s.JsonDSL._
import org.json4s.FieldSerializer._

import scala.collection.JavaConverters._

trait MailChimpListSegment_Options_Condition {
  def getConditionType : String
}

object MailChimpListSegment_Options_ConditionSerializer
  extends CustomSerializer[MailChimpListSegment_Options_Condition](format => (
      {
        case JObject(JField("condition_type", JString("TextMerge")) ::
          JField("field", JString(f)) :: JField("op", JString(o)) ::
          JField("value", JString(v)) :: Nil) =>
          new TextMergeCondition(f, o, v)
      },
      {
        case obj : MailChimpListSegment_Options_Condition =>
          val a : JValue = Extraction.decompose(obj)(DefaultFormats)
          val b : JValue =  JField("condition_type", obj.getConditionType)
          a merge b
      }
    )
  )

case class TextMergeCondition
  (field: String,
   op: String, //@TODO Stricten up
   value: String) extends MailChimpListSegment_Options_Condition
{
  def getConditionType : String = "TextMerge"
}


case class MailChimpListSegment_Options
  (_match: String, // @TODO Stricten up
   conditions: List[MailChimpListSegment_Options_Condition]
  )

object MailChimpListSegment_Options {

  /**
    * Custom serializer for the MailChimpListSegment_Options.
    * The word 'match' is reserved in Scala, therefore we need to rework to _match.
    */
  val serializer = FieldSerializer[MailChimpListSegment_Options](
    renameTo("_match", "match"),
    renameFrom("match", "_match")
  )
}

case class MailChimpListSegment
  (name : String,
   static_segment: NonRequiredField[List[String]], //List of emailadresses
   options: NonRequiredField[MailChimpListSegment_Options],
   member_count: ReadOnlyField[Int] = None,
   _type : ReadOnlyField[String] = None,
   created_at: ReadOnlyField[String] = None,
   updated_at: ReadOnlyField[String] = None,
   list_id : ReadOnlyField[String] = None) extends MailChimpSuccess

object MailChimpListSegment {

  /**
    *
    * @param name
    * @param static_segment Nullable(1)
    * @param options Nullable(1)
    * 1) One of them is required.
    * @return
    */
  def build
    (name: String,
     static_segment: java.util.List[String],
     options: MailChimpListSegment_Options
    ) : MailChimpListSegment = {

    MailChimpListSegment(
      name = name,
      static_segment = if (static_segment == null) None else Option(static_segment.asScala.toList),
      options = if (options == null) None else Option(options)
    )
  }

  /**
    * Custom serializer
    */
  val serializer = FieldSerializer[MailChimpListSegment](
    renameTo("_type", "type"),
    renameFrom("type", "_type")
  )
}

case class MailChimpListSegmentList
  (segments: List[MailChimpListSegment],
   total_items: Int,
   list_id: String) extends MailChimpSuccess
