#!/bin/bash
set -e
source executables.sh

echo -e "\n# LAUNCHING $1\n"

# --add-exports-private java.base/java.lang=ALL-UNNAMED
# 	is needed for reflection over String internals in `Compaction`
# -XX:-RestrictReservedStack
#	is needed to unlock stack frame reservation in `ReservingStackAreas`
$java \
	-cp target/classes \
	--add-opens java.base/java.lang=ALL-UNNAMED \
	-XX:-RestrictReservedStack \
	$1
