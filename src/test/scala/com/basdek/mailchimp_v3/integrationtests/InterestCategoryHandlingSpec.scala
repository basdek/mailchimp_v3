package com.basdek.mailchimp_v3.integrationtests

import java.util.UUID

import com.basdek.mailchimp_v3.dto.MailChimpInterestCategory
import com.basdek.mailchimp_v3.helpers.ConfigLoader
import com.basdek.mailchimp_v3.operations.lists.interestcategories.{GetInterestCategoriesOperation, AddInterestCategoryOperation}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.Await

class InterestCategoryHandlingSpec extends FlatSpec with Matchers with ConfigLoader  {
  "AddInterestCategoryOperation" should "work" in {
    val cfg = defaultCfg
    val data = MailChimpInterestCategory(
      title = s"testCat${UUID.randomUUID()}",
      display_order = None,
      _type = "hidden"
    )
    val operation = new AddInterestCategoryOperation(cfg, testListId, data)

    val result = operation.execute

    val resultValue = Await.result(result, timeout)

    resultValue.isRight should be (true)

  }

  "GetInterestCategoriesOperation" should "work" in {
    val cfg = defaultCfg
    val operation = new GetInterestCategoriesOperation(cfg, testListId)
    val result = operation.execute

    val resultValue = Await.result(result, timeout)

    resultValue.isRight should be (true)

  }
}
