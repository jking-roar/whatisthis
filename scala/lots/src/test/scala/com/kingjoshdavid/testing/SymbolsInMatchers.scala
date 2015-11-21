package com.kingjoshdavid.testing

import org.scalatest.{FunSuite, Matchers}

class SymbolsInMatchers extends FunSuite with Matchers {
  test("symbol equality") {
    'okay == 'okay should be(true)
  }

  ignore("symbols are special in be word") {
    'okay should be('okay) // fails due to ok having no .isOkay or .okay method
  }

  test("don't test this way, either (no assertion is made)") {
    'okay should be eq 'okay
    'okay should be eq 'whatever
    'okay should be eq 'whatever shouldEqual false
    'okay should be eq 'okay shouldEqual false

    // cause:
    val symbolClazz = classOf[Symbol]
    'okay.getClass shouldEqual symbolClazz
    val resultClazz = classOf[ResultOfBeWordForAny[_]]
    ('okay should be).getClass shouldEqual resultClazz
  }

  test("match to symbols this way") {
    'okay shouldEqual 'okay
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
