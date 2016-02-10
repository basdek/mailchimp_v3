package com.basdek.mailchimp_v3.dto

/**
  * This is a generic success response container for any (test) scenario,
  * where you would not be interested in whatever the success response was.
  *
  * I.e. error reporting testing.
  */
case class GenericMailChimpSuccessHolder(x : Any) extends MailChimpSuccess
