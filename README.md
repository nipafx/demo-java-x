# Java 9 Feature Demo

Demonstrates the features that will be available in Java 9.
The links below take you to the demo in this project, the JEP responsible for introducing the feature, and to other sources if available.

This article list a lot of the upcoming changes:

* [The Ultimate Guide to Java 9](https://www.sitepoint.com/ultimate-guide-to-java-9/)

Putting everything here will take a while, so you have to be patient.
If you can't, check out the [JDK 9 page](http://openjdk.java.net/projects/jdk9/) and look through the JEPs.
You can read more from me on [codefx.org](http://codefx.org) and follow me [on Twitter](https://twitter.com/nipafx)
 (and even [G+](https://plus.google.com/+NicolaiParlog), I guess).

## Java Platform Module System

Jigsaw is too big to demo here.
Check out [my blog](http://blog.codefx.org/tag/project-jigsaw/)
 or [this demo project](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar).

## Language Changes

* [private interface methods](src/org/codefx/demo/java9/lang/private_interface_methods/PrivateInterfaceMethods.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* [try-with-resources on effectively final variables](src/org/codefx/demo/java9/lang/try_with_resources/TryWithResources.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* [diamond operator for anonymous classes](src/org/codefx/demo/java9/lang/diamond_operator/DiamondOperator.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* [`@SaveVarargs` on private non-final methods](src/org/codefx/demo/java9/lang/safe_varargs/SafeVarargs.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* [no warnings for deprecated imports](src/org/codefx/demo/java9/lang/deprecated_imports/DeprecatedImports.java) ([JEP 211](http://openjdk.java.net/jeps/211))

## APIs

* [OS processes](src/org/codefx/demo/java9/api/processes/PipeProcessesAndAwaitCompletion.java) ([JEP 102](http://openjdk.java.net/jeps/102))
* [multi-resolution images](src/org/codefx/demo/java9/api/multi_resolution_images/Images.java) ([JEP 251](http://openjdk.java.net/jeps/251))
* [stack walking](src/org/codefx/demo/java9/api/stack_walking/StackWalking.java) ([JEP 259](http://openjdk.java.net/jeps/259))
* [platform logging](src/org/codefx/demo/java9/api/platform_logging) ([JEP 264](http://openjdk.java.net/jeps/264))
* [reactive streams](src/org/codefx/demo/java9/api/reactive_streams) ([JEP 266](http://openjdk.java.net/jeps/266))
* [collection factory methods](src/org/codefx/demo/java9/api/collection_factory_methods) (instead of collection literals; [JEP 269](http://openjdk.java.net/jeps/269))
* platform-specific desktop features (not supported by my OS so [my sample](src/org/codefx/demo/java9/api/desktop/DesktopFeatures.java) sucks; PRs welcome! [JEP 272](http://openjdk.java.net/jeps/272))
* deserialization filter (targeted for JDK 9 but not yet implemented; [JEP 290](http://openjdk.java.net/jeps/290))

## Internals

* [new version string schema](src/org/codefx/demo/java9/internal/version/VersionSchema.java) ([JEP 223](http://openjdk.java.net/jeps/223))
* [UTF-8 property files](src/org/codefx/demo/java9/internal/resources/ResourceFileEncoding.java) ([JEP 226](http://openjdk.java.net/jeps/226))
* [reserved stack areas](src/org/codefx/demo/java9/internal/stack/ReservingStackAreas.java) ([JEP 270](http://openjdk.java.net/jeps/270))
* [DRGB implementations for `SecureRandom`](src/org/codefx/demo/java9/internal/security/Drbg.java) ([JEP 273](http://openjdk.java.net/jeps/273))
