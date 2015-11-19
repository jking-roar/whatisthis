import org.scalatest.{Matchers, FunSuite}

import scala.language.implicitConversions

//noinspection SpellCheckingInspection
class TernarizeTest extends FunSuite with Matchers {
  class IfTrue[A](b: => Boolean, t: => A) {
    def |(f: => A) = if (b) t else f
  }

  class MakeIfTrue(b: => Boolean) {
    def ?[A](t: => A) = new IfTrue[A](b, t)
  }

  implicit def autoMakeIfTrue(b: => Boolean): MakeIfTrue = new MakeIfTrue(b)

  test("true should go to true") {
    def test = {
      println("returning true")
      true
    }
    println("starting test")
    val result = test ? "true" | "false"
    println("result is " + result)

    result should be("true")
  }

}
