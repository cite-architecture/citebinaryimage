package edu.holycross.shot
import edu.holycross.shot.cite._
import scala.scalajs.js
import scala.scalajs.js.annotation._


package citebinaryimage {

   @JSExportAll trait BinaryImageService {
    val protocolString: String
    def serviceRequest(urn:Cite2Urn, width:Option[Int] = None, maxWidth:Option[Int] = None, maxHeight:Option[Int] = None): String
  }

}
