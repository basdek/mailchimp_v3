package com.basdek.mailchimp_v3

package object dto {

  /**
    * Option alias, for fields that are not required according to the spec.
    *
    * These usually do not have a default value set in the DTO classes, users
    * will have to explicitly make them None, in case they do not want to provide
    * a value.
    *
    * @tparam A The type that needs to be wrapped.
    */
  type NonRequiredField[A] = Option[A]

  /**
    * Option alias, for fields that are readonly according to the spec.
    *
    * We make wrap them as Option, to allow using the same DTO class for receiving
    * a resource back from the API as well as creating / updating a resource.
    *
    * @tparam A The type that needs to be wrapped.
    */
  type ReadOnlyField[A] = Option[A]
}
