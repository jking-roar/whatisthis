package com.example

package object packaje {
  case class CaseClassWithOverloads(int: Int, string: String) {
    def this(combined: IntWithString) {
      this(combined.int, combined.string)
    }
  }
  object CaseClassWithOverloads {
    def apply(combined: IntWithString): CaseClassWithOverloads = {
      new CaseClassWithOverloads(combined)
    }
  }

  case class IntWithString(int: Int, string: String)
}
