# Java X Feature Demo

Demonstrates most features that were introduced by Java 9 and later versions.
The links below take you to the demos in this project, the JEPs responsible for introducing the feature, and to other sources if available.

For a more practical approach to many of these features, including some quick code metrics, check my [_Java After Eight_ repo](https://github.com/nipafx/java-after-eight). 

These articles discuss the recent Java versions and list a lot of the new features:

**Java 13:**

* [Definitive Guide To Java 13](https://nipafx.dev/java-13-guide/)
* [81 New Features and APIs in JDK 13](https://www.azul.com/jdk-13-81-new-features-and-apis/)

**Java 12:**

* [Definitive Guide To Java 12](https://nipafx.dev/java-12-guide/)
* [39 New Features (and APIs) in JDK 12](https://www.azul.com/39-new-features-and-apis-in-jdk-12/)

**Java 11:**

* [All You Need To Know For Migrating To Java 11](https://nipafx.dev/java-11-migration-guide/)
* [Java 11 Tutorial](https://winterbe.com/posts/2018/09/24/java-11-tutorial/)
* [90 New Features (and APIs) in JDK 11](https://www.azul.com/90-new-features-and-apis-in-jdk-11/)

**Java 9:**

* [Code-First Java 9 Tutorial](https://nipafx.dev/java-9-tutorial/)
* [The Ultimate Guide to Java 9](https://www.sitepoint.com/ultimate-guide-to-java-9/)
* [Inside Java 9 – Version Schema, Multi-Release JARs, and More](https://www.sitepoint.com/inside-java-9-part-i/)
* [Inside Java 9 – Performance, Compiler, and More](https://www.sitepoint.com/inside-java-9-part-ii/)

You can find more from me on [nipafx.dev](https://nipafx.dev) as well as on [YouTube](https://youtube.com/nipafx) and [Twitch](https://twitch.tv/nipafx).
To get in touch, follow me [on Twitter](https://twitter.com/nipafx) or join [my Discord](https://discord.com/invite/7m9w8Td).

## Setup

This project requires at least the most recent Java release, at times even early-access builds of upcoming versions.
You can get OpenJDK builds for both from [jdk.java.net](http://jdk.java.net), although I prefer to use [SDKMAN](https://sdkman.io/).
Most of the project can be built with Maven, i.e. `mvn package`.

If your IDE is up to speed (which may require preview versions or additional plugins), you can execute most demos straight from there.
Otherwise, you can always build with `mvn pckage` and then run any specific class with ...

```shell
java -p target/java-x.jar -m dev.nipafx.demo.java_x/$CLASS
```

... where $CLASS is the class you want to execute.
Depending on the demo you may have to add additional command line flags - they should be listed in the respective demo.

For some features, you _have to_ run the `.sh` scripts in the root directory.
If that's necessary, the feature list below mentions it.
For that to work, you need to ensure that a `java` binary from the respective JDK version is availble on your path - again, I use SDKMAN for that.

## Java Platform Module System

The module system is too big to demo here.
Check out the [j_ms](https://nipafx.dev/#tags~~j_ms) tag on my blog (for example to find [this handy cheat-sheet](https://nipafx.dev/build-modules/)), [this demo project](https://github.com/nipafx/demo-jpms-monitor), or [my book on the module system](https://www.manning.com/books/the-java-module-system?a_aid=nipa&a_bid=869915cb).

## Language Changes

* ⑰ [sealed classes](src/main/java/dev/nipafx/demo/java17/lang/sealed) (articles [1](https://www.infoq.com/articles/java-sealed-classes/), [2](https://nipafx.dev/java-visitor-pattern-pointless/), [JEP 409](https://openjdk.java.net/jeps/409))
* ⑯ [`instanceof` pattern matching](src/main/java/dev/nipafx/demo/java16/lang/instanceof_) (articles [1](https://nipafx.dev/java-pattern-matching/), [2](https://nipafx.dev/java-type-pattern-matching/), [JEP 394](https://openjdk.java.net/jeps/394))
* ⑯ [records](src/main/java/dev/nipafx/demo/java16/lang/record) ([article](https://nipafx.dev/java-record-semantics/), [JEP 395](https://openjdk.java.net/jeps/395))
* ⑯ [static members of (non-static) inner classes](src/main/java/dev/nipafx/demo/java16/lang/staticinner/OuterClass.java) ([JDK-8254105](https://bugs.openjdk.java.net/browse/JDK-8254105))
* ⑮ [text blocks](src/main/java/dev/nipafx/demo/java15/lang/text_blocks/TextBlocks.java) ([article](https://nipafx.dev/java-13-text-blocks/), [JEP 378](https://openjdk.java.net/jeps/378))
* ⑭ [switch expressions](src/main/java/dev/nipafx/demo/java14/lang/switch_/Switch.java) ([article](https://nipafx.dev/java-13-switch-expressions/), [video](https://www.youtube.com/watch?v=1znHEf3oSNI), [JEP 361](https://openjdk.java.net/jeps/361))
* ⑩ [local-variable type inference with `var`](src/main/java/dev/nipafx/demo/java10/lang/var/VariableTypeInference.java) ([article](https://nipafx.dev/java-10-var-type-inference/), [video](https://www.youtube.com/watch?v=Le1DbpRZdRQ), [JEP 286](http://openjdk.java.net/jeps/286))
	* experiments with [intersection types](src/main/java/dev/nipafx/demo/java10/lang/var/IntersectionTypes.java) ([article](https://nipafx.dev/java-var-intersection-types/))
	* experiments with ad-hoc [fields](src/main/java/dev/nipafx/demo/java10/lang/var/AdHocFields.java) and [methods](src/main/java/dev/nipafx/demo/java10/lang/var/AdHocMethods.java) ([article](https://nipafx.dev/java-var-anonymous-classes-tricks/))
	* experiments with [traits](src/main/java/dev/nipafx/demo/java10/lang/var/Traits.java) ([article](https://nipafx.dev/java-var-traits/))
* ⑨ [private interface methods](src/main/java/dev/nipafx/demo/java9/lang/private_interface_methods/PrivateInterfaceMethods.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* ⑨ [try-with-resources on effectively final variables](src/main/java/dev/nipafx/demo/java9/lang/try_with_resources/TryWithResources.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* ⑨ [diamond operator for anonymous classes](src/main/java/dev/nipafx/demo/java9/lang/diamond_operator/DiamondOperator.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* ⑨ [`@SaveVarargs` on private non-final methods](src/main/java/dev/nipafx/demo/java9/lang/safe_varargs/SafeVarargs.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* ⑨ [no warnings for deprecated imports](src/main/java/dev/nipafx/demo/java9/lang/deprecated_imports/DeprecatedImports.java) ([JEP 211](http://openjdk.java.net/jeps/211))

## New APIs

* ⑱ [address resolution SPI](src/main/java/dev/nipafx/demo/java18/api/ip_resolution) ([JEP 418](http://openjdk.java.net/jeps/418))
* ⑰ [random generator](src/main/java/dev/nipafx/demo/java17/api/random) ([article](https://nipafx.dev/java-random-generator/), [JEP 356](https://openjdk.java.net/jeps/356), [very good Javadoc](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/random/package-summary.html))
* ⑯ [Unix domain sockets](src/main/java/dev/nipafx/demo/java16/api/unix_sockets) ([article](https://nipafx.dev/java-unix-domain-sockets/), [JEP-380](https://openjdk.java.net/jeps/380))
* ⑯⑪ HTTP/2 client: [simple](src/main/java/dev/nipafx/demo/java11/api/http2/Http2Api.java), [more formal](src/main/java/dev/nipafx/demo/java11/api/http2/formalized) ([tutorial](https://nipafx.dev/java-http-2-api-tutorial/), [reactive request/response bodies](https://nipafx.dev/java-reactive-http-2-requests-responses/)); extended [in Java 16](src/main/java/dev/nipafx/demo/java16/api/http2/Example.java)
* ⑩⑨ version API, [introduced in Java 9](src/main/java/dev/nipafx/demo/java9/api/version/VersionApi.java),
  [updated in Java 10](src/main/java/dev/nipafx/demo/java10/api/version/VersionApi.java)
* ⑩⑨ collection factory methods [in Java 10](src/main/java/dev/nipafx/demo/java10/api/collection_factory_methods/CopyOf.java) and [in Java 9](src/main/java/dev/nipafx/demo/java9/api/collection_factory_methods) ([JEP 269](http://openjdk.java.net/jeps/269))
* ⑨ [reactive streams](src/main/java/dev/nipafx/demo/java9/api/reactive_streams) ([JEP 266](http://openjdk.java.net/jeps/266))
* ⑨ [stack walking](src/main/java/dev/nipafx/demo/java9/api/stack_walking/StackWalking.java) ([JEP 259](http://openjdk.java.net/jeps/259), [post on SitePoint](https://www.sitepoint.com/deep-dive-into-java-9s-stack-walking-api/) including benchmarks)
* ⑨ [multi-resolution images](src/main/java/dev/nipafx/demo/java9/api/multi_resolution_images/Images.java) ([JEP 251](http://openjdk.java.net/jeps/251))
* ⑨ platform-specific desktop features (not supported by my OS so [my sample](src/main/java/dev/nipafx/demo/java9/api/desktop/DesktopFeatures.java) sucks; PRs welcome! [JEP 272](http://openjdk.java.net/jeps/272))
* ⑨ [deserialization filter](src/main/java/dev/nipafx/demo/java9/api/deserialization_filter) ([JEP 290](http://openjdk.java.net/jeps/290))

## Updated APIs

* ⑯ [Unix domain socket support in `SocketChannel`/`ServerSocketChannel` API](src/main/java/dev/nipafx/demo/java16/api/unix_sockets) ([JEP 380](https://openjdk.java.net/jeps/380), [article](https://nipafx.dev/java-unix-domain-sockets/))
* ⑯⑫⑩⑨ `Stream` [in Java 16](src/main/java/dev/nipafx/demo/java16/api/stream) (article [1](https://nipafx.dev/java-16-stream-mapmulti), [2](https://nipafx.dev/java-16-stream-mapmulti-group)),
  [in Java 12](src/main/java/dev/nipafx/demo/java12/api/stream/TeeingCollector.java) ([article](https://nipafx.dev/java-12-teeing-collector/)),
  [in Java 10](src/main/java/dev/nipafx/demo/java10/api/stream/CollectToUnmodifiable.java),
  and [in Java 9](src/main/java/dev/nipafx/demo/java9/api/stream) ([article](https://nipafx.dev/java-9-stream/))
* ⑮⑫⑪ `String` [in Java 15](src/main/java/dev/nipafx/demo/java15/api/string/FormatString.java),
  [in Java 12](src/main/java/dev/nipafx/demo/java12/api/string),
  and [in Java 11](src/main/java/dev/nipafx/demo/java11/api/string)
* ⑭ [`@Serial`](src/main/java/dev/nipafx/demo/java14/lang/serial/SerialAnnotation.java) ([Javadoc](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/io/Serial.html))
* ⑫ [formating numbers](src/main/java/dev/nipafx/demo/java12/api/format/CompactFormat.java)
* ⑫ [`Files::mismatch`](src/main/java/dev/nipafx/demo/java12/api/files/FilesMismatch.java)
* ⑫ `CompletableFuture` [in Java 12](src/main/java/dev/nipafx/demo/java12/api/future/Recovery.java) and [in Java 9](src/main/java/dev/nipafx/demo/java9/api/future/CompletableAdditions.java)
* ⑪ [I/O](src/main/java/dev/nipafx/demo/java11/api/io)
* ⑪ [`Predicate` / `Pattern`](src/main/java/dev/nipafx/demo/java11/api/predicate)
* ⑪ [`Collection`](src/main/java/dev/nipafx/demo/java11/api/collection/ToArray.java)
* ⑪⑩⑨ `Optional` [in Java 11](src/main/java/dev/nipafx/demo/java11/api/optional/IsEmpty.java), [in Java 10](src/main/java/dev/nipafx/demo/java10/api/optional/OrElseThrow.java),
  and [in Java 9](src/main/java/dev/nipafx/demo/java9/api/optional/Or.java) ([article](https://nipafx.dev/java-9-optional/))
* ⑨ [OS processes](src/main/java/dev/nipafx/demo/java9/api/processes) ([JEP 102](http://openjdk.java.net/jeps/102))

Some of the small changes have their own articles (in which case they are linked), but many don't.
Most are show-cased in these posts, though:

* [Definitive Guide To Java 12](https://nipafx.dev/java-12-guide/)
* [Eleven Hidden Gems In Java 11](https://nipafx.dev/java-11-gems/)

## Runtime & Tooling

* ⑱ external snippets in Javadoc: [referencing class](src/main/java/dev/nipafx/demo/java18/javadoc/SnippetDocs.java), [referenced demo class](src/demo/java/SnippetDocsDemo.java), and [configuration](pom.xml) ([feature introduction](https://nipafx.dev/inside-java-newscast-20/), [Maven how-to](https://nipafx.dev/javadoc-snippets-maven/), [JEP 413](http://openjdk.java.net/jeps/413))
* ⑯⑭ [helpful `NullPointerException`s]:
  added [in Java 14](src/main/java/dev/nipafx/demo/java14/runtime/npe/ShowNpeDetails.java) ([JEP 358](https://openjdk.java.net/jeps/358)),
  enabled by default [in Java 16](src/main/java/dev/nipafx/demo/java16/runtime/npe/ShowNpeDetailsByDefault.java)
* ⑬⑫⑩ [application class-dara sharing](app-cds.sh) ([article](https://nipafx.dev/java-application-class-data-sharing/), [JEP 310](http://openjdk.java.net/jeps/310), [JEP 341](http://openjdk.java.net/jeps/341), [JEP 350](http://openjdk.java.net/jeps/350))
* ⑪ [single-source-file execution](src/main/java/dev/nipafx/demo/java11/runtime/script) and scripting: run [the script](echo) with `cat echo-haiku.txt | ./echo` ([article](https://nipafx.dev/scripting-java-shebang/), [JEP 330](https://openjdk.java.net/jeps/330))
* ⑨ [unified logging](unified-logging.sh) ([article](https://nipafx.dev/java-unified-logging-xlog/), [JEP 158](http://openjdk.java.net/jeps/158))
* ⑨ multi-release JARs: [classes](src/main/java/dev/nipafx/demo/java9/runtime/multi_release) and [the script](multi-release.sh) ([JEP 238](http://openjdk.java.net/jeps/238))
* ⑨ platform logging: [classes](src/platform_logging/java/dev/nipafx/demo/java9/api/platform_logging) and [the script](platform-logging.sh) ([JEP 264](http://openjdk.java.net/jeps/264))

## Internals

* ⑨ [new version string schema](src/main/java/dev/nipafx/demo/java9/runtime/version/VersionSchema.java) ([JEP 223](http://openjdk.java.net/jeps/223))
* ⑨ [UTF-8 property files](src/main/java/dev/nipafx/demo/java9/runtime/resources/ResourceFileEncoding.java) ([JEP 226](http://openjdk.java.net/jeps/226))
* ⑨ [reserved stack areas](src/main/java/dev/nipafx/demo/java9/runtime/stack/ReservingStackAreas.java) ([JEP 270](http://openjdk.java.net/jeps/270))
* ⑨ [DRGB implementations for `SecureRandom`](src/main/java/dev/nipafx/demo/java9/runtime/security/Drbg.java) ([JEP 273](http://openjdk.java.net/jeps/273))
* ⑨ [string compaction](src/main/java/dev/nipafx/demo/java9/runtime/string) ([JEP 254](http://openjdk.java.net/jeps/254))
