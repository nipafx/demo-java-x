# Java X Feature Demo

Demonstrates most features that were introduced by Java 9 and later versions.
The links below take you to the demos in this project, the JEPs responsible for introducing the feature, and to other sources if available.

For a more practical approach to many of these features, including some quick code metrics, check my [_Java After Eight_ repo](https://github.com/CodeFX-org/java-after-eight). 

These articles discuss the recent Java versions and list a lot of the new features:

**Java 13:**

* [Definitive Guide To Java 13](http://blog.codefx.org/java/java-13-guide/)
* [81 New Features and APIs in JDK 13](https://www.azul.com/jdk-13-81-new-features-and-apis/)

**Java 12:**

* [Definitive Guide To Java 12](https://blog.codefx.org/java/java-12-guide/)
* [39 New Features (and APIs) in JDK 12](https://www.azul.com/39-new-features-and-apis-in-jdk-12/)

**Java 11:**

* [All You Need To Know For Migrating To Java 11](https://blog.codefx.org/java/java-11-migration-guide/)
* [Java 11 Tutorial](https://winterbe.com/posts/2018/09/24/java-11-tutorial/)
* [90 New Features (and APIs) in JDK 11](https://www.azul.com/90-new-features-and-apis-in-jdk-11/)

**Java 9:**

* [Code-First Java 9 Tutorial](https://blog.codefx.org/java/java-9-tutorial/)
* [The Ultimate Guide to Java 9](https://www.sitepoint.com/ultimate-guide-to-java-9/)
* [Inside Java 9 – Version Schema, Multi-Release JARs, and More](https://www.sitepoint.com/inside-java-9-part-i/)
* [Inside Java 9 – Performance, Compiler, and More](https://www.sitepoint.com/inside-java-9-part-ii/)

You can read more from me on [codefx.org](http://codefx.org), watch me blab [on my YouTube channel](https://youtube.com/c/codefx), or follow me [on Twitter](https://twitter.com/nipafx).

## Setup

This project requires at least the most recent Java release, at times even early-access builds of upcoming versions.
You can get OpenJDK builds for both from [jdk.java.net](http://jdk.java.net), although I prefer using [SDKMAN](https://sdkman.io/).

Most of the project can be built with Maven.
It uses [toolchains](https://maven.apache.org/guides/mini/guide-using-toolchains.html) to configure which Java version Maven should use for compilation and packaging.
Create/modify `toolchains.xml` in Maven's user folder (`~/.m2/` on Linux) to contain a block like the following:

```xml
<!-- if the file already contains a <toolchains> tag,
     you only need the inner <toolchain> block -->
<toolchains>
	<toolchain>
		<type>jdk</type>
		<provides>
			<version>13</version>
			<vendor>OpenJDK</vendor>
		</provides>
		<configuration>
			<jdkHome>/opt/jdk/13</jdkHome>
		</configuration>
	</toolchain>
</toolchains>
```

If your IDE doesn't like new syntax or APIs, you can always compile and run by hand - see `compile.sh`, `run.sh` (which expects the fully qualified name of the main class as argument) and `compile-run.sh` (conveniently combines the two - also needs the main class).
The scripts are written for Linux but should look similar on other operating systems.
If the appropriate Java version is not on your path, configure it in `executables.sh`.

For some features, you _have to_ run the `.sh` scripts in the root directory.
If that's necessary, the feature list below mentions it.

## Java Platform Module System

The module system is too big to demo here.
Check out the [jpms](http://blog.codefx.org/tag/jpms/) tag on my blog, [this demo project](https://github.com/CodeFX-org/demo-jpms-monitor), or [my book on the module system](https://www.manning.com/books/the-java-module-system?a_aid=nipa&a_bid=869915cb).

## Language Changes

* ⑬ [text blocks](src/main/java/org/codefx/demo/java13/lang/text_blocks/TextBlocks.java) ([article](https://blog.codefx.org/java/text-blocks))
* ⑬⑫ [switch expressions](src/main/java/org/codefx/demo/java12/lang/switch_/Switch.java) ([article](https://blog.codefx.org/java/switch-expressions/), [video](https://www.youtube.com/watch?v=1znHEf3oSNI), [JEP 325](https://openjdk.java.net/jeps/325), [JEP 354](https://openjdk.java.net/jeps/354))
* ⑩ [local-variable type inference with `var`](src/main/java/org/codefx/demo/java10/lang/var/VariableTypeInference.java) ([article](http://blog.codefx.org/java/java-10-var-type-inference/), [video](https://www.youtube.com/watch?v=Le1DbpRZdRQ), [JEP 286](http://openjdk.java.net/jeps/286))
	* experiments with [intersection types](src/main/java/org/codefx/demo/java10/lang/var/IntersectionTypes.java) ([article](http://blog.codefx.org/java/intersection-types-var))
	* experiments with ad-hoc [fields](src/main/java/org/codefx/demo/java10/lang/var/AdHocFields.java) and [methods](src/main/java/org/codefx/demo/java10/lang/var/AdHocMethods.java) ([article](http://blog.codefx.org/java/tricks-var-anonymous-classes/))
	* experiments with [traits](src/main/java/org/codefx/demo/java10/lang/var/Traits.java) ([article](http://blog.codefx.org/java/traits-var))
* ⑨ [private interface methods](src/main/java/org/codefx/demo/java9/lang/private_interface_methods/PrivateInterfaceMethods.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* ⑨ [try-with-resources on effectively final variables](src/main/java/org/codefx/demo/java9/lang/try_with_resources/TryWithResources.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* ⑨ [diamond operator for anonymous classes](src/main/java/org/codefx/demo/java9/lang/diamond_operator/DiamondOperator.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* ⑨ [`@SaveVarargs` on private non-final methods](src/main/java/org/codefx/demo/java9/lang/safe_varargs/SafeVarargs.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* ⑨ [no warnings for deprecated imports](src/main/java/org/codefx/demo/java9/lang/deprecated_imports/DeprecatedImports.java) ([JEP 211](http://openjdk.java.net/jeps/211))

## New APIs

* ⑪ HTTP/2 client: [simple](src/main/java/org/codefx/demo/java11/api/http2/Http2Api.java), [more formal](src/main/java/org/codefx/demo/java11/api/http2/formalized) ([tutorial](https://blog.codefx.org/java/http-2-api-tutorial/), [reactive request/response bodies](https://blog.codefx.org/java/reactive-http-2-requests-responses/))
* ⑩⑨ version API, [introduced in Java 9](src/main/java/org/codefx/demo/java9/api/version/VersionApi.java),
  [updated in Java 10](src/main/java/org/codefx/demo/java10/api/version/VersionApi.java)
* ⑨ [collection factory methods](src/main/java/org/codefx/demo/java9/api/collection_factory_methods) (instead of collection literals; [JEP 269](http://openjdk.java.net/jeps/269))
* ⑨ [reactive streams](src/main/java/org/codefx/demo/java9/api/reactive_streams) ([JEP 266](http://openjdk.java.net/jeps/266))
* ⑨ [stack walking](src/main/java/org/codefx/demo/java9/api/stack_walking/StackWalking.java) ([JEP 259](http://openjdk.java.net/jeps/259), [post on SitePoint](https://www.sitepoint.com/deep-dive-into-java-9s-stack-walking-api/) including benchmarks)
* ⑨ [multi-resolution images](src/main/java/org/codefx/demo/java9/api/multi_resolution_images/Images.java) ([JEP 251](http://openjdk.java.net/jeps/251))
* ⑨ platform-specific desktop features (not supported by my OS so [my sample](src/main/java/org/codefx/demo/java9/api/desktop/DesktopFeatures.java) sucks; PRs welcome! [JEP 272](http://openjdk.java.net/jeps/272))
* ⑨ deserialization filter (targeted for JDK 9 but wasn't implemented when I created this; [JEP 290](http://openjdk.java.net/jeps/290))

## Updated APIs

* ⑫⑪ `String` [in Java 12](src/main/java/org/codefx/demo/java12/api/string)
  and [in Java 11](src/main/java/org/codefx/demo/java11/api/string)
* ⑫⑨ `Stream` [in Java 12](src/main/java/org/codefx/demo/java12/api/stream/TeeingCollector.java) ([article](https://blog.codefx.org/java/teeing-collector/))
  and [in Java 9](src/main/java/org/codefx/demo/java9/api/stream) ([article](http://blog.codefx.org/java/dev/java-9-stream/))
* ⑫ [formating numbers](src/main/java/org/codefx/demo/java12/api/format/CompactFormat.java)
* ⑫ [`Files::mismatch`](src/main/java/org/codefx/demo/java12/api/files/FilesMismatch.java)
* ⑫ [error recovery with `CompletableFuture`](src/main/java/org/codefx/demo/java12/api/future/Recovery.java)
* ⑪ [I/O](src/main/java/org/codefx/demo/java11/api/io)
* ⑪ [`Predicate` / `Pattern`](src/main/java/org/codefx/demo/java11/api/predicate)
* ⑪ [`Collection`](src/main/java/org/codefx/demo/java11/api/collection/ToArray.java)
* ⑪⑨ `Optional` [in Java 11](src/main/java/org/codefx/demo/java11/api/optional/IsEmpty.java)
  and [in Java 9](src/main/java/org/codefx/demo/java9/api/optional/Or.java) ([article](http://blog.codefx.org/java/dev/java-9-optional/))
* ⑨ [OS processes](src/main/java/org/codefx/demo/java9/api/processes/PipeProcessesAndAwaitCompletion.java) ([JEP 102](http://openjdk.java.net/jeps/102))

Some of the small changes have their own articles (in which case they are linked), but many don't.
Most are show-cased in these posts, though:

* [Definitive Guide To Java 12](https://blog.codefx.org/java/java-12-guide/)
* [Eleven Hidden Gems In Java 11](https://blog.codefx.org/java/java-11-gems/)

## JVM & Tooling

* ⑬⑫⑩ [application class-dara sharing](app-cds.sh) ([article](http://blog.codefx.org/java/application-class-data-sharing/), [JEP 310](http://openjdk.java.net/jeps/310), [JEP 341](http://openjdk.java.net/jeps/341), [JEP 350](http://openjdk.java.net/jeps/350))
* ⑪ [single-source-file execution](src/main/java/org/codefx/demo/java11/jvm/script) and scripting: run [the script](echo) with `cat echo-haiku.txt | ./echo` ([article](http://blog.codefx.org/java/scripting-java-shebang/), [JEP 330](https://openjdk.java.net/jeps/330))
* ⑨ multi-release JARs: [classes](src/main/java/org/codefx/demo/java9/internal/multi_release) and [the script](multi-release.sh) ([JEP 238](http://openjdk.java.net/jeps/238))
* ⑨ platform logging: [classes](src/platform_logging/java/org/codefx/demo/java9/api/platform_logging) and [the script](platform-logging.sh) ([JEP 264](http://openjdk.java.net/jeps/264))

## Internals

* ⑨ [new version string schema](src/main/java/org/codefx/demo/java9/internal/version/VersionSchema.java) ([JEP 223](http://openjdk.java.net/jeps/223))
* ⑨ [UTF-8 property files](src/main/java/org/codefx/demo/java9/internal/resources/ResourceFileEncoding.java) ([JEP 226](http://openjdk.java.net/jeps/226))
* ⑨ [reserved stack areas](src/main/java/org/codefx/demo/java9/internal/stack/ReservingStackAreas.java) ([JEP 270](http://openjdk.java.net/jeps/270))
* ⑨ [DRGB implementations for `SecureRandom`](src/main/java/org/codefx/demo/java9/internal/security/Drbg.java) ([JEP 273](http://openjdk.java.net/jeps/273))
* ⑨ [string compaction](src/main/java/org/codefx/demo/java9/internal/string) ([JEP 254](http://openjdk.java.net/jeps/254))
