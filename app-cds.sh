#!/bin/bash
set -e
source executables.sh

rm -rf app-cds
mkdir app-cds

if ! [ -e target/java-x.jar ]
then
	printf "Building Maven project, so JAR is available...\n"
    mvn clean install
fi

printf "\n--- RUN WITHOUT CDS ---\n\n"
time $java -jar target/java-x.jar

printf "\n--- RUN WITH CDS ---\n"
printf "\n > create archive for default JDK classes\n"
sudo $java -Xshare:dump
printf "\n > use default archive\n"
time $java -Xshare:on -jar target/java-x.jar
printf "\n > non-archived classes\n"
$java -Xshare:on \
	-Xlog:class+load \
	-jar target/java-x.jar \
| grep -v "shared objects file"

printf "\n--- RUN WITH APPLICATION CDS ---\n"
printf "\n > record used classes\n"
$java \
	-XX:DumpLoadedClassList=app-cds/classes.lst \
	-jar target/java-x.jar

printf "\n > create archive for recorded classes\n"
$java \
	-Xshare:dump \
	-XX:+UnlockDiagnosticVMOptions \
	-XX:SharedClassListFile=app-cds/classes.lst \
	-XX:SharedArchiveFile=app-cds/app.jsa \
	--class-path target/java-x.jar

printf "\n > use created archive\n"
time $java \
	-Xshare:on \
	-XX:+UnlockDiagnosticVMOptions \
	-XX:SharedArchiveFile=app-cds/app.jsa \
	-jar target/java-x.jar \
