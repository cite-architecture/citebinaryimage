# `citebinaryimage`: release notes

**3.1.0**:  Add `PathUtility` object for easy work with services following convention of laying out file system by URN components.

**3.0.0**:  API-breaking change of `CiteException` to `CiteBianryImageException` (issue #4).  Also add functions to `IIIFApi` for formatting requests in markdown.


**2.0.1**: Allow a little flexibility in checking values for ImageROIs, to account for human frailty, and Javascript.

**2.0.0**: Breaking change to API. Added htmlImage and linkedHtmlImage methods to IIIF class.

**1.1.3**: Updated library dependencies.

**1.1.2**:  Improve (increase) precision of ROIs. Handle how JVM and JS serialize numbers into strings differently.

**1.1.1**:  Bug fix in update to `cex` parser.

**1.1.0**: Update depenendcies and implement skelly-compliant cross building.

**1.0.1**:  Switch bintray publication to Scala 2.12.

**1.0.0**:  initial release.
