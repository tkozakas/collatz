#!/bin/bash

START=$1
END=$2
MODE=$3
THREADS=$4

javac Main.java CollatzTask.java CollatzCalculator.java
java -Xss2m -Xmx1g Main $START $END $MODE $THREADS

