package com.basdek.mailchimp_v3.dto

import org.json4s.FieldSerializer
import org.json4s.FieldSerializer._

case class MailChimpInterestCategory
  (list_id : ReadOnlyField[String] = None,
   id : ReadOnlyField[String] = None,
   title : String,
   display_order: NonRequiredField[Int],
   _type: String) extends MailChimpSuccess

object MailChimpInterestCategory {

  /**
    * This is a build method to create an InterestCategory more easily from Java.
    *
    * @param title
    * @param display_order Nullable
    * @param _type
    * @return
    */
  def build
  (title : String,
   display_order: java.lang.Integer,
   _type: String) : MailChimpInterestCategory = {
     MailChimpInterestCategory(
       title = title,
       display_order = if(display_order == null) None else Option(display_order),
       _type = _type
     )

   }

  val serializer = FieldSerializer[MailChimpInterestCategory](
    renameTo("_type", "type"),
    renameFrom("type", "_type")
  )
}

case class MailChimpInterestCategoryList
  (categories: List[MailChimpInterestCategory],
   list_id: String,
   total_items: Int) extends MailChimpSuccess
