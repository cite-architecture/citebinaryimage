package edu.holycross.shot
import scala.scalajs.js
import scala.scalajs.js.annotation._
import edu.holycross.shot.cite._

package citebinaryimage {

  /** A Class for creating service requests resolving to binary image data
  * and following the IIIF-API.
  *
  * @constructor create a new [[IIIFApi]] service
  * @param baseUrl String defining the base url of an IIPImage Server
  * @param imagePath String defining the absolute path on the server for pyramidal tiff images
  */
  @JSExportAll case class IIIFApi(baseUrl:String, imagePath:String) extends BinaryImageService {

    val protocolString = "iiifApi"

    /**
    * Returns a String, a URL that resolves to a binary image
    * @param u Cite2Urn, the URN of an image
    */
     def serviceRequest(u:Cite2Urn, width:Option[Int] = None, maxWidth:Option[Int] = None, maxHeight:Option[Int] = None):String = {
      try {

        if (u.objectComponentOption == None) {
          throw CiteBinaryImageException(s"URN must have an object component")
        }
        val imageID:String = u.dropExtensions.objectComponentOption.get

        val roiComponent:String = u.objectExtensionOption match {
          case Some(oe) => {
            val ir:ImageROI = ImageROI(oe)
            ir.iiifRegionString
          }
          case _ => "full"
        }

        // Limit to 2000 px width because IIPServ craps out otherwise
        var sizing:String = ""
        width match {
          case Some(w) => {
            if (w > 2000) { sizing = "2000,"} else { sizing = s"${w},"}
          }
          case None => sizing = "2000," // default max
        }
        maxWidth match {
          case Some(mw) => {
            maxHeight match {
              case Some(mh) => {
                sizing = s"!${mw},${mh}"
              }
              case None => {
                sizing = s"!${mw},"
              }
            }
          }
          case None => {
            maxHeight match {
              case Some(mh) => {
                sizing = s"!,${mh}"
              }
              case None => { } // already set for width or default max
            }
          }
        }

        val urlString = s"${baseUrl}IIIF=${imagePath}${imageID}.tif/${roiComponent}/${sizing}/0/default.jpg"
        urlString
      } catch {
        case e:Exception => throw CiteBinaryImageException(s"CiteBinaryImageService Exception: ${e}")
      }
    }


    /**
    * Returns a markdown String for embedding an image.
    *
    * @param u Cite2Urn, the URN of an image.
    */
    def markdownImage(u: Cite2Urn,
      caption: String = "image",
      width: Option[Int] = None,
      maxWidth: Option[Int] = None,
      maxHeight: Option[Int] = None): String = {
        try {
          val url = serviceRequest(u, width, maxWidth, maxHeight)
          s"![${caption}](${url})"

        } catch {
          case e:Exception => throw CiteBinaryImageException(s"CiteBinaryImageService Exception: ${e}")
        }
    }



    /**
    * Returns a String, an HTML <a> element containing an <img> element
    * for embedding an image.
    * @param u Cite2Urn, the URN of an image
    * @param viewerUrl, a url to an external viewing application, assumed to take the URN value as the last request-parameter
    */
    def linkedMarkdownImage(u: Cite2Urn,

      caption: String = "Linked to zoomble image",
      width: Option[Int] = None,
      maxWidth: Option[Int] = None,
      maxHeight: Option[Int] = None,
      viewerUrl: String = "http://www.homermultitext.org/ict2/?urn="): String = {
      try {
        val embedded = markdownImage(u, caption, width, maxWidth, maxHeight)
        val viewerRequest  = s"${viewerUrl}${u}"
        s"[${embedded}](${viewerRequest})"

      } catch {
        case e:Exception => throw CiteBinaryImageException(s"CiteBinaryImageService Exception: ${e}")
      }
    }

    /**
    * Returns a String, an HTML <img> element for embedding an image.
    *
    * @param u Cite2Urn, the URN of an image
    */
    def htmlImage(u:Cite2Urn, width:Option[Int] = None, maxWidth:Option[Int] = None, maxHeight:Option[Int] = None): String = {
      try {
        val url:String = serviceRequest(u,width,maxWidth,maxHeight)
        s"""<img class="citeImage" src="${url}" />"""
      } catch {
        case e:Exception => throw CiteBinaryImageException(s"CiteBinaryImageService Exception: ${e}")
      }
    }

    /**
    * Returns a String, an HTML <a> element containing an <img> element
    * for embedding an image
    * @param u Cite2Urn, the URN of an image
    * @param viewerUrl, a url to an external viewing application, assumed to take the URN value as the last request-parameter
    */
    def linkedHtmlImage(u: Cite2Urn,
      width: Option[Int] = None,
      maxWidth: Option[Int] = None,
      maxHeight: Option[Int] = None,
      viewerUrl: String = "http://www.homermultitext.org/ict2/?urn="): String = {
      try {
        val imgCode = htmlImage(u, width, maxWidth, maxHeight)
        val viewerRequest  = s"${viewerUrl}${u}"
        s"""<a href="${viewerRequest}">${imgCode}</a>"""

      } catch {
        case e:Exception => throw CiteBinaryImageException(s"CiteBinaryImageService Exception: ${e}")
      }
    }

  }
}
