package edu.holycross.shot.citebinaryimage

import edu.holycross.shot.cite._
import scala.scalajs.js

object Main extends js.JSApp {
  def main(): Unit = {

    val mini = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:1.1")
    println(mini)

    println("Match function: " + mini.~~(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1")))
  }
}
