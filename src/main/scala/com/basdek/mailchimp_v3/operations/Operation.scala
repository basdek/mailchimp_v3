package com.basdek.mailchimp_v3.operations

import com.basdek.mailchimp_v3.dto.{MailChimpSuccess, MailChimpError}
import com.basdek.mailchimp_v3.{Config, MailChimpResultFuture}
import com.ning.http.client.Response
import dispatch._, Defaults._
import org.json4s._
import org.json4s.native.JsonMethods._


/**
  * This is a basic Operation with some utilities / generalized stuff for an
  * operation on the MailChimp api.
  */
trait Operation {

  def execute : MailChimpResultFuture

  /**
    * Expect the Config to be provided via the descendants / implementors of this
    * trait.
    */
  implicit val cfg : Config


  implicit val formats : Formats = DefaultFormats + MailChimpError.serializer


  /**
    * Converts a Dispatch Req to either a MailChimpError case class or any
    * desired subtype of the trait MailChimp.
    *
    * @param req The Request that is to be transformed to a result future.
    * @param transformer This transformer is applied to Req in case of HTTP success.
    * @tparam A The type where transformer => A (must extend MailChimpSuccess).
    * @return A MailChimpResultFuture (with either the transformation or error).
    */
  def httpToResult[A <: MailChimpSuccess]
  (req: Req, transformer : (Response) => A) : MailChimpResultFuture = {
    Http(req).map {
      (res : Response) => res.getStatusCode match {
        case 200 => Right(transformer.apply(res))
        case _ => Left(parse(res.getResponseBody()).extract[MailChimpError]) //@TODO: reporting, logging, etc.
      }
    }
  }


}
