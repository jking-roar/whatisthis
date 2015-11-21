package com.kingjoshdavid.throwing

import org.scalatest.{FunSuite, Matchers}

class MapGetOrElseWithFold extends FunSuite with Matchers{
  val something = Some('thing)
  val nothing = None

  test("map and get or else") {
    something.map(_ => "pizza").getOrElse(throw new IllegalArgumentException) shouldEqual "pizza"
    intercept[IllegalArgumentException] {
      nothing.map(_ => "pizza").getOrElse(throw new IllegalArgumentException)
    }

    something.map(_ => "pizza").getOrElse("yogurt") shouldEqual "pizza"
    nothing.map(_ => "pizza").getOrElse("yogurt") shouldEqual "yogurt"

  }

  test("fold equivalent to map and get or else") {
    something.fold(throw new IllegalArgumentException)(_ => "pizza") shouldEqual "pizza"
    intercept[IllegalArgumentException] {
      nothing.fold(throw new IllegalArgumentException)(_ => "pizza")
    }

    something.fold("yogurt")(_ => "pizza") shouldEqual "pizza"
    nothing.fold("yogurt")(_ => "pizza") shouldEqual "yogurt"
  }
}
