#!/bin/bash
set -e

if [ -e target/site/apidocs ]
then
	printf "# Javadoc was already generated.\n"
else
	printf "# Building Maven project, so Javadoc is generated...\n"
    mvn clean package
fi

echo "# Launching \`jwebserver\` in \`target/site/apidocs\`"
cd target/site/apidocs
jwebserver
