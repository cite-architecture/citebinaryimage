---
layout: page
title: Constructing an IIIF service
---


**Library version 3.2.0**

You'll need these two libraries:

```scala
import edu.holycross.shot.citebinaryimage._
import edu.holycross.shot.cite._
```

To construct an `IIIFApi` for a specific image, you need to know:

- a base url for the IIIF service, and
- a path on the server to the image.

```scala
val baseUrl  = "http://www.homermultitext.org/iipsrv?"
// baseUrl: String = "http://www.homermultitext.org/iipsrv?"
val pathBase = "/project/homer/pyramidal/deepzoom/"
// pathBase: String = "/project/homer/pyramidal/deepzoom/"
```

In this example, the image service's directory layout reflects the URN of the image.  In cases like this, you can use the `PathUtility` object to expand a Cite2Urn into a directory path.  We can contenate that with the `pathBase` to get a full path to the image.


```scala
// The image citation:
val imgCollection = Cite2Urn("urn:cite2:hmt:vaimg.2017a:")
// imgCollection: Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:")
val imgPath = PathUtility.expandedPath(imgCollection)
// imgPath: String = "hmt/vaimg/2017a/"
val fullPath = pathBase + imgPath
// fullPath: String = "/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/"
```
And at this point we can construct the `IIIFApi` to form requests for any image in that CITE Collection.

```scala
val iif = IIIFApi(baseUrl,fullPath)
// iif: IIIFApi = IIIFApi(
//   "http://www.homermultitext.org/iipsrv?",
//   "/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/"
// )
```
