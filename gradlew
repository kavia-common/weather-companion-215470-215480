#!/usr/bin/env bash
# Root-level Gradle wrapper shim.
# Delegates to the Android mobile_frontend module's Gradle wrapper so that
# CI or tools calling "./gradlew ..." at the repository root work seamlessly.

set -euo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ANDROID_DIR="$REPO_ROOT/mobile_frontend"

WRAPPER="$ANDROID_DIR/gradlew"
if [ ! -f "$WRAPPER" ]; then
  echo "Error: Gradle wrapper not found at $WRAPPER" >&2
  exit 127
fi

cd "$ANDROID_DIR"
chmod +x ./gradlew
exec ./gradlew "$@"
