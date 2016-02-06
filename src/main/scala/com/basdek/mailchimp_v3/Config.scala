package com.basdek.mailchimp_v3

/**
  * This is the configuration to tie basic credentials to the operations.
  *
  * Instantiate this with your apiKey and the url of the endpoint and pass it
  * to any Operation you want to execute.
  *
  * @param apiKey Your api key.
  * @param apiEndpoint The endpoint for your account: usX.api.mailchimp.com/3.0
  */
class Config(val apiKey : String, val apiEndpoint : String) {
}
