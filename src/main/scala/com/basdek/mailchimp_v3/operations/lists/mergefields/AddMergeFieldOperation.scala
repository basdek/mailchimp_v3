package com.basdek.mailchimp_v3.operations.lists.mergefields

import com.basdek.mailchimp_v3.dto.MailChimpListMergeField
import com.basdek.mailchimp_v3.{MailChimpResultFuture, Config, SimpleAuthenticate}
import com.basdek.mailchimp_v3.operations.Operation
import com.ning.http.client.Response
import dispatch._, Defaults._
import org.json4s._, native.JsonMethods._, native.Serialization.{write => jsonWrite}

/**
  * Implementation of the POST /lists/{listId}/merge-fields operation.
  *
  * @param cfg A Config instance.
  * @param listId The id of the list where you want to add a MergeField.
  * @param data An instance of the MailChimpListMergeField DTO object.
  */
class AddMergeFieldOperation
  (val cfg: Config, listId: String, data: MailChimpListMergeField)
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
