# Android app (Kotlin DSL)

This project was migrated from an experimental Declarative Gradle DSL setup to standard, supported Gradle Kotlin DSL files to ensure compatibility in CI/preview environments.

Key changes:
- Replaced .dcl files with standard `build.gradle.kts` and `settings.gradle.kts`.
- Removed unsupported Declarative DSL `configurations { ... }` usage that caused unresolved function errors.
- Kept existing AndroidX, Navigation, Coroutines, Retrofit/Moshi/OkHttp dependencies.
- Enabled JUnit Platform for unit tests in Gradle.

How to build (any environment):
- From repo root:
  - ./gradlew build
  - ./gradle.sh build
  - ./weather-companion-215470-215480/gradlew build
  - ./weather-companion-215470-215480/gradlew-proxy.sh build
- From module directory:
  - cd weather-companion-215470-215480/mobile_frontend && ./build.sh build

Install and run on a connected device/emulator:
- ./gradlew :app:installDebug

Backend URL:
- Defaults to http://10.0.2.2:3001/ (emulator loopback)
- Override via system property when running Gradle:
  - ./gradlew -DAPP_BASE_URL="http://10.0.2.2:3001/" build
