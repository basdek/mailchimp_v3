package com.basdek.mailchimp_v3.operations.lists.segments

import com.basdek.mailchimp_v3.{Config, MailChimpResultFuture, SimpleAuthenticate}
import com.basdek.mailchimp_v3.dto.MailChimpListSegmentList
import com.basdek.mailchimp_v3.operations.Operation
import com.ning.http.client.Response
import dispatch._, Defaults._
import org.json4s._, native.JsonMethods._

class GetSegmentsOperation(val cfg: Config, listId: String) extends Operation with SimpleAuthenticate {

  private def transformer(res: Response) : MailChimpListSegmentList = {
    val responseBody = res.getResponseBody
    parse(responseBody).extract[MailChimpListSegmentList]
  }

  def execute: MailChimpResultFuture = {
    val req = addAuth(:/(s"${cfg.apiEndpoint}/lists/$listId/segments").secure)
    httpToResult(req, transformer)
  }
}
