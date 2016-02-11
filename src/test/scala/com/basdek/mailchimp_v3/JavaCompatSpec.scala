package com.basdek.mailchimp_v3

import com.basdek.mailchimp_v3.compatibility.InstantiationFromJava
import com.basdek.mailchimp_v3.dto.{MailChimpList, MailChimpList_Contact, MailChimpMember}
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

}
