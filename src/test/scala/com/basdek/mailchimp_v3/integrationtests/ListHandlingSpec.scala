package com.basdek.mailchimp_v3.integrationtests

import java.util.UUID

import com.basdek.mailchimp_v3.MailChimpResultFuture
import com.basdek.mailchimp_v3.dto.{MailChimpList_Contact, MailChimpList_CampaignDefaults, MailChimpList, MailChimpListList}
import com.basdek.mailchimp_v3.helpers.ConfigLoader
import com.basdek.mailchimp_v3.operations.lists.{CreateListOperation, GetListOperation, GetListsOperation}
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
    val operation = new GetListOperation(cfg, testListId)
    val resultFuture : MailChimpResultFuture = operation.execute

    val result = Await.result(resultFuture, timeout)

    result.isRight should equal(true)

    val resultValue = result.right.get.asInstanceOf[MailChimpList]

    resultValue.name should equal("testlist_donotdelete")
  }

  "CreateListOperation" should "create a list" in {
    val cfg = defaultCfg

    val lstName = s"testlist${UUID.randomUUID().toString}"
    val data : MailChimpList = MailChimpList(
      name = lstName,
      contact = MailChimpList_Contact(
        company = "Gambol and Japes Wizarding Joke Shop",
        address1 = "Diagon Alley 12",
        address2 = None,
        city = "London",
        state = "GL",
        zip = "SW1A0AA",
        country = "UK",
        phone = None
      ),
      permission_reminder = "You did not choose it, it choose you.",
      use_archive_bar = true,
      campaign_defaults = MailChimpList_CampaignDefaults(
        from_name = "Gambol and Japes",
        from_email = "gaj@basdek.com",
        subject = "Some subject",
        language = "English"
      ),
      notify_on_subscribe = None,
      notify_on_unsubscribe = None,
      email_type_option = false
    )

    val operation = new CreateListOperation(cfg, data)
    val resultFuture : MailChimpResultFuture = operation.execute

    val result = Await.result(resultFuture, timeout)

    result.isRight should equal (true)

    val resultValue = result.right.get.asInstanceOf[MailChimpList]

    resultValue.id should not be null
    resultValue.id should not be empty
  }
}
