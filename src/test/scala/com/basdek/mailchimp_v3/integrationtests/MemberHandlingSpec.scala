package com.basdek.mailchimp_v3.integrationtests

import java.util.UUID

import com.basdek.mailchimp_v3.MailChimpResultFuture
import com.basdek.mailchimp_v3.dto.{MailChimpMember, MailChimpMemberList}
import com.basdek.mailchimp_v3.helpers.ConfigLoader
import com.basdek.mailchimp_v3.operations.lists.members.{AddMemberOperation, GetMembersOperation}
import org.scalatest.{Matchers, FlatSpec}

import scala.concurrent.Await

class MemberHandlingSpec extends FlatSpec with Matchers with ConfigLoader {

  "GetMembersOperation" should "give a list with members back" in {
    val cfg = defaultCfg
    val operation = new GetMembersOperation(cfg, testListId)
    val resultFuture : MailChimpResultFuture = operation.execute

    val result = Await.result(resultFuture, timeout)

    result.isRight should equal(true)

    val resultValue = result.right.get.asInstanceOf[MailChimpMemberList]

    (resultValue.members.size > 0) should equal(true)
  }

  "AddMemberOperation" should "add a member to a list" in {

    val cfg = defaultCfg
    val data = MailChimpMember(
      email_address = s"albus.dumbledore${UUID.randomUUID}@basdek.com",
      status = "subscribed",
      merge_fields = None,
      interests = None,
      language = "English",
      vip = false,
      location = None
    )

    val operation = new AddMemberOperation(cfg, testListId, data)
    val resultFuture : MailChimpResultFuture = operation.execute

    val result = Await.result(resultFuture, timeout)

    result.isRight should equal (true)
  }
}
