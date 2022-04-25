# Java X Feature Demo

Demonstrates most features that were introduced after Java 8.
They are listed below with links that take you to the demos in this repo, the JEPs responsible for introducing the feature, and to other sources if available.
These are the categories:

* [Language Changes](#language-changes)
* [New APIs](#new-apis)
* [Updated APIs](#updated-apis)
* [Tooling](#tooling)
* [Runtime](#runtime)
* [Internals](#internals)

Circled numbers like ⑰ indicate which Java version introduced a feature or a change.
A circled X ⓧ indicates an incubating or previewing feature that is not yet standardized - it can be experimented with but is subject to change.

For a more practical approach to many of these features, including some quick code metrics, check my [_Java After Eight_ repo](https://github.com/nipafx/java-after-eight).
You can find more from me on [nipafx.dev](https://nipafx.dev) as well as on [YouTube](https://youtube.com/nipafx) and [Twitch](https://twitch.tv/nipafx).
To get in touch, follow me [on Twitter](https://twitter.com/nipafx) (DMs are open) or join [my Discord](https://discord.com/invite/7m9w8Td).

## Setup

This project requires at least the most recent Java release, at times even early-access builds of upcoming versions.
You can get OpenJDK builds for both directly from [jdk.java.net](http://jdk.java.net), although I prefer to use [SDKMAN](https://sdkman.io/) to install and manage them.
The project can be built with Maven, i.e. `mvn package`.

There are several ways to execute demos:

* If your IDE is up to speed (which may require preview versions or additional plugins), you can execute most demos straight from there.
* If a feature is contained in a single class, you can also use [single-source file execution](https://nipafx.dev/scripting-java-shebang/) and just call `java $SOURCE_FILE`.
* You can always build with `mvn package` and then run any specific class with ...
	```shell
	java -p target/java-x.jar -m dev.nipafx.demo.java_x/$CLASS
	```
  ... where $CLASS is the fully qualified name of the class you want to execute.

Depending on the demo you may have to add additional command line flags - this should be listed in the respective file.

For some features, you have to run the `.sh` scripts in this repo's root directory to see them in action.
If that's necessary, the feature list below mentions it.
For that to work, you need to ensure that a `java` binary from the respective JDK version is available on your path - again, I use SDKMAN for that.


## Language Changes

* ⑰ [sealed classes](src/main/java/dev/nipafx/demo/java17/lang/sealed)
  (articles
   [1](https://www.infoq.com/articles/java-sealed-classes/),
   [2](https://nipafx.dev/java-visitor-pattern-pointless/);
   [JEP 409](https://openjdk.java.net/jeps/409))
* ⑯ [`instanceof` pattern matching](src/main/java/dev/nipafx/demo/java16/lang/instanceof_)
  (articles
   [1](https://nipafx.dev/java-pattern-matching/),
   [2](https://nipafx.dev/java-type-pattern-matching/);
   [JEP 394](https://openjdk.java.net/jeps/394))
* ⑯ [records](src/main/java/dev/nipafx/demo/java16/lang/record)
  ([article](https://nipafx.dev/java-record-semantics/),
   [JEP 395](https://openjdk.java.net/jeps/395))
* ⑯ [static members of (non-static) inner classes](src/main/java/dev/nipafx/demo/java16/lang/staticinner/OuterClass.java)
  ([JDK-8254105](https://bugs.openjdk.java.net/browse/JDK-8254105))
* ⑮ [text blocks](src/main/java/dev/nipafx/demo/java15/lang/text_blocks/TextBlocks.java)
  ([article](https://nipafx.dev/java-13-text-blocks/),
   [JEP 378](https://openjdk.java.net/jeps/378))
* ⑭ [switch expressions](src/main/java/dev/nipafx/demo/java14/lang/switch_/Switch.java) ([article](https://nipafx.dev/java-13-switch-expressions/), [video](https://www.youtube.com/watch?v=1znHEf3oSNI), [JEP 361](https://openjdk.java.net/jeps/361))
* ⑩ [local-variable type inference with `var`](src/main/java/dev/nipafx/demo/java10/lang/var/VariableTypeInference.java)
  ([article](https://nipafx.dev/java-10-var-type-inference/),
   [video](https://www.youtube.com/watch?v=Le1DbpRZdRQ),
   [JEP 286](http://openjdk.java.net/jeps/286)):
	* experiments with [intersection types](src/main/java/dev/nipafx/demo/java10/lang/var/IntersectionTypes.java)
	  ([article](https://nipafx.dev/java-var-intersection-types/))
	* experiments with ad-hoc [fields](src/main/java/dev/nipafx/demo/java10/lang/var/AdHocFields.java) and [methods](src/main/java/dev/nipafx/demo/java10/lang/var/AdHocMethods.java)
	  ([article](https://nipafx.dev/java-var-anonymous-classes-tricks/))
	* experiments with [traits](src/main/java/dev/nipafx/demo/java10/lang/var/Traits.java)
	  ([article](https://nipafx.dev/java-var-traits/))
* ⑨ [private interface methods](src/main/java/dev/nipafx/demo/java9/lang/private_interface_methods/PrivateInterfaceMethods.java)
  ([JEP 213](http://openjdk.java.net/jeps/213))
* ⑨ [try-with-resources on effectively final variables](src/main/java/dev/nipafx/demo/java9/lang/try_with_resources/TryWithResources.java)
  ([JEP 213](http://openjdk.java.net/jeps/213))
* ⑨ [diamond operator for anonymous classes](src/main/java/dev/nipafx/demo/java9/lang/diamond_operator/DiamondOperator.java)
  ([JEP 213](http://openjdk.java.net/jeps/213))
* ⑨ [`@SaveVarargs` on private non-final methods](src/main/java/dev/nipafx/demo/java9/lang/safe_varargs/SafeVarargs.java)
  ([JEP 213](http://openjdk.java.net/jeps/213))
* ⑨ [no warnings for deprecated imports](src/main/java/dev/nipafx/demo/java9/lang/deprecated_imports/DeprecatedImports.java)
  ([JEP 211](http://openjdk.java.net/jeps/211))

### Module System

The module system is too big to demo here.
Check out the [j_ms](https://nipafx.dev/#tags~~j_ms) tag on my blog (for example to find [this handy cheat-sheet](https://nipafx.dev/build-modules/)), [the dedicated demo project](https://github.com/nipafx/demo-jpms-monitor), [a project with solutions to common issues](https://github.com/nipafx/module-system-woes/), or my book [_The Java Module System_](https://www.manning.com/books/the-java-module-system?a_aid=nipa&a_bid=869915cb).


## New APIs

If an API that was introduced in Java 9+ was later updated, the update is listed in the next section.

* ⓧ [vector API](src/main/java/dev/nipafx/demo/java_next/api/vector)
  ([JEP 426](https://openjdk.java.net/jeps/426))
* ⑱ [address resolution SPI](src/main/java/dev/nipafx/demo/java18/api/ip_resolution)
  ([JEP 418](http://openjdk.java.net/jeps/418))
* ⑰ [random generator](src/main/java/dev/nipafx/demo/java17/api/random)
  ([article](https://nipafx.dev/java-random-generator/),
   [JEP 356](https://openjdk.java.net/jeps/356),
   [very good Javadoc](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/random/package-summary.html))
* ⑯ [Unix domain sockets](src/main/java/dev/nipafx/demo/java16/api/unix_sockets)
  ([article](https://nipafx.dev/java-unix-domain-sockets/),
   [JEP-380](https://openjdk.java.net/jeps/380))
* ⑪ HTTP/2 client
  ([tutorial](https://nipafx.dev/java-http-2-api-tutorial/),
   [reactive request/response bodies](https://nipafx.dev/java-reactive-http-2-requests-responses/)):
	* [simple](src/main/java/dev/nipafx/demo/java11/api/http2/Http2Api.java)
	* [more formal](src/main/java/dev/nipafx/demo/java11/api/http2/formalized)
* ⑨ [version API](src/main/java/dev/nipafx/demo/java9/api/version/VersionApi.java)
* ⑨ [collection factory methods](src/main/java/dev/nipafx/demo/java9/api/collection_factory_methods/CollectionFactories.java)
  ([JEP 269](http://openjdk.java.net/jeps/269))
* ⑨ [reactive streams](src/main/java/dev/nipafx/demo/java9/api/reactive_streams)
  ([JEP 266](http://openjdk.java.net/jeps/266))
* ⑨ [stack walking](src/main/java/dev/nipafx/demo/java9/api/stack_walking/StackWalking.java)
  ([tutorial with benchmarks](https://www.sitepoint.com/deep-dive-into-java-9s-stack-walking-api/),
   [JEP 259](http://openjdk.java.net/jeps/259))
* ⑨ [multi-resolution images](src/main/java/dev/nipafx/demo/java9/api/multi_resolution_images/Images.java) ([JEP 251](http://openjdk.java.net/jeps/251))
* ⑨ platform-specific desktop features
  (not supported by my OS so [my sample](src/main/java/dev/nipafx/demo/java9/api/desktop/DesktopFeatures.java) sucks; PRs welcome!
   [JEP 272](http://openjdk.java.net/jeps/272))
* ⑨ [deserialization filter](src/main/java/dev/nipafx/demo/java9/api/deserialization_filter/SerializeThenFilter.java) ([JEP 290](http://openjdk.java.net/jeps/290))

## Updated APIs

* ⑯ (server) socket channels: [Unix domain socket support](src/main/java/dev/nipafx/demo/java16/api/unix_sockets)
  ([article](https://nipafx.dev/java-unix-domain-sockets/),
   [JEP 380](https://openjdk.java.net/jeps/380))
* ⑯ HTTP/2 client: [copying a request](src/main/java/dev/nipafx/demo/java16/api/http2/CopyRequest.java)
* `Stream`:
	* ⑯: [`mapMulti`](src/main/java/dev/nipafx/demo/java16/api/stream)
	  (article
	   [1](https://nipafx.dev/java-16-stream-mapmulti),
	   [2](https://nipafx.dev/java-16-stream-mapmulti-group))
	* ⑫: [teeing collector](src/main/java/dev/nipafx/demo/java12/api/stream/TeeingCollector.java)
	  ([article](https://nipafx.dev/java-12-teeing-collector/))
	* ⑩: [to-unmodifiable collector](src/main/java/dev/nipafx/demo/java10/api/stream/CollectToUnmodifiable.java)
	* ⑨: [`takeWhile`, `dropWhile`, `iterate`, `ofNullable`](src/main/java/dev/nipafx/demo/java9/api/stream)
	  ([article](https://nipafx.dev/java-9-stream/))
* `String`:
	* ⑮: [`formatted`](src/main/java/dev/nipafx/demo/java15/api/string/FormatString.java),
	* ⑫:
	  [`indent`](src/main/java/dev/nipafx/demo/java12/api/string/Indent.java),
	  [`transform`](src/main/java/dev/nipafx/demo/java12/api/string/Transform.java)
	* ⑪:
	  [`lines`](src/main/java/dev/nipafx/demo/java11/api/string/Lines.java),
	  [`repeat`](src/main/java/dev/nipafx/demo/java11/api/string/Repeat.java),
	  [`strip`](src/main/java/dev/nipafx/demo/java11/api/string/Strip.java)
* ⑭ [`@Serial`](src/main/java/dev/nipafx/demo/java14/lang/serial/SerialAnnotation.java) ([Javadoc](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/io/Serial.html))
* ⑫ [formatting numbers](src/main/java/dev/nipafx/demo/java12/api/format/CompactFormat.java)
* I/O:
	* ⑫: [`Files::mismatch`](src/main/java/dev/nipafx/demo/java12/api/io/FilesMismatch.java)
	* ⑪:
	  [null streams/readers](src/main/java/dev/nipafx/demo/java11/api/io/Null_IO.java),
	  [`Path::of`](src/main/java/dev/nipafx/demo/java11/api/io/PathOf.java),
	  [`Files::readString/writeString`](src/main/java/dev/nipafx/demo/java11/api/io/ReadAndWriteFiles.java)
* `CompletableFuture`:
	* ⑫: [`exceptionallyCompose`, `exceptionallyComposeAsync`](src/main/java/dev/nipafx/demo/java12/api/future/Recovery.java)
	* ⑨: [lots of additions](src/main/java/dev/nipafx/demo/java9/api/future/CompletableAdditions.java)
* ⑪ [`Predicate::not`](src/main/java/dev/nipafx/demo/java11/api/predicate/Not.java)
* ⑪ [`Pattern::asMatchPredicate`](src/main/java/dev/nipafx/demo/java11/api/regex/AsMatchPredicate.java)
* collections
	* ⑪: [`Collection::toArray` overload](src/main/java/dev/nipafx/demo/java11/api/collection/ToArray.java)
	* ⑩: [`List/Set/Map::copyOf`](src/main/java/dev/nipafx/demo/java10/api/collection_factory_methods/CopyOf.java)
* `Optional`:
	* ⑪: [`isEmpty`](src/main/java/dev/nipafx/demo/java11/api/optional/IsEmpty.java)
	* ⑩: [`orElseThrow`](src/main/java/dev/nipafx/demo/java10/api/optional/OrElseThrow.java)
	* ⑨: [`or`](src/main/java/dev/nipafx/demo/java9/api/optional/Or.java), `ifPresentOrElse`, `stream`
	  ([article](https://nipafx.dev/java-9-optional/))
* ⑩ version API: [`feature()`, `interim()`, `update()`](src/main/java/dev/nipafx/demo/java10/api/version/VersionApi.java)
* ⑨ [OS processes](src/main/java/dev/nipafx/demo/java9/api/processes)
  ([JEP 102](http://openjdk.java.net/jeps/102))

A few changes have their own articles (in which case they are linked), but many don't.
Some of them are show-cased in these posts, though:

* [Definitive Guide To Java 13](https://nipafx.dev/java-13-guide/)
* [Definitive Guide To Java 12](https://nipafx.dev/java-12-guide/)
* [Eleven Hidden Gems In Java 11](https://nipafx.dev/java-11-gems/)
* [All You Need To Know For Migrating To Java 11](https://nipafx.dev/java-11-migration-guide/)
* [Code-First Java 9 Tutorial](https://nipafx.dev/java-9-tutorial/)

## Tooling

* ⑱ external snippets in Javadoc
  ([video](https://nipafx.dev/inside-java-newscast-20/),
   [Maven how-to](https://nipafx.dev/javadoc-snippets-maven/),
   [JEP 413](http://openjdk.java.net/jeps/413)):
	* [referencing class (and how-to)](src/main/java/dev/nipafx/demo/java18/javadoc/SnippetDocs.java)
	* [referenced demo class](src/demo/java/SnippetDocsDemo.java)
	* [build configuration](pom.xml)
* ⑱ [`jwebserver`, a simple web server](simple-webserver.sh)
  ([video](https://www.youtube.com/watch?v=IsCEzP-inkU),
   [JEP 408](https://openjdk.java.net/jeps/408))

## Runtime

* helpful `NullPointerException`s:
	* ⑯: [enabled by default](src/main/java/dev/nipafx/demo/java16/runtime/npe/ShowNpeDetailsByDefault.java)
	* ⑭: [introduced](src/main/java/dev/nipafx/demo/java14/runtime/npe/ShowNpeDetails.java)
	  ([JEP 358](https://openjdk.java.net/jeps/358))
* ⑬⑫⑩ [application class-dara sharing](app-cds.sh)
  ([article](https://nipafx.dev/java-application-class-data-sharing/),
   [JEP 310](http://openjdk.java.net/jeps/310),
   [JEP 341](http://openjdk.java.net/jeps/341),
   [JEP 350](http://openjdk.java.net/jeps/350))
* ⑪ single-source-file execution
  ([article](https://nipafx.dev/scripting-java-shebang/),
   [JEP 330](https://openjdk.java.net/jeps/330)):
	* [getting started](src/main/java/dev/nipafx/demo/java11/runtime/script/HelloJavaScripts.java)
	* [echo class](src/main/java/dev/nipafx/demo/java11/runtime/script/Echo.java)
	* [proper script](echo) (run with `cat echo-haiku.txt | ./echo`)
* ⑨ [unified logging](unified-logging.sh)
  ([article](https://nipafx.dev/java-unified-logging-xlog/),
   [JEP 158](http://openjdk.java.net/jeps/158))
* ⑨ multi-release JARs: [classes](src/main/java/dev/nipafx/demo/java9/runtime/multi_release) and [the script](multi-release.sh) ([JEP 238](http://openjdk.java.net/jeps/238))
* ⑨ platform logging: [classes](src/platform_logging/java/dev/nipafx/demo/java9/api/platform_logging) and [the script](platform-logging.sh) ([JEP 264](http://openjdk.java.net/jeps/264))

## Internals

* ⑨ [new version string schema](src/main/java/dev/nipafx/demo/java9/runtime/version/VersionSchema.java) ([JEP 223](http://openjdk.java.net/jeps/223))
* ⑨ [UTF-8 property files](src/main/java/dev/nipafx/demo/java9/runtime/resources/ResourceFileEncoding.java) ([JEP 226](http://openjdk.java.net/jeps/226))
* ⑨ [reserved stack areas](src/main/java/dev/nipafx/demo/java9/runtime/stack/ReservingStackAreas.java) ([JEP 270](http://openjdk.java.net/jeps/270))
* ⑨ [DRGB implementations for `SecureRandom`](src/main/java/dev/nipafx/demo/java9/runtime/security/Drbg.java) ([JEP 273](http://openjdk.java.net/jeps/273))
* ⑨ [string compaction](src/main/java/dev/nipafx/demo/java9/runtime/string) ([JEP 254](http://openjdk.java.net/jeps/254))
