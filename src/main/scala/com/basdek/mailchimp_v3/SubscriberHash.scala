package com.basdek.mailchimp_v3

import java.security.MessageDigest

object SubscriberHash {

  /**
    * Generates a subscriber-hash from an email-address.
    *
    * @param in The input (will be lowercased before hash)
    * @return A subscriber-hash.
    */
  def hash(in : String) : String = {
    MessageDigest.getInstance("MD5").digest(in.toLowerCase()
      .getBytes).map("%02x".format(_)).mkString
  }

}
