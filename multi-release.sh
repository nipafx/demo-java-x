#!/bin/bash

rm -rf out-mr/*
mkdir -p out-mr/java-8
mkdir -p out-mr/java-9

echo "compile classes for Java 8"
cp src/org/codefx/demo/java9/internal/multi_release/VersionDependent8.java src/org/codefx/demo/java9/internal/multi_release/VersionDependent.java
$JAVA9_HOME/bin/javac --release 8 -d out-mr/java-8 \
	src/org/codefx/demo/java9/internal/multi_release/VersionDependent.java \
	src/org/codefx/demo/java9/internal/multi_release/Main.java
rm src/org/codefx/demo/java9/internal/multi_release/VersionDependent.java

echo "compile classes for Java 9"
cp src/org/codefx/demo/java9/internal/multi_release/VersionDependent9.java src/org/codefx/demo/java9/internal/multi_release/VersionDependent.java
$JAVA9_HOME/bin/javac --release 8 -d out-mr/java-9 \
	src/org/codefx/demo/java9/internal/multi_release/VersionDependent.java
rm src/org/codefx/demo/java9/internal/multi_release/VersionDependent.java

echo "package"
$JAVA9_HOME/bin/jar --create --file out-mr/mr.jar -C out-mr/java-8 . --release 9 -C out-mr/java-9 .

echo "run with Java 8:"
java -cp out-mr/mr.jar org.codefx.demo.java9.internal.multi_release.Main

echo "run with Java 9:"
$JAVA9_HOME/bin/java -cp out-mr/mr.jar org.codefx.demo.java9.internal.multi_release.Main
