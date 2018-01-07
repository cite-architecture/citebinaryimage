package edu.holycross.shot
import scala.scalajs.js
import scala.scalajs.js.annotation._
import edu.holycross.shot.cite._

package citebinaryimage {


  /** A ROI subreference for CITE Image URNs
  *
  * @constructor create a new [[ImageROI]] object
  * @param roiString String with four, comma-delimited numbers between 0 and 1
  */
  @JSExportAll case class ImageROI(roiString:String){
         val splitRoi:Array[String] = roiString.split(",") 
         val leftString:String = splitRoi(0)
         val topString:String = splitRoi(1)
         val widthString:String = splitRoi(2)
         val heightString:String = splitRoi(3)

         val left:Float = splitRoi(0).toFloat
         val top:Float = splitRoi(1).toFloat
         val width:Float = splitRoi(2).toFloat
         val height:Float = splitRoi(3).toFloat

         if ( (left < 0) || ( left > 1)){
         	throw new Exception(s"""left (${left}) must be between 0 and 1""")
         }
         if ( (top < 0) || ( top > 1)){
         	throw new Exception(s"""top (${top}) must be between 0 and 1""")
         }
         if ( (width < 0) || ( width > 1)){
         	throw new Exception(s"""width (${width}) must be between 0 and 1""")
         }
         if ( (height < 0) || ( height > 1)){
         	throw new Exception(s"""height (${height}) must be between 0 and 1""")
         }
         if ( (top + height) > 1){
         	throw new Exception(s"""top + height (${top} + ${height} = ${top+height}) must be <= 1""")
         }
         if ( (left + width) > 1){
         	throw new Exception(s"""left + height (${left} + ${width} = ${left+width}) must be <= 1""")
         }

      def iiifRegionString:String = {
      	val l:Int = (left * 100).toInt
      	val t:Int = (top * 100).toInt
      	val w:Int = (width * 100).toInt
      	val h:Int = (height * 100).toInt
      	val ifrs:String = s"pct:${l},${t},${w},${h}"
      	ifrs
      }

  		def exists:Boolean = { true }
  		override def toString:String = {
  			val rs:String = s"${this.leftString},${this.topString},${this.widthString},${this.heightString}"
  			rs
  		}

  }

}
