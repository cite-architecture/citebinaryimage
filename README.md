# `citebinaryimage`

## What it is

`citebinaryimage` is a cross-platform library for retrieving binary image date identified by Cite2 URNs.

## Current version: 1.1.2

Status:  **active development**. [Release notes](releases.md)


## License

[GPL 3.0](http://www.opensource.org/licenses/gpl-3.0.html)

## Documentation

In addition to the API documentation, see the [project wiki](https://github.com/cite-architecture/citebinaryimage/wiki).

## Using, building, testing

`citebinaryimage` is compiled for both the JVM and ScalaJS using scala versions 2.11 and 2.12.  Binaries for all platforms are available from jcenter.

If you are using sbt, include `Resolver.jcenterRepo` in your list of resolvers

    resolvers += Resolver.jcenterRepo

and add this to your library dependencies:

    "edu.holycross.shot.cite" %%% "citebinaryimage" % VERSION


For maven, ivy or gradle equivalents, refer to <https://bintray.com/neelsmith/maven/citebinaryimage>.

To build from source and test, use normal sbt commands (`compile`, `test` ...).
