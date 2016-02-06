package com.basdek.mailchimp_v3.integrationtests

import com.basdek.mailchimp_v3.operations.AuthTestOperation
import com.basdek.mailchimp_v3.{MailChimpResultFuture, Config}
import com.basdek.mailchimp_v3.helpers.ConfigLoader
import org.scalatest.{FlatSpec, Matchers}
import scala.concurrent.Await
import scala.concurrent.duration._

class BasicErrorHandlingSpec extends FlatSpec with Matchers with ConfigLoader {

  "Operation" should "process a non 200 status code correctly" in {

    val cfg = new Config(apiEndpoint = apiEndpoint, apiKey = "aNonValidKey")
    val operation = new AuthTestOperation(cfg)
    val resultFuture : MailChimpResultFuture = operation.execute

    val result = Await.result(resultFuture, 2 seconds)

    result.isLeft should equal(true)
    result.left.get.status should not equal(200)
  }

}
