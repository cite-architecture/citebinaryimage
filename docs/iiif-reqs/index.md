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
- htm to embed an image and link to a zoomable, citable version


Here is the requests for embedded images, followed by their output:



```scala
iiif.markdownImage(img)
// res1: String = "![image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/2000,/0/default.jpg)"
```
![image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/2000,/0/default.jpg)

```scala
iiif.linkedMarkdownImage(img)
// res3: String = "[![Linked to zoomble image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/2000,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013)"
```
[![Linked to zoomble image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/2000,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013)


```scala
iiif.htmlImage(img)
// res5: String = "<img class=\"citeImage\" src=\"http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/2000,/0/default.jpg\" />"
```
<img class="citeImage" src="http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/2000,/0/default.jpg" />


```scala
iiif.linkedHtmlImage(img)
// res7: String = "<a href=\"http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013\"><img class=\"citeImage\" src=\"http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/2000,/0/default.jpg\" /></a>"
```
<a href="http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA012RN_0013"><img class="citeImage" src="http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/2000,/0/default.jpg" /></a>



Each of these requests has the optional parameters to specify a width, or a maximum width or height.

```scala
iiif.serviceRequest(img, width = Some(200))
// res9: String = "http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif/full/200,/0/default.jpg"
```
The linked  image requests also have an optional `viewerUrl` parameter.
