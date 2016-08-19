#!/bin/bash

rm -rf out/*
$JAVA9_HOME/bin/javac -d out src/org/codefx/demo/java9/**/**/*.java
