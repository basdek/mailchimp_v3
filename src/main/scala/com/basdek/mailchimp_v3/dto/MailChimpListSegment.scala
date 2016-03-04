package com.basdek.mailchimp_v3.dto

import org.json4s.JsonAST.{JField, JString, JObject, JValue}
import org.json4s.{DefaultFormats, Extraction, CustomSerializer, FieldSerializer}
import org.json4s.JsonDSL._
import org.json4s.FieldSerializer._

import scala.collection.JavaConverters._

trait MailChimpListSegment_Options_Condition {
  def getConditionType : String
}

/**
  * Custom serializer for all MailChimpListSegment_Options_Condition types.
  *
  * You need to write an entry for every new Condition type we want to add,
  * luckily serialization is generic.
  *
  */
object MailChimpListSegment_Options_ConditionSerializer
  extends CustomSerializer[MailChimpListSegment_Options_Condition](format => (
      {
        //Deserialize TextMergeCondition.
        case JObject(JField("condition_type", JString("TextMerge")) ::
          JField("field", JString(f)) :: JField("op", JString(o)) ::
          JField("value", JString(v)) :: Nil) =>
          new TextMergeCondition(f, o, v)
      },
      {
        //Serialization is generic.
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
    * This is a build method to make the instantiation of MCLS_O_C more easily in java.
    * @param _match
    * @param conditions
    * @return
    */
  def build
  (_match: String,
   conditions: java.util.List[MailChimpListSegment_Options_Condition]) :
  MailChimpListSegment_Options = {
    MailChimpListSegment_Options(
      _match = _match,
      conditions = conditions.asScala.toList
    )
  }

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
  (id : ReadOnlyField[Integer] = None,
   name : String,
   static_segment: NonRequiredField[List[String]], //List of email-addresses
   options: NonRequiredField[MailChimpListSegment_Options],
   member_count: ReadOnlyField[Int] = None,
   _type : ReadOnlyField[String] = None,
   created_at: ReadOnlyField[String] = None,
   updated_at: ReadOnlyField[String] = None,
   list_id : ReadOnlyField[String] = None) extends MailChimpSuccess

object MailChimpListSegment {

  /**
    * This is a build method to make the instantiation of ListSegments more easily in java.
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
    * Custom serializer for MailChimpListSegment.
    * The term 'type' is a reserved word in Scala, so we have to rework to _type.
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
