#!/bin/bash
source executables.sh

echo -e "\n# COMPILING WITH OLD BOOL\n"

rm -rf target/*
# --enable-preview is needed for preview language features
# 	like switch expressions in Java 12
# --source (or --release) is needed if --enable-preview is used
cp \
	src/switch_default/java/org/codefx/demo/java12/lang/switch_/OldBool.java \
	src/switch_default/java/org/codefx/demo/java12/lang/switch_/Bool.java
$javac \
	--enable-preview \
	--source 12 \
	-d target/classes \
	src/switch_default/java/org/codefx/demo/java12/lang/switch_/Switch.java \
	src/switch_default/java/org/codefx/demo/java12/lang/switch_/Bool.java
rm src/switch_default/java/org/codefx/demo/java12/lang/switch_/Bool.java

echo -e "\n# RUNNING WITH OLD BOOL\n"

echo -e "# Argument: \"TRUE\""
$java \
	-cp target/classes \
	--enable-preview \
	org.codefx.demo.java12.lang.switch_.Switch \
	TRUE
echo -e "# Argument: \"FILE_NOT_FOUND\""
$java \
	-cp target/classes \
	--enable-preview \
	org.codefx.demo.java12.lang.switch_.Switch \
	FILE_NOT_FOUND

echo -e "\n# REPLACING OLD WITH NEW BOOL\n"

cp \
	src/switch_default/java/org/codefx/demo/java12/lang/switch_/NewBool.java \
	src/switch_default/java/org/codefx/demo/java12/lang/switch_/Bool.java
$javac \
	-d target/classes \
	src/switch_default/java/org/codefx/demo/java12/lang/switch_/Bool.java
rm src/switch_default/java/org/codefx/demo/java12/lang/switch_/Bool.java

echo -e "# RUNNING WITH NEW BOOL\n"

echo -e "# Argument: \"TRUE\""
$java \
	-cp target/classes \
	--enable-preview \
	org.codefx.demo.java12.lang.switch_.Switch \
	TRUE
echo -e "# Argument: \"FILE_NOT_FOUND\""
$java \
	-cp target/classes \
	--enable-preview \
	org.codefx.demo.java12.lang.switch_.Switch \
	FILE_NOT_FOUND
