# Java 9 Feature Demo

Demonstrates the features that will be available in Java 9.
The links below take you to the demo in this project, the JEP respnsible for introducing the feature, and to other sources if available.

Putting everything here will take a while, so you have to be patient.
If you can't, check out the [JDK 9 page](http://openjdk.java.net/projects/jdk9/) and look through the JEPs.
You can read more from me on [codefx.org](http://codefx.org) and follow me [on Twitter](https://twitter.com/nipafx)
 (and even [G+](https://plus.google.com/+NicolaiParlog), I guess).

## Java Platform Module System

Jigsaw is to big to demo here.
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
* [stack-walking](src/org/codefx/demo/java9/api/stack_walking/StackWalking.java) ([JEP 259](http://openjdk.java.net/jeps/259))
