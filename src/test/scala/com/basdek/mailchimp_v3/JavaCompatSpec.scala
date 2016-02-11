package com.basdek.mailchimp_v3

import com.basdek.mailchimp_v3.compatibility.InstantiationFromJava
import com.basdek.mailchimp_v3.dto.MailChimpMember
import org.scalatest.{Matchers, FlatSpec}

class JavaCompatSpec extends FlatSpec with Matchers {

  val ifj = new InstantiationFromJava()

  "MailChimpMember" should "be instantiateable via aux constructor in Java" in {
    val test = ifj.instantiateMailChimpMember()
    test shouldBe an[MailChimpMember]
  }

}
