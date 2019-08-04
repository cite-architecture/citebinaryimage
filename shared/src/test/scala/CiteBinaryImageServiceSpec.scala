package edu.holycross.shot.citebinaryimage

import edu.holycross.shot.cite._
import org.scalatest.FlatSpec

class CiteBinaryImageServiceSpec extends FlatSpec {

  val baseUrl:String = "http://www.homermultitext.org/iipsrv?"
  val imagePath:String = "/project/homer/pyramidal/VenA/"
  val testImage1:Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013")
  val testImage2:Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.1,0.2,0.3,0.4")
  val testImage3:Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.12345,0.22345,0.32345,0.42345")

  "A Cite Binary Image Service" should "compile" in {
    val groupLevel = CtsUrn("urn:cts:greekLit:tlg0012:")
    assert(groupLevel.textGroup == "tlg0012")
  }

  it should "have a protocol string" in {
      val bis:IIIFApi = IIIFApi(baseUrl, imagePath)
      assert(bis.protocolString == "iiifApi")
  }


}
