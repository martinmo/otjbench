#!/bin/sh
exec java -cp "build/bin:libs/compile/*" -Xbootclasspath/a:libs/runtime/otre_min.jar -javaagent:libs/runtime/otredyn_agent.jar "$@"
