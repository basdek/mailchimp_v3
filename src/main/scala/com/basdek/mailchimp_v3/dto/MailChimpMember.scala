package com.basdek.mailchimp_v3.dto

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

case class MailChimpMember_Location
  (latitude: Double,
   longtitude : Double)

sealed case class MailChimpMember_Stats
  (avg_open_rate : Double,
   avg_click_rate : Double)

sealed case class MailChimpMember_LastNote
  (note_id : String,
   created_at : String,
   created_by: String,
   note: String)

case class MailChimpMember
  (id : ReadOnlyField[String] = None,
   email_address : String,
   unique_email_id : ReadOnlyField[String] = None,
   email_type : NonRequiredField[String] = None,
   status : String, //@TODO: stricten up.
   merge_fields: NonRequiredField[Map[String, Any]],
   interests : NonRequiredField[Map[String,Any]],
   stats : ReadOnlyField[MailChimpMember_Stats] = None,
   ip_signup: ReadOnlyField[String] = None,
   timestamp_signup: ReadOnlyField[String] = None,
   ip_opt: ReadOnlyField[String] = None,
   timestamp_opt : ReadOnlyField[String] = None,
   member_rating : ReadOnlyField[Int] = None,
   last_changed : ReadOnlyField[String] = None,
   language : String,
   vip: Boolean,
   email_client : ReadOnlyField[String] = None,
   location: NonRequiredField[MailChimpMember_Location],
   last_note: ReadOnlyField[MailChimpMember_LastNote] = None,
   list_id : ReadOnlyField[String] = None) extends MailChimpSuccess

object MailChimpMember {

  /**
    * This is a build method to create a Member instance more easily from Java.
    *
    * @param email_address
    * @param email_type Nullable
    * @param status
    * @param merge_fields Nullable
    * @param interests Nullable
    * @param language
    * @param vip
    * @param location Nullable
    * @return
    */
  def build
  (email_address : String,
   email_type : String,
   status: String,
   merge_fields: java.util.Map[String, Object],
   interests: java.util.Map[String, Object],
   language: String,
   vip: Boolean,
   location: MailChimpMember_Location) : MailChimpMember = {

    MailChimpMember(
      email_address = email_address,
      email_type = Option(email_type),
      status = status,
      merge_fields = if (merge_fields == null) None else Option(merge_fields.asScala.toMap),
      interests = if (interests == null) None else Option(interests.asScala.toMap),
      language = language,
      vip = vip,
      location = Option(location)
    )
  }
}

case class MailChimpMemberList
  (members : List[MailChimpMember],
   total_items : Int) extends MailChimpSuccess
