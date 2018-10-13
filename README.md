# Java After Eight Feature Demo

Demonstrates the features of Java 9, 10, and 11.
The links below take you to the demos in this project, the JEPs responsible for introducing the feature, and to other sources if available.

These articles discuss the recent Java versions and list a lot of the new features:

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

Most of the project can be built with Maven.
For some features, though, you need to run the `.sh` scripts in the root directory.
The scripts are written for Linux but should look similar on other operating systems.
If the approprate Java verson is not on your path, configure it in `executables.sh`.

## Java Platform Module System

The module system is too big to demo here.
Check out the [jpms](http://blog.codefx.org/tag/jpms/) tag on my blog, [this demo project](https://github.com/CodeFX-org/demo-jpms-monitor), or [my book on the module system](https://www.manning.com/books/the-java-module-system?a_aid=nipa&a_bid=869915cb).

## Language Changes

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

* ⑨ [collection factory methods](src/main/java/org/codefx/demo/java9/api/collection_factory_methods) (instead of collection literals; [JEP 269](http://openjdk.java.net/jeps/269))
* ⑨ [reactive streams](src/main/java/org/codefx/demo/java9/api/reactive_streams) ([JEP 266](http://openjdk.java.net/jeps/266))
* ⑨ [stack walking](src/main/java/org/codefx/demo/java9/api/stack_walking/StackWalking.java) ([JEP 259](http://openjdk.java.net/jeps/259), [post on SitePoint](https://www.sitepoint.com/deep-dive-into-java-9s-stack-walking-api/) including benchmarks)
* ⑨ [multi-resolution images](src/main/java/org/codefx/demo/java9/api/multi_resolution_images/Images.java) ([JEP 251](http://openjdk.java.net/jeps/251))
* ⑨ platform-specific desktop features (not supported by my OS so [my sample](src/main/java/org/codefx/demo/java9/api/desktop/DesktopFeatures.java) sucks; PRs welcome! [JEP 272](http://openjdk.java.net/jeps/272))
* ⑨ deserialization filter (targeted for JDK 9 but wasn't implemented when I created this; [JEP 290](http://openjdk.java.net/jeps/290))

## Updated APIs

* ⑨ [`Stream` improvements](src/main/java/org/codefx/demo/java9/api/stream) ([article](http://blog.codefx.org/java/dev/java-9-stream/))
* ⑨ [`Optional` improvements](src/main/java/org/codefx/demo/java9/api/optional) ([article](http://blog.codefx.org/java/dev/java-9-optional/))
* ⑨ [OS processes](src/main/java/org/codefx/demo/java9/api/processes/PipeProcessesAndAwaitCompletion.java) ([JEP 102](http://openjdk.java.net/jeps/102))

## JVM & Tooling

* ⑩ [application class-dara sharing](app-cds.sh) ([article](http://blog.codefx.org/java/application-class-data-sharing/), [JEP 310](http://openjdk.java.net/jeps/310))
* ⑨ multi-release JARs: [classes](src/main/java/org/codefx/demo/java9/internal/multi_release) and [the script](multi-release.sh) ([JEP 238](http://openjdk.java.net/jeps/238))
* ⑨ platform logging: [classes](src/platform_logging/java/org/codefx/demo/java9/api/platform_logging) and [the script](platform-logging.sh) ([JEP 264](http://openjdk.java.net/jeps/264))

## Internals

* ⑨ [new version string schema](src/main/java/org/codefx/demo/java9/internal/version/VersionSchema.java) ([JEP 223](http://openjdk.java.net/jeps/223))
* ⑨ [UTF-8 property files](src/main/java/org/codefx/demo/java9/internal/resources/ResourceFileEncoding.java) ([JEP 226](http://openjdk.java.net/jeps/226))
* ⑨ [reserved stack areas](src/main/java/org/codefx/demo/java9/internal/stack/ReservingStackAreas.java) ([JEP 270](http://openjdk.java.net/jeps/270))
* ⑨ [DRGB implementations for `SecureRandom`](src/main/java/org/codefx/demo/java9/internal/security/Drbg.java) ([JEP 273](http://openjdk.java.net/jeps/273))
* ⑨ [string compaction](src/main/java/org/codefx/demo/java9/internal/string) ([JEP 254](http://openjdk.java.net/jeps/254))
