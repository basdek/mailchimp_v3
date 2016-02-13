package com.basdek.mailchimp_v3.operations.lists.segments

import com.basdek.mailchimp_v3.dto.MailChimpListSegment
import com.basdek.mailchimp_v3.{MailChimpResultFuture, Config, SimpleAuthenticate}
import com.basdek.mailchimp_v3.operations.Operation
import com.ning.http.client.Response
import dispatch._
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization.{write => jsonWrite}

class AddSegmentOperation(val cfg: Config, listId: String, data: MailChimpListSegment)
  extends Operation with SimpleAuthenticate {

  private def transformer(res : Response) : MailChimpListSegment = {
    val responseBody = res.getResponseBody
    parse(responseBody).extract[MailChimpListSegment]
  }

  def execute : MailChimpResultFuture = {
    val req = addAuth(:/(s"${cfg.apiEndpoint}/lists/$listId/segments").POST.secure)
      .setBody(jsonWrite(data))
      .setContentType("application/json", "utf-8")
    httpToResult(req, transformer)
  }
}
