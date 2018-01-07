package edu.holycross.shot

package citebinaryimage {

  case class CiteException(message: String = "", cause: Option[Throwable] = None) extends Exception(message) {
    cause.foreach(initCause)
  }

}
