package edu.holycross.shot

package citebinaryimage {

  /** Class for Exceptions in citebinaryimage package.
  *
  * @param message Message specific to this library.
  * @param cause Optional upstream Throwable.
  */
  case class CiteBinaryImageException(message: String = "", cause: Option[Throwable] = None) extends Exception(message) {
    cause.foreach(initCause)
  }

}
