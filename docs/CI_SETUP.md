# CI Setup

## Required Secrets

None. npm publishing uses GitHub OIDC trusted publishers (no static token needed).

## npm OIDC Publishing Setup

To enable publishing without an `NPM_TOKEN`:

1. Go to npmjs.com → `@ackagent/api` package settings → Publishing access
2. Add a trusted publisher: GitHub Actions
3. Set repository: `AckAgent/api`, workflow: `release.yml`

## Workflows

### ci.yml
Runs on every push/PR to main. Lints OpenAPI specs with redocly and verifies Go/TypeScript generation is up-to-date.

### release.yml
Triggered on tag push (`v*`). Publishes the npm package via OIDC and verifies the Go module builds.

## Releasing

```bash
make release VERSION=0.2.0
```

This updates `ts/package.json`, commits, tags, and pushes. CI handles npm publish and Go module indexing.
