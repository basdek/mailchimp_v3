package com.basdek.mailchimp_v3.operations

import com.basdek.mailchimp_v3.dto.GenericMailChimpSuccessHolder
import com.basdek.mailchimp_v3.{MailChimpResultFuture, Config}
import dispatch.:/

/**
  * Operation to test your credentials
  *
  * Useful to run as part of an integration suite on a production system.
  * To test credentials / availability of the service of MailChimp.
  *
  * Example usage: your config is working (correct credentials & MailChimp is up)
  * when this operation returns a MailChimpSuccess -> Right.
  *
  * @param cfg A Config instance.
  */
class AuthTestOperation(val cfg: Config)
  extends Operation with SimpleAuthenticate {

  /**
    * See class description.
    * @return A MailChimpResultFuture (either an error or a result)
    */
  def execute : MailChimpResultFuture = {
    val req = addAuth(:/(s"${cfg.apiEndpoint}/").secure)
    httpToResult(req, (r) => GenericMailChimpSuccessHolder(r))
  }

}
