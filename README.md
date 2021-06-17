# `citebinaryimage`

## What it is

`citebinaryimage` is a cross-platform library for retrieving binary image date identified by Cite2 URNs.

## Current version: 3.2.1

Status:  **active development**. [Release notes](releases.md)


## License

[GPL 3.0](http://www.opensource.org/licenses/gpl-3.0.html)

## Documentation

- [user guide](https://cite-architecture.github.io/citebinaryimage/)
- [API docs](https://cite-architecture.github.io/cite-api-docs/citebinaryimage/api/edu/holycross/shot/citebinaryimage/index.html)

## Using, building, testing

`citebinaryimage` is compiled for both the JVM and ScalaJS using scala versions 2.11 and 2.12.  Binaries for all three versions are available from the Nexus repository on <terracotta.hpcc.uh.edu/nexus>.

If you are using sbt, include `Resolver.jcenterRepo` in your list of resolvers

```scala
	resolvers += "Nexus" at "https://terracotta.hpcc.uh.edu/nexus/repository/maven-releases/",
```

and add this to your library dependencies:

    "edu.holycross.shot.cite" %%% "citebinaryimage" % VERSION


For maven, ivy or gradle equivalents, refer to <https://bintray.com/neelsmith/maven/citebinaryimage>.

To build from source and test, use normal sbt commands (`compile`, `test` ...).
