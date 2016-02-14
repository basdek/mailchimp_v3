package com.basdek.mailchimp_v3.integrationtests

import java.util.UUID

import com.basdek.mailchimp_v3.{SubscriberHash, MailChimpResultFuture}
import com.basdek.mailchimp_v3.dto.{MailChimpMember, MailChimpMemberList}
import com.basdek.mailchimp_v3.helpers.ConfigLoader
import com.basdek.mailchimp_v3.operations.lists.members.{EditMemberOperation, GetMemberOperation, AddMemberOperation, GetMembersOperation}
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

  "GetMemberOperation" should "retrieve a member" in {

    val cfg = defaultCfg
    val operation = new GetMemberOperation(
      cfg, testListId, SubscriberHash.hash("basdek@basdek.com")
    )
    val result = operation.execute

    val resultValue = Await.result(result, timeout)

    resultValue.isRight should be (true)
  }

  //@TODO: this testscenario stinks.
  "EditMemberOperation" should "be able to edit a member" in {

    val cfg = defaultCfg

    val member = Await.result(new GetMemberOperation(
      cfg, testListId, SubscriberHash.hash("basdek@basdek.com")
    ).execute, timeout)

    if(member.isLeft) fail("Member not found")

    val old = member.right.get.asInstanceOf[MailChimpMember]

    val data = MailChimpMember(
      email_address = old.email_address,
      email_type = old.email_type,
      status = old.status,
      merge_fields = old.merge_fields,
      interests =  old.interests,
      language = old.language,
      vip = !old.vip, //Switch that boolean...
      location = old.location
    )

    val operation = new EditMemberOperation(
      cfg, testListId, SubscriberHash.hash(old.email_address), data
    )

    val result = operation.execute

    val resultValue = Await.result(result, timeout)

    resultValue.isRight shouldBe true

    resultValue.right.get.asInstanceOf[MailChimpMember].vip should not be old.vip
  }
}
