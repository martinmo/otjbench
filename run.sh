#!/bin/sh
exec java -cp "bin:lib/*" -Xbootclasspath/a:otre_min.jar -javaagent:otredyn_agent.jar "$@"
