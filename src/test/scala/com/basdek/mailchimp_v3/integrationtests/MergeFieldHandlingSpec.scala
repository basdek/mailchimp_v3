package com.basdek.mailchimp_v3.integrationtests

import com.basdek.mailchimp_v3.dto.MailChimpListMergeField
import com.basdek.mailchimp_v3.helpers.ConfigLoader
import com.basdek.mailchimp_v3.operations.lists.mergefields.{AddMergeFieldOperation, GetMergeFieldsOperation}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.Await

class MergeFieldHandlingSpec extends FlatSpec with Matchers with ConfigLoader {

  "GetMergeFieldsOperation" should "work" in {

    val config = defaultCfg
    val operation = new GetMergeFieldsOperation(config, testListId)
    val result = operation.execute

    val resultValue = Await.result(result, timeout)

    resultValue.isRight should be(true)
  }

  "AddMergeFieldOperation" should "work" in {

    val config = defaultCfg
    val data = MailChimpListMergeField(
      tag = "TAG",
      name = "tag",
      _type = "text",
      required =  None,
      default_value = None,
      public = None,
      display_order = None,
      help_text =  None
    )
    val operation = new AddMergeFieldOperation(config, testListId, data)
    val result = operation.execute

    val resultValue = Await.result(result, timeout)

    resultValue.isRight should be (true)
  }

}
