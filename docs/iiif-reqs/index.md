---
layout: page
title: Forming IIIF requests
---


**Library version 3.2.0**




Construct an `IIIFApi` for your image collection.

```scala
val iiif = IIIFApi(baseUrl,fullPath)
// iiif: IIIFApi = IIIFApi(
//   "http://www.homermultitext.org/iipsrv?",
//   "/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/"
// )
```

Now you can form requests to:

- retrieve a binary image
- markdown to embed an image in a document
- markdown to embed an image and link to a zoomable, citable version
- html to embed an image in a document
- htm to embed an image and link to a zoomable, citable version

```scala
val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013")
// img: Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013")

iiif.serviceRequest(img)
// res0: String = "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/2000,/0/default.jpg"
iiif.markdownImage(img)
// res1: String = "![image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/2000,/0/default.jpg)"
iiif.linkedMarkdownImage(img)
// res2: String = "[![Linked to zoomble image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/2000,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013)"
iiif.htmlImage(img)
// res3: String = "<img class=\"citeImage\" src=\"http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/2000,/0/default.jpg\" />"
iiif.linkedHtmlImage(img)
// res4: String = "<a href=\"http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013\"><img class=\"citeImage\" src=\"http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/2000,/0/default.jpg\" /></a>"
```

Each of these requests has the optional parameters to specify a width, or a maximum width or height.

```scala
iiif.serviceRequest(img, width = Some(200))
// res5: String = "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/200,/0/default.jpg"
```

The linked  image requests also have an optional `viewerUrl` parameter.
