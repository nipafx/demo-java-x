#!/bin/bash

echo "########################"
echo "# BASIC LOGGING OUTPUT #"
echo "########################"
echo ""
java -Xlog -version

echo ""
echo "################"
echo "# LOG WARNINGS #"
echo "################"
echo ""
java -Xlog:all=warning -version

echo ""
echo "###############"
echo "# LOG GC INFO #"
echo "###############"
echo ""
java -Xlog:gc*=info -version

echo ""
echo "####################"
echo "# LOG SOME GC INFO #"
echo "####################"
echo ""
java -Xlog:gc*=info:stdout:time,uptimemillis -version

echo ""
echo "###############"
echo "# ALL OPTIONS #"
echo "###############"
echo ""
java -Xlog:help