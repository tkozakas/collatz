#!/bin/bash

START=$1
END=$2
MODE=$3
THREADS=$4

javac -d build src/Main.java src/CollatzTask.java src/CollatzCalculator.java
java -Xss5m -Xmx1g build/Main $START $END $MODE $THREADS
