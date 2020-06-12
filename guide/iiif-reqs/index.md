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

val imgPath = PathUtility.expandedPath(Cite2Urn("urn:cite2:hmt:vaimg.2017a:"))
val fullPath = pathBase + imgPath
```


Construct an `IIIFApi` for your image collection.

```scala mdoc
val iiif = IIIFApi(baseUrl,fullPath)
```

Now you can form requests to:

- retrieve a binary image
- markdown to embed an image in a document
- markdown to embed an image and link to a zoomable, citable version
- html to embed an image in a document
- htm to embed an image and link to a zoomable, citable version

```scala mdoc
val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013")

iiif.serviceRequest(img)
iiif.markdownImage(img)
iiif.linkedMarkdownImage(img)
iiif.htmlImage(img)
iiif.linkedHtmlImage(img)
```

Each of these requests has the optional parameters to specify a width, or a maximum width or height.

```scala mdoc
iiif.serviceRequest(img, width = Some(200))
```

The linked  image requests also have an optional `viewerUrl` parameter.
