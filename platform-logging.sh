#!/bin/bash
set -e

echo "# Creating clean directories"
rm -rf target/logging/classes
mkdir -p target/logging/classes
rm -rf target/logging/mods
mkdir -p target/logging/mods

echo "# Compiling and packaging logger"
mkdir target/logging/classes/org.codefx.demo.java9.logging
javac \
	-d target/logging/classes/org.codefx.demo.java9.logging \
	src/platform_logging/java/org/codefx/demo/java9/api/platform_logging/logger/*.java
jar \
	-c \
	--file target/logging/mods/org.codefx.demo.java9.logging.jar \
	-C target/logging/classes/org.codefx.demo.java9.logging/ .

echo "# Compiling and packaging app"
mkdir target/logging/classes/org.codefx.demo.java9.app
javac \
	-d target/logging/classes/org.codefx.demo.java9.app\
	src/platform_logging/java/org/codefx/demo/java9/api/platform_logging/app/*.java
jar \
	-c \
	--file target/logging/mods/org.codefx.demo.java9.app.jar \
	--main-class org.codefx.demo.java9.api.platform_logging.app.LoggingApplication \
	-C target/logging/classes/org.codefx.demo.java9.app/ .

echo "# Running App"
java -verbose:gc -p target/logging/mods -m org.codefx.demo.javaX.app
