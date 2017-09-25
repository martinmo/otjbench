#!/bin/sh
exec java -Xbootclasspath/a:otre_min.jar -javaagent:otredyn_agent.jar -jar target/benchmarks.jar "$@"
