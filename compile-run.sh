#!/bin/bash

./compile.sh
# -XX:-RestrictReservedStack
#	is needed to unlock stack frame reservation in `ReservingStackAreas`
$JAVA9_HOME/bin/java \
	-cp out \
	-XX:-RestrictReservedStack \
	$1
