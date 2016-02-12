package com.basdek.mailchimp_v3.operations.lists.interestcategories

import com.basdek.mailchimp_v3.dto.MailChimpInterestCategoryList
import com.basdek.mailchimp_v3.operations.Operation
import com.basdek.mailchimp_v3.{Config, MailChimpResultFuture, SimpleAuthenticate}
import com.ning.http.client.Response
import dispatch._
import org.json4s._
import org.json4s.native.JsonMethods._

class GetInterestCategoriesOperation(val cfg: Config, listId: String)
  extends Operation with SimpleAuthenticate {

  private def transformer(res : Response) : MailChimpInterestCategoryList = {
    val responseBody = res.getResponseBody()
    parse(responseBody).extract[MailChimpInterestCategoryList]
  }

  def execute : MailChimpResultFuture = {
    val req = addAuth(:/(s"${cfg.apiEndpoint}/lists/$listId/interest-categories"))
    httpToResult(req, transformer)
  }
}
