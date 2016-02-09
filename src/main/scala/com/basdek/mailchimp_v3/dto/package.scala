package com.basdek.mailchimp_v3

package object dto {
  type NonRequiredField[A] = Option[A]
  type ReadOnlyField[A] = Option[A]
}
