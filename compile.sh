#!/bin/bash
set -e
source executables.sh

echo -e "\n# COMPILING\n"

rm -rf target/*
# --add-exports java.base/jdk.internal.vm.annotation=ALL-UNNAMED
# 	is needed to get access to `@ReservedStackAccess` in `ReservedStackAccess`
# --enable-preview is needed for preview language features
# 	like switch expressions in Java 13
# --source (or --release) is needed if --enable-preview is used
$javac \
	--add-exports java.base/jdk.internal.vm.annotation=ALL-UNNAMED \
	--enable-preview \
	--source 13 \
	-d target/classes `find src/main/java -name "*.java"`
# copy resources needed for `ResourceFileEncoding`
cp resources/** target/classes
