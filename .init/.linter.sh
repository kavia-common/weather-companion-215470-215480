#!/bin/bash
cd /home/kavia/workspace/code-generation/weather-companion-215470-215480/mobile_frontend
./gradlew lint
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

