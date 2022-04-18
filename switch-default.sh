#!/bin/bash
# no `set -e` to see expected errors

echo -e "\n# COMPILING WITH OLD BOOL\n"

rm -rf target/*
cp \
	src/switch_default/java/dev/nipafx/demo/java14/lang/switch_/OldBool.java \
	src/switch_default/java/dev/nipafx/demo/java14/lang/switch_/Bool.java
javac \
	-d target/classes \
	src/switch_default/java/dev/nipafx/demo/java14/lang/switch_/Switch.java \
	src/switch_default/java/dev/nipafx/demo/java14/lang/switch_/Bool.java
rm src/switch_default/java/dev/nipafx/demo/java14/lang/switch_/Bool.java

echo -e "\n# RUNNING WITH OLD BOOL\n"

echo -e "# Argument: \"TRUE\""
java \
	-cp target/classes \
	--enable-preview \
	dev.nipafx.demo.java14.lang.switch_.Switch \
	TRUE
echo -e "# Argument: \"FILE_NOT_FOUND\""
java \
	-cp target/classes \
	--enable-preview \
	dev.nipafx.demo.java14.lang.switch_.Switch \
	FILE_NOT_FOUND

echo -e "\n# REPLACING OLD WITH NEW BOOL\n"

cp \
	src/switch_default/java/dev/nipafx/demo/java14/lang/switch_/NewBool.java \
	src/switch_default/java/dev/nipafx/demo/java14/lang/switch_/Bool.java
javac \
	-d target/classes \
	src/switch_default/java/dev/nipafx/demo/java14/lang/switch_/Bool.java
rm src/switch_default/java/dev/nipafx/demo/java14/lang/switch_/Bool.java

echo -e "# RUNNING WITH NEW BOOL\n"

echo -e "# Argument: \"TRUE\""
java \
	-cp target/classes \
	--enable-preview \
	dev.nipafx.demo.java14.lang.switch_.Switch \
	TRUE
echo -e "# Argument: \"FILE_NOT_FOUND\""
java \
	-cp target/classes \
	--enable-preview \
	dev.nipafx.demo.java14.lang.switch_.Switch \
	FILE_NOT_FOUND
