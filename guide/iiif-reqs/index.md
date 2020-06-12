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
- html to embed an image and link to a zoomable, citable version


Each of these requests has optional parameters to specify a width, or a maximum width or height.
Here are examples of each request for embedded images, followed by their output:



```scala mdoc
iiif.markdownImage(img, width=Some(200))
```
```scala mdoc:passthrough
println(iiif.markdownImage(img, width=Some(200)))
```

```scala mdoc
iiif.htmlImage(img, maxWidth= Some(75))
```
```scala mdoc:passthrough
println(iiif.htmlImage(img, maxWidth =Some(75)))
```

The linked  image requests also have an optional `viewerUrl` parameter.
```scala mdoc
iiif.linkedHtmlImage(img, maxHeight=Some(150))
```
```scala mdoc:passthrough
println(iiif.linkedHtmlImage(img, maxHeight=Some(150)))
```

The linked markdown image can also include a caption parameter.
```scala mdoc
iiif.linkedMarkdownImage(img, width=Some(150), caption="Folio 12 recto of the Venetus A manuscript of the Iliad")
```
```scala mdoc:passthrough
println(iiif.linkedMarkdownImage(img, width=Some(150), caption="Folio 12 recto"))
```


The requests work with URNs citation regions of an image.


```scala mdoc
val citedRegion = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.1575,0.09332,0.3879,0.03225")
```

```scala mdoc
iiif.linkedMarkdownImage(citedRegion, width=Some(150), caption="Metrical summary of book 1")
```

```scala mdoc:passthrough
println(iiif.linkedMarkdownImage(citedRegion, maxHeight=Some(150), caption="Metrical summary of book 1"))
```
