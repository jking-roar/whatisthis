package com.kingjoshdavid.silly

import org.scalatest.{FunSuite, Matchers}

class SpaceInVariableName extends FunSuite with Matchers {
  test("spaces in names") {
    val `are spaces okay in variable names?`: Symbol = new `backticks allow you to write any junk in here!`().`is that okay?`
    `are spaces okay in variable names?` shouldEqual 'iGuessSo
  }
}

class `backticks allow you to write any junk in here!` {
  def `is that okay?`: Symbol = 'iGuessSo
}
