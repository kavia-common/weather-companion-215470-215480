#!/usr/bin/env bash
# Simple wrapper to ensure Gradle runs from this module directory.
# Usage:
#   ./build.sh [gradle arguments...]
# Examples:
#   ./build.sh build
#   ./build.sh :app:installDebug
#   ./build.sh -DAPP_BASE_URL="http://10.0.2.2:3001/" build

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

# Ensure wrapper is executable
chmod +x ./gradlew

# Forward all arguments to Gradle
./gradlew "$@"
