package com.basdek.mailchimp_v3.operations.lists

import com.basdek.mailchimp_v3.dto.MailChimpList
import com.basdek.mailchimp_v3.{MailChimpResultFuture, SimpleAuthenticate, Config}
import com.basdek.mailchimp_v3.operations.Operation
import com.ning.http.client.Response
import dispatch._, Defaults._
import org.json4s._
import org.json4s.native.JsonMethods._

/**
  * Implementation of the /lists/{id} endpoint.
  *
  * @param cfg A config instance.
  */
class GetListOperation(val cfg : Config, val id: String)
  extends Operation with SimpleAuthenticate {

  private def transformer(res : Response) : MailChimpList = {
    val responseBody = res.getResponseBody()
    parse(responseBody).extract[MailChimpList]
  }

  def execute : MailChimpResultFuture = {
    val req = addAuth(:/(s"${cfg.apiEndpoint}/lists/$id").secure)
    httpToResult(req, transformer)
  }

}
