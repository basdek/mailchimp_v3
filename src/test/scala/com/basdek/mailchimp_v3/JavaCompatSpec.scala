package com.basdek.mailchimp_v3

import com.basdek.mailchimp_v3.compatibility.InstantiationFromJava
import com.basdek.mailchimp_v3.dto._
import org.scalatest.{Matchers, FlatSpec}

class JavaCompatSpec extends FlatSpec with Matchers {

  val ifj = new InstantiationFromJava()

  "MailChimpMember" should "be instantiable via aux constructor in Java" in {
    val test = ifj.instantiateMailChimpMember()
    test shouldBe an[MailChimpMember]
  }

  "MailChimpList" should "have it's sub resource _Contact instantiable in Java" in {
    val test = ifj.instantiateMailChimpList_Contact()
    test shouldBe an[MailChimpList_Contact]
  }

  it should "be instantiable via aux constructor in Java" in {
    val test = ifj.instantiateMailChimpList()
    test shouldBe an[MailChimpList]
  }

  "MailChimpListMergeField" should "be instantiable via aux constructor in Java" in {
    val test = ifj.instantiateMailChimpListMergeField();
    test shouldBe an[MailChimpListMergeField]
  }

  "MailChimpListSegment" should "be instantiable via aux constructor in Java" in {
    val test = ifj.instantiateMailChimpListSegment()
    test shouldBe an[MailChimpListSegment]
  }

  "MailChimpListSegment_Options" should "be instantiable via aux constructor in Java" in {
    val test = ifj.instantiateMailChimpListSegment_Options()
    test shouldBe an[MailChimpListSegment_Options]
  }

}
