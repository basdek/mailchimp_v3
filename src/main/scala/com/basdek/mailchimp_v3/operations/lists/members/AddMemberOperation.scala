package com.basdek.mailchimp_v3.operations.lists.members

import com.basdek.mailchimp_v3.dto.MailChimpMember
import com.basdek.mailchimp_v3.{MailChimpResultFuture, Config, SimpleAuthenticate}
import com.basdek.mailchimp_v3.operations.Operation
import com.ning.http.client.Response
import dispatch._, Defaults._
import org.json4s._, native.JsonMethods._, native.Serialization.{write => jsonWrite}

class AddMemberOperation(val cfg: Config, listId: String, data: MailChimpMember)
  extends Operation with SimpleAuthenticate {

  private def transformer(res : Response) : MailChimpMember = {
    val responseBody = res.getResponseBody
    parse(responseBody).extract[MailChimpMember]
  }

  def execute : MailChimpResultFuture = {

    val req = addAuth(:/(s"${cfg.apiEndpoint}/lists/$listId/members").POST.secure)
      .setBody(jsonWrite(data))
      .setContentType("application/json", "utf-8")

    httpToResult(req, transformer)
  }

}
