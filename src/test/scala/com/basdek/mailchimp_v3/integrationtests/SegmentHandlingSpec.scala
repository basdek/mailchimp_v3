package com.basdek.mailchimp_v3.integrationtests

import java.util.UUID

import com.basdek.mailchimp_v3.dto.{TextMergeCondition, MailChimpListSegment_Options, MailChimpListSegment}
import com.basdek.mailchimp_v3.helpers.ConfigLoader
import com.basdek.mailchimp_v3.operations.lists.segments.{GetSegmentsOperation, AddSegmentOperation}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.Await

class SegmentHandlingSpec extends FlatSpec with Matchers with ConfigLoader {

  "AddSegmentOperation" should "add a segment to a list" in {

    val cfg = defaultCfg
    val data = MailChimpListSegment(
      name = s"Segment${UUID.randomUUID()}",
      static_segment = None,
      options = Option(
        MailChimpListSegment_Options(
          _match = "all",
          conditions = TextMergeCondition("FNAME", "contains", "albus") :: Nil
        )
      )
    )
    val operation = new AddSegmentOperation(cfg, testListId, data)
    val result = operation.execute

    val resultValue = Await.result(result, timeout)

    resultValue.isRight shouldBe true
  }

  "GetSegmentsOperation" should "work" in {

    val cfg = defaultCfg
    val operation = new GetSegmentsOperation(cfg, testListId)
    val result = operation.execute

    val resultValue = Await.result(result, timeout)

    resultValue.isRight shouldBe true
  }

}
