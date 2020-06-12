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

Now you can form a request to retrieve a binary image like this:

```scala mdoc
val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013")

iiif.serviceRequest(img)
```


In addition, you can form requests formatted as:

- markdown to embed an image in a document
- markdown to embed an image and link to a zoomable, citable version
- html to embed an image in a document
- htm to embed an image and link to a zoomable, citable version


Here is the requests for embedded images, followed by their output:



```scala mdoc
iiif.markdownImage(img)
```
```scala mdoc:passthrough
println(iiif.markdownImage(img))
```

```scala mdoc
iiif.linkedMarkdownImage(img)
```
```scala mdoc:passthrough
println(iiif.linkedMarkdownImage(img))
```


```scala mdoc
iiif.htmlImage(img)
```
```scala mdoc:passthrough
println(iiif.htmlImage(img))
```


```scala mdoc
iiif.linkedHtmlImage(img)
```
```scala mdoc:passthrough
println(iiif.linkedHtmlImage(img))
```



Each of these requests has the optional parameters to specify a width, or a maximum width or height.

```scala mdoc
iiif.serviceRequest(img, width = Some(200))
```
```scala mdoc:passthrough
iiif.serviceRequest(img, width = Some(200))
```
The linked  image requests also have an optional `viewerUrl` parameter.
