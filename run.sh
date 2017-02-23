#!/bin/bash

# --add-exports-private java.base/java.lang=ALL-UNNAMED
# 	is needed for reflection over String internals in `Compaction`
# -XX:-RestrictReservedStack
#	is needed to unlock stack frame reservation in `ReservingStackAreas`
java9 \
	-cp out \
	--add-opens java.base/java.lang=ALL-UNNAMED \
	-XX:-RestrictReservedStack \
	$1
