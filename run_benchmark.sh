#!/bin/sh
JVM_ARGS="-Dot.weavable=whitelist.txt"
JVM_ARGS="$JVM_ARGS -Duser.language=en -Duser.country=US"
JVM_ARGS="$JVM_ARGS -Xbootclasspath/a:libs/runtime/otre_min.jar"
JVM_ARGS="$JVM_ARGS -javaagent:libs/runtime/otredyn_agent.jar"

if java -version 2>&1 | grep -qE '^(openjdk|java) version "9\..*"$'; then
    # for JDK 9
    JVM_ARGS="$JVM_ARGS --add-opens=java.base/java.io=ALL-UNNAMED"
fi

exec java $JVM_ARGS -jar target/benchmarks.jar "$@"
