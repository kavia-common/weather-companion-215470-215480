# weather-companion-215470-215480

Build helpers for mobile_frontend:
- From repo root, you can now simply use the standard wrapper shim:
  - ./weather-companion-215470-215480/gradlew build
  - ./weather-companion-215470-215480/gradlew :app:installDebug
  - ./weather-companion-215470-215480/gradlew -DAPP_BASE_URL="http://10.0.2.2:3001/" build

- Alternatively, use the proxy script:
  - ./weather-companion-215470-215480/gradlew-proxy.sh build
  - ./weather-companion-215470-215480/gradlew-proxy.sh :app:installDebug
  - ./weather-companion-215470-215480/gradlew-proxy.sh -DAPP_BASE_URL="http://10.0.2.2:3001/" build

- Or run from the module directory:
  - cd weather-companion-215470-215480/mobile_frontend && ./build.sh build