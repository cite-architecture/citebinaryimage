---
layout: page
title: Forming IIIF requests
---


**Library version @VERSION@**


```scala mdoc:invisible
import edu.holycross.shot.citebinaryimage._
import edu.holycross.shot.cite._


val baseUrl  = "http://www.homermultitext.org/iipsrv?"
val pathBase = "/project/homer/pyramidal/deepzoom/"

val imgPath = PathUtility.expandedPath(Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013"))
val fullPath = pathBase + imgPath
```


When you've constructed an `IIIFApi`, you can send it requests.

```scala mdoc
val iif = IIIFApi(baseUrl,fullPath)
val imgUrn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013")
```
