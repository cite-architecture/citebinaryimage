package edu.holycross.shot
import edu.holycross.shot.cite._


package citebinaryimage {

  trait BinaryImageService {
    val protocolString: String
    def serviceRequest(urn:Cite2Urn): String
  }

}
