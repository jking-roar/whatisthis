package com.example

object RunWithCompanion {
  def main(args: Array[String]): Unit = new RunWithCompanion().run()
}

class RunWithCompanion {
  def run(): Unit = println("hi")
}