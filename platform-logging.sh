#!/bin/bash
set -e

if ! [ -e target/app ]
then
	printf "# Building Maven project, so app JARs are available...\n"
    mvn clean package
fi

echo "# Creating clean directories"
rm -rf target/logging/classes
mkdir -p target/logging/classes
rm -rf target/logging/mods
mkdir -p target/logging/mods

echo "# Compiling and packaging logger"
mkdir target/logging/classes/dev.nipafx.demo.java9.logging
javac \
	-d target/logging/classes/dev.nipafx.demo.java9.logging \
	src/platform_logging/java/dev/nipafx/demo/java9/api/platform_logging/logger/*.java
jar \
	-c \
	--file target/logging/mods/dev.nipafx.demo.java9.logging.jar \
	-C target/logging/classes/dev.nipafx.demo.java9.logging/ .

echo "# Compiling and packaging app"
mkdir target/logging/classes/dev.nipafx.demo.java9.app
javac \
	-p target/app \
	-d target/logging/classes/dev.nipafx.demo.java9.app \
	src/platform_logging/java/dev/nipafx/demo/java9/api/platform_logging/app/*.java
jar \
	-c \
	--file target/logging/mods/dev.nipafx.demo.java9.app.jar \
	--main-class dev.nipafx.demo.java9.api.platform_logging.app.LoggingApplication \
	-C target/logging/classes/dev.nipafx.demo.java9.app/ .

echo "# Running App"
java -verbose:gc -p target/app:target/logging/mods -m dev.nipafx.demo.javaX.app
