package edu.holycross.shot
import scala.scalajs.js
import scala.scalajs.js.annotation._
import edu.holycross.shot.cite._

package citebinaryimage {

  /** A URN for a canonically citable text or passage of text.
  *
  * @constructor create a new [[IIPImage]] service
  * @param baseUrl String defining the base url of an IIPImage Server
  * @param imagePath String defining the absolute path on the server for pyramidal tiff images
  */
  @JSExportAll case class IIIFApi(baseUrl:String, imagePath:String, width:Option[Int] = None, maxWidth:Option[Int] = None, maxHeight:Option[Int] = None) extends BinaryImageService {

    val protocolString = "iiifApi"
    

    def serviceRequest(u:Cite2Urn):String = {
      try {

        if (u.objectComponentOption == None) {
          throw CiteException(s"URN must have an object component")
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
        case e:Exception => throw CiteException(s"CiteBinaryImageService Exception: ${e}")
      }
    }

  }

  // urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.09125620,0.11955275,0.70064910,0.06909404

// http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/VenA/VA012RN-0013.tif/full/2000,/0/default.jpg 

//http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/VenA/VA012RN-0013.tif&RGN=0.09125620,0.11955275,0.70064910,0.06909404&WID=8000&CVT=JPEG

//  http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/VenA/VA012RN-0013.tif&RGN=0.164,0.0541,0.49,0.1366&WID=9000&CVT=JPEG

}
