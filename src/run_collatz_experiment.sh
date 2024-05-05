#!/bin/bash
#SBATCH --output=collatz_%j.out
#SBATCH --error=collatz_%j.err

run_experiment() {
  local start=$1
  local end=$2
  local debug_mode=$3
  local threads=$4

  echo "Running experiment from $start to $end with $threads thread(s) in $debug_mode mode."
  java Main $start $end $debug_mode $threads
}

DEBUG_MODE=debug
START=1

# Nuo 1 to 10 mln
END1=10000000
for THREADS in 1 2 4 8 16 32; do
  sbatch -c $THREADS run_collatz_experiment.sh
  run_experiment $START $END1 $DEBUG_MODE $THREADS
done

# Nuo 1 to 1 bln
END2=1000000000
for THREADS in 1 2 4 8 16 32; do
  sbatch -c $THREADS run_collatz_experiment.sh
  run_experiment $START $END2 $DEBUG_MODE $THREADS
done
