package com.basdek.mailchimp_v3.integrationtests

import com.basdek.mailchimp_v3.MailChimpResultFuture
import com.basdek.mailchimp_v3.dto.MailChimpMemberList
import com.basdek.mailchimp_v3.helpers.ConfigLoader
import com.basdek.mailchimp_v3.operations.lists.members.GetMembersOperation
import org.scalatest.{Matchers, FlatSpec}

import scala.concurrent.Await

class MemberHandlingSpec extends FlatSpec with Matchers with ConfigLoader {

  "GetMembersOperation" should "give a list with members back" in {
    val cfg = defaultCfg
    val operation = new GetMembersOperation(cfg, "10870f2eb5")
    val resultFuture : MailChimpResultFuture = operation.execute

    val result = Await.result(resultFuture, timeout)

    result.isRight should equal(true)

    val resultValue = result.right.get.asInstanceOf[MailChimpMemberList]

    (resultValue.members.size > 0) should equal(true)
  }
}
