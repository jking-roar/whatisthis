package com.kingjoshdavid

import org.scalatest.{Matchers, FunSuite}


class ThingsDoneInScalaTest extends FunSuite with Matchers {
  test("spaces in names") {
    new `junk you can do`().`what is okay?`
  }

  test("symbol equality") {
    val expectedSymbol: Symbol = 'okay
    val ok = new `junk you can do`().`what is okay?`
    ok == 'okay should be(true)
  }

  ignore("symbols are special in be word") {
    val expectedSymbol: Symbol = 'okay
    val ok: Symbol = new `junk you can do`().`what is okay?`
    ok should be ('okay)
  }

  test("match to symbols this way") {
    val expectedSymbol: Symbol = 'okay
    val ok: Symbol = new `junk you can do`().`what is okay?`
    //noinspection ComparingUnrelatedTypes
    ok should be eq 'okay
  }

  test("symbols in be word access state") {
    case class thing(tested: Boolean, isAnimal: Boolean = false)

    val testedThing: thing = thing(tested = true)
    val unTestedThing: thing = thing(tested = false)
    val testedAnimal: thing = thing(tested = true, isAnimal = true)

    testedThing should be('tested)
    testedThing.should(be('tested))

    unTestedThing should not be 'tested
    unTestedThing.should(not).be('tested)

    testedAnimal should be an 'animal
    testedAnimal.should(be).an('animal)

    testedThing should not be an('animal)
    testedThing.should(not).be(an('animal))
  }


}

class `junk you can do` {
  def `what is okay?`: Symbol = 'okay
}
