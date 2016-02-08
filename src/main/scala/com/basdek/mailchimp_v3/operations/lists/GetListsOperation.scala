package com.basdek.mailchimp_v3.operations.lists

import com.basdek.mailchimp_v3.dto.MailChimpListList
import com.basdek.mailchimp_v3.{Config, MailChimpResultFuture, SimpleAuthenticate}
import com.basdek.mailchimp_v3.operations.Operation
import com.ning.http.client.Response
import dispatch._, Defaults._
import org.json4s._
import org.json4s.native.JsonMethods._

/**
  * Implementation of the get /lists endpoint.
  *
  * @param cfg A Config instance.
  */
class GetListsOperation(val cfg : Config)
  extends Operation with SimpleAuthenticate {

  private def transformer(res: Response): MailChimpListList = {
    val responseBody = res.getResponseBody()
    parse(responseBody).extract[MailChimpListList]
  }

  def execute : MailChimpResultFuture = {
    val req = addAuth(:/(s"${cfg.apiEndpoint}/lists").secure)
    httpToResult(req, transformer)
  }

}
