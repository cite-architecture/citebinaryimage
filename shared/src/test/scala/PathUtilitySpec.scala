package edu.holycross.shot.citebinaryimage

import edu.holycross.shot.cite._
import org.scalatest.FlatSpec

class PathUtilitySpec extends FlatSpec {

  val baseUrl  = "http://www.homermultitext.org/iipsrv?"
  val imagePathBase = "/project/homer/pyramidal/deepzoom/"

  val vaImage  = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.12345,0.22345,0.32345,0.42345")

  "The PathUtils object" should "compose an expanded path for a well formed image URN" in {
    val subPath = PathUtility.expandedPath(vaImage)
    val expectedSubPath = "hmt/vaimg/2017a/"
    assert(subPath == expectedSubPath)
  }

  it should "object if URN does not incude a version" in {
    val noVersion  = Cite2Urn("urn:cite2:hmt:vaimg:VA012RN_0013")
    try {
      val subPath = PathUtility.expandedPath(noVersion)
    } catch {
      case t: Throwable => {
        val expectedMessage = "Could not compute all URN components from urn:cite2:hmt:vaimg:VA012RN_0013"
        assert(t.toString.contains(expectedMessage))
      }
    }
  }

  it should "be dead easy to use with a CiteBinaryImageService" in {
    val bis  = IIIFApi(baseUrl, imagePathBase + PathUtility.expandedPath(vaImage))
    val requestString  = bis.serviceRequest(vaImage)
    val expectedRequestString = "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/pct:12.345,22.345,32.345,42.345/2000,/0/default.jpg"
    assert(requestString == expectedRequestString)
  }






}
