#!/bin/bash

# EXECUTE FROM PROJECT ROOT!

echo " > creating clean directories"
rm -r out/classes
mkdir -p out/classes
rm -r out/mods
mkdir -p out/mods

echo " > compiling and packaging logger"
mkdir out/classes/org.codefx.demo.java9.logging
javac9 \
	-d out/classes/org.codefx.demo.java9.logging \
	src/org/codefx/demo/java9/api/platform_logging/logger/*.java
jar9 \
	-c \
	--file out/mods/org.codefx.demo.java9.logging.jar \
	-C out/classes/org.codefx.demo.java9.logging/ .

echo " > compiling and packaging app"
mkdir out/classes/org.codefx.demo.java9.app
javac9 \
	-d out/classes/org.codefx.demo.java9.app\
	src/org/codefx/demo/java9/api/platform_logging/app/*.java
jar9 \
	-c \
	--file out/mods/org.codefx.demo.java9.app.jar \
	--main-class org.codefx.demo.java9.api.platform_logging.app.LoggingApplication \
	-C out/classes/org.codefx.demo.java9.app/ .

echo " > running App"
java9 -verbose:gc -p out/mods -m org.codefx.demo.java9.app

echo " > deleting directories"
rm -r out/classes
rm -r out/mods

