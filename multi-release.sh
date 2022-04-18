#!/bin/bash
set -e

rm -rf target/mr
mkdir -p target/mr/java-8-src
mkdir -p target/mr/java-8
mkdir -p target/mr/java-9-src
mkdir -p target/mr/java-9

echo "# Compiling classes for Java 8"
sed 's/VersionDependent8/VersionDependent/g' \
	src/main/java/org/codefx/demo/java9/internal/multi_release/VersionDependent8.java \
	> target/mr/java-8-src/VersionDependent.java
sed 's/VersionDependent8/VersionDependent/g' \
	src/main/java/org/codefx/demo/java9/internal/multi_release/Main.java \
	> target/mr/java-8-src/Main.java
javac --release 8 -d target/mr/java-8 target/mr/java-8-src/*.java

echo "# Compiling classes for Java 9"
sed 's/VersionDependent9/VersionDependent/g' \
	src/main/java/org/codefx/demo/java9/internal/multi_release/VersionDependent9.java \
	> target/mr/java-9-src/VersionDependent.java
javac --release 8 -d target/mr/java-9 target/mr/java-9-src/*.java

echo "# Packaging"
jar --create --file target/mr/mr.jar -C target/mr/java-8 . --release 9 -C target/mr/java-9 .

echo "# Execute the following command with Java 8 and with Java 9+:"
echo "java -cp target/mr/mr.jar org.codefx.demo.java9.internal.multi_release.Main"
