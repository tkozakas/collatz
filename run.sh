#!/bin/bash

START=$1
END=$2
MODE=$3
THREADS=$4

javac -d out/production/collatz src/*.java
java -Xss5m -Xmx1g -cp out/production/collatz Main $START $END $MODE $THREADS
