package edu.holycross.shot.citebinaryimage

import scala.scalajs.js
import scala.scalajs.js.annotation._
import edu.holycross.shot.cite._

@JSExportAll object PathUtility {

  /** Derives a path based from an image URN for binary
  * image services organizing files following the convention of
  * grouping images by CITE2 namespace, collection, and collection version.
  * This can be appended to a baseUrl applying to a whole service to arrive
  * at a correctly map of image URN to image path on the server.
  *
  * @param img Image URN to calculate path for.
  */
  def expandedPath(img: Cite2Urn): String = {
    try {
      s"${img.namespace}/${img.collection}/${img.version}/"
    } catch {
      case t: Throwable => {
        val msg = s"Could not compute all URN components from ${img}.  "
        throw new Exception(msg + t.toString)
      }
    }
  }
/*
val imagePath = "/project/homer/pyramidal/deepzoom/ecod/codsang359imgs/v1/"
    val img = Cite2Urn("urn:cite2:ecod:codsang359imgs.v1:csg359_0_43_36_0@0.3888,0.2488,0.04748,0.03466")
  */
}
