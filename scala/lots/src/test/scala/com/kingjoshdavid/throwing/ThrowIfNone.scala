package com.kingjoshdavid.throwing

import org.scalatest.{FunSuite, Matchers}

class ThrowIfNone extends FunSuite with Matchers {
  val something = Some('value)
  val nothing = None


  def usingMatching[Thing](maybeThing: Option[Thing]): Thing = {
    maybeThing match {
      case Some(thing) => thing
      case None => throw new IllegalArgumentException
    }
  }

  def usingGetOrElse[Thing](maybeThing: Option[Thing]): Thing = {
    maybeThing.getOrElse(throw new IllegalArgumentException)
  }

  def usingCheck[Thing](maybeThing: Option[Thing]): Thing = {
    if (maybeThing.isEmpty)
      throw new IllegalArgumentException
    maybeThing.get
  }


  test("get the thing or throw") {
    withClue("usingMatching") {
      check(usingMatching)
    }
    withClue("usingGetOrElse") {
      check(usingGetOrElse)
    }
    withClue("usingCheck") {
      check(usingCheck)
    }
  }

  def check(method: (Option[Any]) => Any): Unit = {
    method(something) shouldEqual 'value
    intercept[IllegalArgumentException] {
      method(nothing)
    }
  }
}
