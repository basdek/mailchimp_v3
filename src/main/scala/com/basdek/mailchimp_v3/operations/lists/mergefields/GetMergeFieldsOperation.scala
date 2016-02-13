package com.basdek.mailchimp_v3.operations.lists.mergefields

import com.basdek.mailchimp_v3.dto.MailChimpListMergeFieldList
import com.basdek.mailchimp_v3.{MailChimpResultFuture, Config, SimpleAuthenticate}
import com.basdek.mailchimp_v3.operations.Operation
import com.ning.http.client.Response
import dispatch._, Defaults._
import org.json4s._, native.JsonMethods._

/**
  * Implementation of the /lists/{listId}/merge-fields operation.
  *
  * @param cfg A Config instance.
  * @param listId The listId from which you want to obtain the MergeFields.
  */
class GetMergeFieldsOperation(val cfg: Config, listId: String)
  extends Operation with SimpleAuthenticate {

  private def transformer(res : Response) : MailChimpListMergeFieldList = {
    val jsonResponse = res.getResponseBody
    parse(jsonResponse).extract[MailChimpListMergeFieldList]
  }

  def execute : MailChimpResultFuture = {
    val req = addAuth(:/(s"${cfg.apiEndpoint}/lists/$listId/merge-fields").secure)
    httpToResult(req, transformer)
  }

}
