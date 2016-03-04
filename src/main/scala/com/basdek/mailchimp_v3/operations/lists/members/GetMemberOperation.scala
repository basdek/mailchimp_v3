package com.basdek.mailchimp_v3.operations.lists.members

import com.basdek.mailchimp_v3.dto.MailChimpMember
import com.basdek.mailchimp_v3.{MailChimpResultFuture, Config, SimpleAuthenticate}
import com.basdek.mailchimp_v3.operations.Operation
import com.ning.http.client.Response
import dispatch._, Defaults._
import org.json4s._, native.JsonMethods._

/**
  * Implementation of the GET /lists/{listId}/members/{subscriberHash}
  *
  * @param cfg A Config instance.
  * @param listId The id of the list.
  * @param subscriberHash The subscriberhash.
  */
class GetMemberOperation(val cfg: Config, listId: String, subscriberHash: String)
  extends Operation with SimpleAuthenticate {

  private def transformer(res: Response) : MailChimpMember = {
    val responseBody = res.getResponseBody
    parse(responseBody).extract[MailChimpMember]
  }

  def execute : MailChimpResultFuture = {
    val req = addAuth(:/(s"${cfg.apiEndpoint}/lists/$listId/members/$subscriberHash").secure)
    httpToResult(req, transformer)
  }

}
