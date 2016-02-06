package com.basdek.mailchimp_v3

import dispatch.Req

/**
  * This is for later OAUTH implementation.
  */
trait Authenticate

trait SimpleAuthenticate extends Authenticate {

  implicit val cfg : Config
  private val user = "anyString"

  /**
    * Adds authentication to a dispatch Req.
    * @param in The dispatch Req.
    * @return The altered Req, with basic auth.
    */
  def addAuth(in : Req) : Req = in.as(user, cfg.apiKey)
}
