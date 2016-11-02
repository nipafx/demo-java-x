#!/bin/bash

rm -rf out/*
# --add-exports java.base/jdk.internal.vm.annotation=ALL-UNNAMED
# 	is needed to get access to `@ReservedStackAccess` in `ReservedStackAccess`
javac9 \
	--add-exports java.base/jdk.internal.vm.annotation=ALL-UNNAMED \
	-d out src/org/codefx/demo/java9/**/**/*.java
# copy resources needed for `ResourceFileEncoding`
cp resources/** out
