# JMH microbenchmarks for OT/J 

This is a set of microbenchmarks for OT/J using the JMH benchmarking harness.

## Todo list

- [x] Callin overhead
- [ ] Callout overhead
- [ ] Load-time weaving overhead
- ...


## Build and run

Disclaimer: This project uses an ugly hybrid Ant/Maven build, because at the moment, there seems to
be no reliable way to build OT/J projects with Maven. Therefore, some JARs from the official OT/J
distribution (licensed under the Eclipse Public License v1.0) are included. For convenience, it also
ships with an Eclipse project file for easier editing. Note that the benchmark cannot and should
not be executed from inside the IDE.

In order to build the "payload" (OT/J and Ant) and the benchmark (JMH and Maven), just use:

    ./build.sh

which calls Ant and Maven in sequence.

The benchmarks are executed as follows:

    ./run_benchmarks.sh
    ./run_benchmarks_otj.sh

The latter one runs the benchmarks with the OT/J runtime enabled.
