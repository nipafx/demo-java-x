#!/bin/bash

rm -rf out-mr
mkdir -p out-mr/java-8-src
mkdir -p out-mr/java-8
mkdir -p out-mr/java-9-src
mkdir -p out-mr/java-9

echo "compile classes for Java 8"
sed 's/VersionDependent8/VersionDependent/g' \
	src/org/codefx/demo/java9/internal/multi_release/VersionDependent8.java \
	> out-mr/java-8-src/VersionDependent.java
sed 's/VersionDependent8/VersionDependent/g' \
	src/org/codefx/demo/java9/internal/multi_release/Main.java \
	> out-mr/java-8-src/Main.java
javac9 --release 8 -d out-mr/java-8 out-mr/java-8-src/*.java

echo "compile classes for Java 9"
sed 's/VersionDependent9/VersionDependent/g' \
	src/org/codefx/demo/java9/internal/multi_release/VersionDependent9.java \
	> out-mr/java-9-src/VersionDependent.java
javac9 --release 8 -d out-mr/java-9 out-mr/java-9-src/*.java

echo "package"
jar9 --create --file out-mr/mr.jar -C out-mr/java-8 . --release 9 -C out-mr/java-9 .

echo "run with Java 8:"
java -cp out-mr/mr.jar org.codefx.demo.java9.internal.multi_release.Main

echo "run with Java 9:"
java9 -cp out-mr/mr.jar org.codefx.demo.java9.internal.multi_release.Main
