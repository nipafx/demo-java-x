#!/bin/bash
set -e
source executables.sh

echo -e "\n# COMPILING\n"

rm -rf target/*
# --add-exports java.base/jdk.internal.vm.annotation=ALL-UNNAMED
# 	is needed to get access to `@ReservedStackAccess` in `ReservedStackAccess`
$javac \
	--add-exports java.base/jdk.internal.vm.annotation=ALL-UNNAMED \
	-d target/classes `find src/main/java -name "*.java"`
# copy resources needed for `ResourceFileEncoding`
cp resources/** target/classes
