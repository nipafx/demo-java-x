#!/bin/bash

./compile.sh
$JAVA9_HOME/bin/java -cp out $1
