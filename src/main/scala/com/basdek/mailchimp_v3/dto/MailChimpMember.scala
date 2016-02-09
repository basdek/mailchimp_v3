package com.basdek.mailchimp_v3.dto

case class MailChimpMember_Location(latitude: Double, longtitude : Double)

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
   list_id : ReadOnlyField[String] = None
  ) extends MailChimpSuccess

case class MailChimpMemberList
  (members : List[MailChimpMember],
   total_items : Int) extends MailChimpSuccess
