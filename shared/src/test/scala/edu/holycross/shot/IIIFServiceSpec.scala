package edu.holycross.shot.citebinaryimage

import edu.holycross.shot.cite._
import org.scalatest.FlatSpec

class IIIFServiceSpec extends FlatSpec {

  val baseUrl  = "http://www.homermultitext.org/iipsrv?"
  val imagePath = "/project/homer/pyramidal/VenA/"
  val testImage1  = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013")
  val testImage2  = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.1,0.2,0.3,0.4")
  val testImage3  = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.12345,0.22345,0.32345,0.42345")

  "An IIIF Service" should "construct an IIIFApi object given a baseUrl and Path" in {
      val bis:IIIFApi = IIIFApi(baseUrl, imagePath)
      val u:String = bis.serviceRequest(testImage1)
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/2000,/0/default.jpg"
      )
      //println(u)
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
      //println(u)
    }

  it should "construct an IIIFApi object given a baseUrl and Path and a width" in {
      val w:Int = 200
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val u:String = bis.serviceRequest(testImage1, width = Some(w))
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/200,/0/default.jpg"
      )
      //println(u)
  }

  it should "construct an IIIFApi object given a baseUrl and Path and a maxWidth" in {
      val mw:Int = 300
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val u:String = bis.serviceRequest(testImage1, maxWidth = Some(mw))
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/!300,/0/default.jpg"
      )
      //println(u)
  }

  it should "construct an IIIFApi object given a baseUrl and Path and a maxHeight" in {
      val mh:Int = 300
      val bis:IIIFApi = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val u:String = bis.serviceRequest(testImage1, maxHeight = Some(mh))
      assert(u.size > 0)
      assert(
        u == "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/!,300/0/default.jpg"
      )
      //println(u)
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
      //println(u)
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
      //println(u)
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
      //println(u)
  }

  it should "refuse to construct an IIIFApi object from a URN with an invalid ROI" in {
      val bis  = IIIFApi(baseUrl, imagePath)
      val badImage  = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.2305,0.5115,0.494")

      try {
         val u1 = bis.serviceRequest(badImage)
         fail("Should have thrown exception.")

      } catch {
        case jvm: CiteBinaryImageException => {
          // correct exception under JVM
          assert(jvm.toString.contains("java.lang.ArrayIndexOutOfBoundsException"))
        }
        case js : Throwable => {
          // Runtime error in JS, catch as other Throwable.
          // This is appropriately the correct exception message under ScalaJS,
          // since in javascript all behavior is undefined, as far as I can tell.
          assert(js.toString.contains("UndefinedBehaviorError"))
        }
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
      val mw = 600
      val mh = 300
      val bis = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val imgEl = bis.linkedHtmlImage(testImage1, maxWidth = Some(mw), maxHeight = Some(mh), viewerUrl = "http://some.viewer/?urn=")
    assert (imgEl == """<a href="http://some.viewer/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013"><img class="citeImage" src="http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/full/!600,300/0/default.jpg" /></a>""")
    }

    it should "compose markdown for an embedded image" in {
      val bis = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val mdImg = bis.markdownImage(testImage3)

      val expected = "![image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/pct:12.345,22.345,32.345,42.345/2000,/0/default.jpg)"
      assert(mdImg == expected)
    }

    it should "permit optional caption in embedded markdown images" in {
      val bis = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val maxH = Some(200)
      val caption = "Detail of 12 recto"
      val mdImg = bis.markdownImage(testImage3, caption, maxHeight = maxH)
      val expected = "![Detail of 12 recto](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/pct:12.345,22.345,32.345,42.345/!,200/0/default.jpg)"
      assert(mdImg == expected)
    }

    it should "compose markdown for an embedded image linked to a zoomable view" in {
      val bis = IIIFApi(baseUrl = baseUrl, imagePath = imagePath)
      val maxH = Some(200)
      val caption = "Link to zoomable view of 12 recto"
      val linkedImage = bis.linkedMarkdownImage(testImage3, caption, maxHeight = maxH)


      val expected = "[![Link to zoomable view of 12 recto](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN_0013.tif/pct:12.345,22.345,32.345,42.345/!,200/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.12345,0.22345,0.32345,0.42345)"
      assert(linkedImage == expected)
    }


}
