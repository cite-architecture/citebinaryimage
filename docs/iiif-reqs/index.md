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

Now you can form a request to retrieve a binary image like this:

```scala
val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013")
// img: Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013")

iiif.serviceRequest(img)
// res0: String = "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/2000,/0/default.jpg"
```


In addition, you can form requests formatted as:

- markdown to embed an image in a document
- markdown to embed an image and link to a zoomable, citable version
- html to embed an image in a document
- html to embed an image and link to a zoomable, citable version


Each of these requests has optional parameters to specify a width, or a maximum width or height.
Here are examples of each request for embedded images, followed by their output:



```scala
iiif.markdownImage(img, width=Some(200))
// res1: String = "![image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/200,/0/default.jpg)"
```
![image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/200,/0/default.jpg)

```scala
iiif.htmlImage(img, maxWidth= Some(75))
// res3: String = "<img class=\"citeImage\" src=\"http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/!75,/0/default.jpg\" />"
```
<img class="citeImage" src="http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/!75,/0/default.jpg" />

The linked  image requests also have an optional `viewerUrl` parameter.
```scala
iiif.linkedHtmlImage(img, maxHeight=Some(150))
// res5: String = "<a href=\"http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013\"><img class=\"citeImage\" src=\"http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/!,150/0/default.jpg\" /></a>"
```
<a href="http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013"><img class="citeImage" src="http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/!,150/0/default.jpg" /></a>

The linked markdown image can also include a caption parameter.
```scala
iiif.linkedMarkdownImage(img, width=Some(150), caption="Folio 12 recto of the Venetus A manuscript of the Iliad")
// res7: String = "[![Folio 12 recto of the Venetus A manuscript of the Iliad](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/150,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013)"
```
[![Folio 12 recto](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/150,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013)


The requests work with URNs citing regions of an image.


```scala
val citedRegion = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.1575,0.09332,0.3879,0.03225")
// citedRegion: Cite2Urn = Cite2Urn(
//   "urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.1575,0.09332,0.3879,0.03225"
// )
```

```scala
iiif.linkedMarkdownImage(citedRegion, width=Some(150), caption="Metrical summary of book 1")
// res9: String = "[![Metrical summary of book 1](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/pct:15.75,9.332,38.79,3.225/150,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.1575,0.09332,0.3879,0.03225)"
```

[![Metrical summary of book 1](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/pct:15.75,9.332,38.79,3.225/!,150/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.1575,0.09332,0.3879,0.03225)
