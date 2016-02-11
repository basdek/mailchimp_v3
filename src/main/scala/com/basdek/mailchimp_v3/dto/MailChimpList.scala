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

case object MailChimpList_Contact {
  /**
    * This is a build method to create a Contact more easily from Java.
    * @param company
    * @param address1
    * @param address2 //Nullable
    * @param city
    * @param state
    * @param zip
    * @param country
    * @param phone //Nullable
    * @return
    */
  def build
    (company : String,
     address1 : String,
     address2: String,
     city : String,
     state: String,
     zip : String,
     country : String,
     phone: String
    ) : MailChimpList_Contact = {

    MailChimpList_Contact(
      company = company,
      address1 = address1,
      address2 = if (address2 == null) None else Option(address2),
      city = city,
      state = state,
      zip = zip,
      country = country,
      phone = if (phone == null) None else Option(phone)
    )
  }
}

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

object MailChimpList {
  /**
    * This is a build method to create a MailChimpList instance more easily from Java.
    *
    * @param name
    * @param contact
    * @param permission_reminder
    * @param use_archive_bar
    * @param campaign_defaults
    * @param notify_on_subscribe Nullable
    * @param notify_on_unsubscribe Nullable
    * @param email_type_option
    * @param visibility Nullable
    * @return
    */
  def build
    (name : String,
     contact: MailChimpList_Contact,
     permission_reminder: String,
     use_archive_bar : Boolean,
     campaign_defaults: MailChimpList_CampaignDefaults,
     notify_on_subscribe : String,
     notify_on_unsubscribe : String,
     email_type_option : Boolean,
     visibility : String
    ) : MailChimpList = {
    MailChimpList(
      name = name,
      contact = contact,
      permission_reminder = permission_reminder,
      use_archive_bar = use_archive_bar,
      campaign_defaults = campaign_defaults,
      notify_on_subscribe = if (notify_on_subscribe == null) None else Option(notify_on_subscribe),
      notify_on_unsubscribe = if(notify_on_unsubscribe == null) None else Option(notify_on_unsubscribe),
      email_type_option = email_type_option,
      visibility = if(visibility == null)  None else Option(visibility)
    )
  }
}

case class MailChimpListList
  (lists : List[MailChimpList],
   total_items: Int
  ) extends MailChimpSuccess
