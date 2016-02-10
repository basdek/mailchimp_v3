package com.basdek.mailchimp_v3.operations.lists

import com.basdek.mailchimp_v3.dto.MailChimpList
import com.basdek.mailchimp_v3.{Config, MailChimpResultFuture, SimpleAuthenticate}
import com.basdek.mailchimp_v3.operations.Operation
import com.ning.http.client.Response
import dispatch._, Defaults._
import org.json4s._, native.JsonMethods._, native.Serialization.{write => jsonWrite}

/**
  * Implementation of the POST /lists operation.
  * @param cfg A Config instance.
  * @param data A list DTO object.
  */
class CreateListOperation(val cfg: Config, val data : MailChimpList)
  extends Operation with SimpleAuthenticate {

  private def transformer(res: Response) : MailChimpList = {
    val responseBody = res.getResponseBody
    parse(responseBody).extract[MailChimpList]
  }

  def execute : MailChimpResultFuture = {

    val req = addAuth(:/(s"${cfg.apiEndpoint}/lists").POST.secure)
      .setBody(jsonWrite(data))
      .setContentType("application/json", "utf-8")

    httpToResult(req, transformer)
  }
}
