#!/bin/sh
exec java -Dot.weavable="$PWD/whitelist.txt" -Xbootclasspath/a:otre_min.jar -javaagent:otredyn_agent.jar -jar target/benchmarks.jar "$@"
