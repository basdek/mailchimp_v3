package com.basdek.mailchimp_v3.integrationtests

import com.basdek.mailchimp_v3.MailChimpResultFuture
import com.basdek.mailchimp_v3.dto.{MailChimpList, MailChimpListList}
import com.basdek.mailchimp_v3.helpers.ConfigLoader
import com.basdek.mailchimp_v3.operations.lists.{GetListOperation, GetListsOperation}
import org.scalatest.{Matchers, FlatSpec}

import scala.concurrent.Await

class ListHandlingSpec extends FlatSpec with Matchers with ConfigLoader {

  "GetListsOperation" should "at the least work and have lists[], and total_items" in {
    val cfg = defaultCfg
    val operation = new GetListsOperation(cfg)
    val resultFuture : MailChimpResultFuture = operation.execute

    val result = Await.result(resultFuture, timeout)

    result.isRight should equal(true)

    val resultValue = result.right.get.asInstanceOf[MailChimpListList]

    resultValue.total_items should not equal null
    resultValue.lists should not equal null
  }

  "GetListOperation" should "be able to retrieve a list" in {
    val cfg = defaultCfg
    val operation = new GetListOperation(cfg, "10870f2eb5")
    val resultFuture : MailChimpResultFuture = operation.execute

    val result = Await.result(resultFuture, timeout)

    result.isRight should equal(true)

    val resultValue = result.right.get.asInstanceOf[MailChimpList]

    resultValue.name should equal("testlist_donotdelete")
  }
}
