package edu.holycross.shot.citebinaryimage

import edu.holycross.shot.cite._
import org.scalatest.FlatSpec

class IIIFServiceSpec extends FlatSpec {

  val baseUrl:String = "http://www.homermultitext.org/iipsrv?"
  val imagePath:String = "/project/homer/pyramidal/VenA/"
  val testImage1:Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013")
  val testImage2:Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.1,0.2,0.3,0.4")
  val testImage3:Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.12345,0.22345,0.32345,0.42345")

  "A IIIF Service" should "construct an IIIFApi object given a baseUrl and Path" in {
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
        (u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/pct:10.0,20.0,30.0,40.0/2000,/0/default.jpg")
        ||
        (u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/pct:10,20,30,40/2000,/0/default.jpg")
      )
      println(u)
    }

  it should "construct an IIIFApi object given a baseUrl and Path and a width" in {
      val w:Int = 200
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val u:String = bis.serviceRequest(testImage1, width = Some(w))
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/200,/0/default.jpg"
      )
      println(u)
  }

  it should "construct an IIIFApi object given a baseUrl and Path and a maxWidth" in {
      val mw:Int = 300
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val u:String = bis.serviceRequest(testImage1, maxWidth = Some(mw))
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/!300,/0/default.jpg"
      )
      println(u)
  }

  it should "construct an IIIFApi object given a baseUrl and Path and a maxHeight" in {
      val mh:Int = 300
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val u:String = bis.serviceRequest(testImage1, maxHeight = Some(mh))
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/!,300/0/default.jpg"
      )
      println(u)
  }

  it should "construct an IIIFApi object given a baseUrl, imagePath, a maxWidth, and a maxHeight" in {
      val mw:Int = 600
      val mh:Int = 300
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val u:String = bis.serviceRequest(testImage1, maxWidth = Some(mw), maxHeight = Some(mh))
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/!600,300/0/default.jpg"
      )
      println(u)
  }

   it should "construct an IIIFApi objec for an image+ROI given a baseUrl, imagePath, a maxWidth, and a maxHeight" in {
      val mw:Int = 600
      val mh:Int = 300
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val u:String = bis.serviceRequest(testImage2, maxWidth = Some(mw), maxHeight = Some(mh))
      assert(u.size > 0)
      assert(
        (u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/pct:10,20,30,40/!600,300/0/default.jpg") 
        ||
        (u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/pct:10.0,20.0,30.0,40.0/!600,300/0/default.jpg") 
      )
      println(u)
  }

  it should "construct an IIIFApi objec for an image+ROI at a high level of precision" in {
      val mw:Int = 600
      val mh:Int = 300
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val u:String = bis.serviceRequest(testImage3, maxWidth = Some(mw), maxHeight = Some(mh))
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/pct:12.345,22.345,32.345,42.345/!600,300/0/default.jpg"
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




    it should "deliver a working html <img> element" in {
      val mw:Int = 600
      val mh:Int = 300
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val imgEl:String = bis.htmlImage(testImage1, maxWidth = Some(mw), maxHeight = Some(mh))
    assert (
      imgEl == """<img class="citeImage" src="http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/!600,300/0/default.jpg" />""")
    }

    it should "deliver a working linked html <img> element, with default values" in {
      val mw:Int = 600
      val mh:Int = 300
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val imgEl:String = bis.linkedHtmlImage(testImage1, maxWidth = Some(mw), maxHeight = Some(mh))
    assert (imgEl == """<a href="http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013"><img class="citeImage" src="http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/!600,300/0/default.jpg" /></a>""")
    }

    it should "deliver a working linked html <img> element, with a specified viewer url " in {
      val mw:Int = 600
      val mh:Int = 300
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val imgEl:String = bis.linkedHtmlImage(testImage1, maxWidth = Some(mw), maxHeight = Some(mh), viewerUrl = "http://some.viewer/?urn=")
    assert (imgEl == """<a href="http://some.viewer/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013"><img class="citeImage" src="http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/!600,300/0/default.jpg" /></a>""")
    }

}
