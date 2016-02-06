package com.basdek

import com.basdek.mailchimp_v3.dto.{MailChimpSuccess, MailChimpError}

import scala.concurrent.Future


package object mailchimp_v3 {
  type MailChimpResult = Either[MailChimpError, MailChimpSuccess]
  type MailChimpResultFuture = Future[MailChimpResult]
}
