#!/bin/bash

START=$1
END=$2
MODE=$3
THREADS=$4

javac -d build src/*.java
java -Xss5m -Xmx1g -cp build Main $START $END $MODE $THREADS
