#!/usr/bin/env bash
# Proxy to invoke the Android module's Gradle wrapper from any working directory.
# Usage:
#   ./gradlew-proxy.sh [gradle args...]
# Example:
#   ./gradlew-proxy.sh build
#   ./gradlew-proxy.sh :app:assembleDebug

set -euo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ANDROID_DIR="$REPO_ROOT/mobile_frontend"

if [ ! -f "$ANDROID_DIR/gradlew" ]; then
  echo "Error: Gradle wrapper not found at $ANDROID_DIR/gradlew" >&2
  exit 127
fi

cd "$ANDROID_DIR"
chmod +x ./gradlew
./gradlew "$@"
