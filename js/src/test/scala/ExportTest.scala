package edu.holycross.shot.cite

import org.scalatest._
import edu.holycross.shot.cite._

class ExportTest extends FlatSpec {

   "A Cite Binary Image Service" should "compile" in {
    val groupLevel = CtsUrn("urn:cts:greekLit:tlg0012:")
    assert(groupLevel.textGroup == "tlg0012")
  }

}
