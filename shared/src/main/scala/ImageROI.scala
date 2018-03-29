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

         val left:Double = splitRoi(0).toDouble
         val top:Double = splitRoi(1).toDouble
         val width:Double = splitRoi(2).toDouble
         val height:Double = splitRoi(3).toDouble

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

      /** 
      Returns a string expressing the ROI as the IIIFApi expects
      */
      def iiifRegionString:String = {
        val precision:Int = 5
      	val l:Double = (truncate(left*100,precision))
      	val t:Double = (truncate(top*100,precision))
      	val w:Double = (truncate(width*100,precision))
      	val h:Double = (truncate(height*100,precision))
      	val ifrs:String = s"pct:${l},${t},${w},${h}"
      	ifrs
      }

      def truncate(d:Double, precision:Int):Double = {
         val multiplier:Int = scala.math.pow(10, precision).toInt
         val trunced:Double =  (math floor d * multiplier) / multiplier
         trunced
      }

      /** Returns true
      */
   		def exists:Boolean = { true }
  		override def toString:String = {
  			val rs:String = s"${this.leftString},${this.topString},${this.widthString},${this.heightString}"
  			rs
  		}

  }

}
