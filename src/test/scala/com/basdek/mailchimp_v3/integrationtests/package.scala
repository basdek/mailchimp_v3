package com.basdek.mailchimp_v3

import scala.concurrent.duration._

package object integrationtests {

  /**
    * This value configures how long we await results per test call.
    */
  val timeout = 3 seconds

  /**
    * @TODO: short term solution: mocking is better.
    */
  val testListId : String = "10870f2eb5"
}
