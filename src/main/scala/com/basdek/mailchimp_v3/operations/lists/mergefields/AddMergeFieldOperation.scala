package com.basdek.mailchimp_v3.operations.lists.mergefields

import com.basdek.mailchimp_v3.dto.MailChimpListMergeField
import com.basdek.mailchimp_v3.{MailChimpResultFuture, Config, SimpleAuthenticate}
import com.basdek.mailchimp_v3.operations.Operation
import com.ning.http.client.Response
import dispatch._, Defaults._
import org.json4s._, native.JsonMethods._, native.Serialization.{write => jsonWrite}

class AddMergeFieldOperation(val cfg: Config, listId: String, data: MailChimpListMergeField)
  extends Operation with SimpleAuthenticate {

  private def transformer(res: Response) : MailChimpListMergeField = {
    val responseBody = res.getResponseBody
    parse(responseBody).extract[MailChimpListMergeField]
  }

  def execute : MailChimpResultFuture = {
    val req = addAuth(:/(s"${cfg.apiEndpoint}/lists/$listId/merge-fields").POST.secure)
      .setBody(jsonWrite(data))
      .setContentType("application/json", "utf-8")
    httpToResult(req, transformer)
  }
}
