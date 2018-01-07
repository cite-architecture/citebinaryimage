package edu.holycross.shot.citebinaryimage

import edu.holycross.shot.cite._
import org.scalatest.FlatSpec

class CiteBinaryImageServiceSpec extends FlatSpec {

  val baseUrl:String = "http://www.homermultitext.org/iipsrv?"
  val imagePath:String = "/project/homer/pyramidal/VenA/"
  val testImage1:Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013")
  val testImage2:Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.1,0.2,0.3,0.4")

  "A Cite Binary Image Service" should "compile" in {
    val groupLevel = CtsUrn("urn:cts:greekLit:tlg0012:")
    assert(groupLevel.textGroup == "tlg0012")
  }

  it should "have a protocol string" in {
      val bis:IIIFApi = IIIFApi(baseUrl, imagePath)
      assert(bis.protocolString == "iiifApi")
  }

  it should "construct an IIIFApi object given a baseUrl and Path" in {
      val bis:IIIFApi = IIIFApi(baseUrl, imagePath)
      val u:String = bis.serviceRequest(testImage1)
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/2000,/0/default.jpg"
      )
      println(u)
  }

  it should "construct an IIIFApi object from a URN with an ROI" in {
      val bis:IIIFApi = IIIFApi(baseUrl, imagePath)
      val u:String = bis.serviceRequest(testImage2)
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/pct:10,20,30,40/2000,/0/default.jpg"
      )
      println(u)
    }

  it should "construct an IIIFApi object given a baseUrl and Path and a width" in {
      val w:Int = 200
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath, width = Some(w))
      val u:String = bis.serviceRequest(testImage1)
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/200,/0/default.jpg"
      )
      println(u)
  }

  it should "construct an IIIFApi object given a baseUrl and Path and a maxWidth" in {
      val mw:Int = 300
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath, maxWidth = Some(mw))
      val u:String = bis.serviceRequest(testImage1)
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/!300,/0/default.jpg"
      )
      println(u)
  }

  it should "construct an IIIFApi object given a baseUrl and Path and a maxHeight" in {
      val mh:Int = 300
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath, maxHeight = Some(mh))
      val u:String = bis.serviceRequest(testImage1)
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/!,300/0/default.jpg"
      )
      println(u)
  }

  it should "construct an IIIFApi object given a baseUrl, imagePath, a maxWidth, and a maxHeight" in {
      val mw:Int = 600
      val mh:Int = 300
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath, maxWidth = Some(mw), maxHeight = Some(mh))
      val u:String = bis.serviceRequest(testImage1)
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/!600,300/0/default.jpg"
      )
      println(u)
  }

   it should "construct an IIIFApi objec for an image+ROI given a baseUrl, imagePath, a maxWidth, and a maxHeight" in {
      val mw:Int = 600
      val mh:Int = 300
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath, maxWidth = Some(mw), maxHeight = Some(mh))
      val u:String = bis.serviceRequest(testImage2)
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/pct:10,20,30,40/!600,300/0/default.jpg"
      )
      println(u)
  }

/*
  it should "construct an IIPUrl object given a baseUrl and Path" in {
      val bis:IIIFApi = IIIFApi(baseUrl, imagePath)
      val u:String = bis.serviceRequest(testImage1)
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/VenA/VA012RN-0013.tif&CVT=JPEG"
      )
  }

  it should "construct an IIPUrl object from a URN with an ROI" in {
      val bis:IIPUrl = IIPUrl(baseUrl, imagePath)
      val u:String = bis.serviceRequest(testImage2)
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/VenA/VA012RN-0013.tif&RGN=0.0551,0.2305,0.5115,0.494&CVT=JPEG"
      )
    }
    */

  it should "refuse to construct an IIIFApi object from a URN with an invalid ROI" in {
      val bis:IIIFApi = IIIFApi(baseUrl, imagePath)
      val badImage1:Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.2305,0.5115,0.494")
      val badImage2:Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@2,0.2305,0.5115,0.494")
      val badImage3:Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.8,0.2305,0.8,0.494")


      try {
         val u1:String = bis.serviceRequest(badImage1)      
         fail("Should have thrown exception.")
      } catch {
        case e:Throwable => 
      }

    }

}
