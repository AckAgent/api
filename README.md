# AckAgent API

OpenAPI specs and generated typed packages for the AckAgent platform. Single source of truth for all cross-platform API types and test vectors.

## Packages

| Platform | Package | Install |
|----------|---------|---------|
| Go | `github.com/ackagent/api` | `go get github.com/ackagent/api` |
| TypeScript | `@ackagent/api` | `npm install @ackagent/api` |
| Kotlin | `com.ackagent:api-kotlin` | GitHub Packages Maven |
| Swift | Bundled specs | Xcode build-time generation |

## Build

```sh
pnpm install
make generate       # Regenerate all packages from specs
make lint           # Lint OpenAPI specs
cd kotlin && ./gradlew build  # Verify Kotlin
```

## Test Vectors

Cross-platform test vectors in `data/` and `test-vectors/` are the canonical source. Consumer repos download these at test time via `make download-test-vectors`, pinned to a version tag.

## Consumers

- [ackagent/cli](https://github.com/AckAgent/ackagent) — Go module
- [ackagent/web-sdk](https://github.com/AckAgent/web-sdk) — npm
- [ackagent/android](https://github.com/AckAgent/android) — Kotlin/Maven
- [ackagent/platform](https://github.com/AckAgent/platform) — Go module + npm
