package edu.holycross.shot.citebinaryimage

import edu.holycross.shot.cite._
import org.scalatest.FlatSpec

class ImageROISpec extends FlatSpec {


	"The ImageROI class" should "initialize" in {
		val rs:String = "0.1,0.2,0.3,0.4"
		val ir:ImageROI = ImageROI(rs)
		assert (ir.exists)
	}

	it should "return an ROI as a string" in {
		val rs:String = "0.1,0.2,0.3,0.4"
		val ir:ImageROI = ImageROI(rs)
		assert ( ir.toString == rs )
	}

	it should "return an IIIFRegionString" in {
		val rs:String = "0.1,0.2,0.3,0.4"
		val iiifRoi:String = "pct:10,20,30,40"
		val ir:ImageROI = ImageROI(rs)
		assert(ir.iiifRegionString == iiifRoi)
	}

  it should "refuse to construct an IIIFApi object from a string with too few components" in {
  		val rs:String = "0.1,0.2,0.3"
      try {
         val ir:ImageROI = ImageROI(rs)
         fail("Should have thrown exception.")
      } catch {
        case e:Throwable => 
      }

    }

  it should "refuse to construct an IIIFApi object from a string with out-of-bounds components" in {
  		val rs:String = "2,0.2,0.3,0.4"
      try {
         val ir:ImageROI = ImageROI(rs)
         fail("Should have thrown exception.")
      } catch {
        case e:Throwable => 
      }

    }
  it should "refuse to construct an IIIFApi object that is too big" in {
  		val rs:String = "0.6,0.2,0.6,0.4"
      try {
         val ir:ImageROI = ImageROI(rs)
         fail("Should have thrown exception.")
      } catch {
        case e:Throwable => 
      }

    }
}
