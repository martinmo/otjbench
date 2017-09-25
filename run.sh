#!/bin/sh
exec java -cp "build/bin:lib/*" -Xbootclasspath/a:otre_min.jar -javaagent:otredyn_agent.jar "$@"
