package com.kingjoshdavid

import org.scalatest.{Matchers, FunSuite}


class junkYouCanDoTest extends FunSuite with Matchers {
  test("junk") {
    val expectedSymbol: Symbol = 'okay
    (new `junk you can do`().___? == 'okay) should be(true)
  }

}

class `junk you can do` {
  def ___? : Symbol = 'okay
}
