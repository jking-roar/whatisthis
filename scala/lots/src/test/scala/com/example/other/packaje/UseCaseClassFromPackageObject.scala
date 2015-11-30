package com.example.other.packaje

import com.example.packaje.{CaseClassWithOverloads, IntWithString}

object UseCaseClassFromPackageObject {
  def main(args: Array[String]) = {
    println(CaseClassWithOverloads(107, "a"))
    println(CaseClassWithOverloads(new IntWithString(21, "b")))
  }
}
