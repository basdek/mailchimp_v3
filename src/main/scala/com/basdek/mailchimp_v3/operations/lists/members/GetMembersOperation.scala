package com.basdek.mailchimp_v3.operations.lists.members

import com.basdek.mailchimp_v3.dto.{MailChimpMemberList, MailChimpSuccess}
import com.basdek.mailchimp_v3.{MailChimpResultFuture, Config, SimpleAuthenticate}
import com.basdek.mailchimp_v3.operations.Operation
import com.ning.http.client.Response
import dispatch._, Defaults._
import org.json4s._, native.JsonMethods._

class GetMembersOperation(val cfg: Config, val listId : String)
  extends Operation with SimpleAuthenticate {

  private def transformer (res : Response) : MailChimpMemberList = {
    val responseBody = res.getResponseBody
    parse(responseBody).extract[MailChimpMemberList]
  }

  def execute : MailChimpResultFuture = {
    val req = addAuth(:/(s"${cfg.apiEndpoint}/lists/$listId/members").secure)
    httpToResult(req, transformer)
  }

}
