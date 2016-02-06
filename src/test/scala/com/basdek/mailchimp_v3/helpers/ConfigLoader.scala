package com.basdek.mailchimp_v3.helpers

import scala.io.Source
import com.basdek.mailchimp_v3.Config
import com.typesafe.config.ConfigFactory

/**
  * This trait helps loading a Config based upon ENV-vars (for test purposes,
  * one is free to do it in whatever way one sees fit when using the lib, through
  * passing a Config instance to an Operation.)
  */
trait ConfigLoader {

  private val confSource = Source.fromURL(getClass.getResource("/TestConfig.conf"))
  private val conf = ConfigFactory.parseReader(confSource.bufferedReader()).resolve()

  /**
    * Returns the api endpoint.
    * @return The url for the api endpoint.
    */
  def apiEndpoint : String = conf.getString("mailchimp.api_endpoint")


  /**
    * Returns the api key.
    * @return An api key.
    */
  def apiKey : String = conf.getString("mailchimp.api_key")

  /**
    * Creates a default Config (loading the ENV vars).
    * @return A Config instance.
    */
  def defaultCfg : Config = new Config(this.apiEndpoint, this.apiKey)

}
