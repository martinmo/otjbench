#!/bin/sh
exec java -Dot.weavable="$PWD/whitelist.txt" -Xbootclasspath/a:libs/runtime/otre_min.jar -javaagent:libs/runtime/otredyn_agent.jar -jar target/benchmarks.jar "$@"
