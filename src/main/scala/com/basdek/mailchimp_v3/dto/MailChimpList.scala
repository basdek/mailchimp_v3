package com.basdek.mailchimp_v3.dto

sealed case class MailChimpList_Stats
  (member_count : Int,
   unsubscribe_count: Int,
   cleaned_count: Int,
   member_count_since_send: Int,
   unsubscribe_count_since_send: Int,
   cleaned_count_since_send: Int,
   campaign_count: Int,
   merge_field_count: Int,
   avg_sub_rate: Int,
   avg_unsub_rate: Int,
   target_sub_rate: Int,
   open_rate: Int,
   click_rate: Int)

case class MailChimpList_Contact
  (company : String,
   address1: String,
   address2 : NonRequiredField[String],
   city: String,
   state: String,
   zip : String,
   country: String,
   phone : NonRequiredField[String])

case class MailChimpList_CampaignDefaults
  (from_name : String,
   from_email : String,
   subject : String,
   language: String)

case class MailChimpList
  (id : ReadOnlyField[String] = None,
   name : String,
   contact : MailChimpList_Contact,
   permission_reminder : String,
   use_archive_bar : Boolean,
   campaign_defaults : MailChimpList_CampaignDefaults,
   notify_on_subscribe : NonRequiredField[String],
   notify_on_unsubscribe : NonRequiredField[String],
   date_created : ReadOnlyField[String] = None,
   list_rating: ReadOnlyField[Int] = None,
   email_type_option: Boolean,
   subscribe_url_short : ReadOnlyField[String] = None,
   subscribe_url_long: ReadOnlyField[String] = None,
   beamer_address : ReadOnlyField[String] = None,
   visibility : NonRequiredField[String] = None, //@TODO: stricten up
   stats: ReadOnlyField[MailChimpList_Stats] = None
  ) extends MailChimpSuccess

case class MailChimpListList
  (lists : List[MailChimpList],
   total_items: Int
  ) extends MailChimpSuccess
