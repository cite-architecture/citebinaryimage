---
layout: page
title: Forming IIIF requests
---


**Library version 3.2.0**




When you've constructed an `IIIFApi`, you can send it requests.

```scala
val iif = IIIFApi(baseUrl,fullPath)
// iif: IIIFApi = IIIFApi(
//   "http://www.homermultitext.org/iipsrv?",
//   "/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/"
// )
val imgUrn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013")
// imgUrn: Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013")
```
