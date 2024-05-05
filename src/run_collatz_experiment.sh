#!/bin/bash

if [ "$#" -ne 4 ]; then
    echo "Usage: ./run_collatz_experiment.sh <cores> <mode> <start> <end>"
    exit 1
fi

CORES=$1
MODE=$2
START=$3
END=$4

mkdir -p output

for THREADS in 1 2 4 8 16 32; do
  JOB_NAME="c${CORES}_t${THREADS}"
  OUTPUT_FILE="output/collatz_c${CORES}_t${THREADS}.out"

  echo "Running with $CORES core(s) from $START to $END with $THREADS thread(s) in $MODE mode."
  sbatch --exclusive --ntasks=1 --nodes=1 --cpus-per-task $CORES --job-name=$JOB_NAME -o $OUTPUT_FILE run.sh $START $END $MODE $THREADS
  echo "Find output in ${OUTPUT_FILE}"
done
