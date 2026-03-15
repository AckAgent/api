.PHONY: build test generate generate-go generate-ts generate-kotlin generate-swift strip-internal clean lint bundle-protocol release

OPENAPI_GENERATOR = pnpm exec openapi-generator-cli
KOTLIN_OUT = kotlin/src/main/kotlin
KOTLIN_PKG_BASE = com.ackagent.api.generated

# Specs to generate Kotlin clients for (service:specFile)
KOTLIN_SERVICE_SPECS = auth:auth-openapi.yaml relay:relay-openapi.yaml blob:blob-openapi.yaml org:org-openapi.yaml

# ── VERSION resolution ─────────────────────────────────────
# Supports: make release VERSION=1.2.3 | VERSION=patch | VERSION=minor | VERSION=major
ifdef VERSION
  ifneq ($(filter v%,$(VERSION)),)
    $(error VERSION must not start with 'v' — the prefix is added automatically. Usage: make release VERSION=1.2.3)
  endif
  ifneq ($(filter patch minor major,$(VERSION)),)
    _LATEST_TAG := $(shell git describe --tags --abbrev=0 --match 'v*' 2>/dev/null || echo v0.0.0)
    _LATEST_VER := $(patsubst v%,%,$(_LATEST_TAG))
    _VER_PARTS  := $(subst ., ,$(_LATEST_VER))
    _CUR_MAJOR  := $(or $(word 1,$(_VER_PARTS)),0)
    _CUR_MINOR  := $(or $(word 2,$(_VER_PARTS)),0)
    _CUR_PATCH  := $(or $(word 3,$(_VER_PARTS)),0)
    ifeq ($(VERSION),patch)
      override VERSION := $(_CUR_MAJOR).$(_CUR_MINOR).$(shell echo $$(($(_CUR_PATCH) + 1)))
    else ifeq ($(VERSION),minor)
      override VERSION := $(_CUR_MAJOR).$(shell echo $$(($(_CUR_MINOR) + 1))).0
    else ifeq ($(VERSION),major)
      override VERSION := $(shell echo $$(($(_CUR_MAJOR) + 1))).0.0
    endif
  endif
  ifeq ($(shell echo '$(VERSION)' | grep -cE '^[0-9]+\.[0-9]+\.[0-9]+$$'),0)
    $(error Invalid VERSION '$(VERSION)'. Must be semver X.Y.Z (e.g. 1.2.3) or bump keyword (patch|minor|major))
  endif
endif
# ────────────────────────────────────────────────────────────

# Build all generated packages
build:
	go build ./...
	cd ts && pnpm install && pnpm build
	cd kotlin && ./gradlew build

# Verify generated code compiles
test:
	go build ./...
	go vet ./...
	cd ts && pnpm install && pnpm build
	cd kotlin && ./gradlew build

# Bundle the e2ee-protocol spec first (other specs reference it)
bundle-protocol:
	pnpm exec redocly bundle openapi/e2ee-protocol-openapi.yaml -o openapi/bundled/e2ee-protocol-openapi.json

# Strip x-internal endpoints from public specs
strip-internal:
	@echo "Stripping internal endpoints..."
	bash tools/strip-internal/strip-internal.sh openapi/relay-openapi.yaml
	bash tools/strip-internal/strip-internal.sh openapi/auth-openapi.yaml
	bash tools/strip-internal/strip-internal.sh openapi/org-openapi.yaml

# Generate Go types from OpenAPI specs
generate-go:
	cd go/protocol && go generate ./...
	cd go/relay && go generate ./...
	cd go/auth && go generate ./...
	cd go/blob && go generate ./...
	cd go/keyauth && go generate ./...
	cd go/auditchain && go generate ./...
	cd go/credential-issuer && go generate ./...

# Generate TypeScript types from OpenAPI specs
generate-ts:
	pnpm exec openapi-typescript openapi/relay-openapi.yaml -o ts/relay-api.d.ts
	pnpm exec openapi-typescript openapi/auth-openapi.yaml -o ts/auth-api.d.ts
	pnpm exec openapi-typescript openapi/blob-openapi.yaml -o ts/blob-api.d.ts
	pnpm exec openapi-typescript openapi/bundled/e2ee-protocol-openapi.json -o ts/protocol-api.d.ts
	pnpm exec openapi-typescript openapi/keyauth-openapi.yaml -o ts/keyauth-api.d.ts
	pnpm exec openapi-typescript openapi/org-openapi.yaml -o ts/org-api.d.ts
	pnpm exec openapi-typescript openapi/auditchain-openapi.yaml -o ts/auditchain-api.d.ts
	pnpm exec openapi-typescript openapi/credential-issuer-openapi.yaml -o ts/credential-issuer-api.d.ts
	cd ts && pnpm build

# Generate Kotlin models/clients from OpenAPI specs
generate-kotlin:
	@echo "==> Generating Kotlin models from OpenAPI specs..."
	@rm -rf $(KOTLIN_OUT)
	@for entry in $(KOTLIN_SERVICE_SPECS); do \
		svc=$${entry%%:*}; spec=$${entry##*:}; \
		echo "  -> $$svc ($$spec)"; \
		$(OPENAPI_GENERATOR) generate \
			-g kotlin \
			-i openapi/$$spec \
			-o kotlin/build/openapi-tmp/$$svc \
			--package-name $(KOTLIN_PKG_BASE).$$svc \
			--model-package $(KOTLIN_PKG_BASE).$$svc.models \
			--api-package $(KOTLIN_PKG_BASE).$$svc.apis \
			--invoker-package $(KOTLIN_PKG_BASE).$$svc.invoker \
			--global-property models,apis,supportingFiles,modelTests=false,apiTests=false,modelDocs=false,apiDocs=false \
			--additional-properties library=jvm-retrofit2,useCoroutines=true,serializationLibrary=kotlinx_serialization,enumPropertyNaming=UPPERCASE,sourceFolder=src/main/kotlin \
			--type-mappings object=JsonObject,AnyType=JsonElement,ByteArray=kotlin.String \
			--import-mappings JsonObject=kotlinx.serialization.json.JsonObject,JsonElement=kotlinx.serialization.json.JsonElement \
			--ignore-file-override /dev/null \
			2>&1 | tail -1; \
	done
	@echo "  -> protocol (bundled/e2ee-protocol-openapi.json, models only)"
	@$(OPENAPI_GENERATOR) generate \
		-g kotlin \
		-i openapi/bundled/e2ee-protocol-openapi.json \
		-o kotlin/build/openapi-tmp/protocol \
		--package-name $(KOTLIN_PKG_BASE).protocol \
		--model-package $(KOTLIN_PKG_BASE).protocol.models \
		--api-package $(KOTLIN_PKG_BASE).protocol.apis \
		--invoker-package $(KOTLIN_PKG_BASE).protocol.invoker \
		--global-property models,apis=false,supportingFiles=false,modelTests=false,apiTests=false,modelDocs=false,apiDocs=false \
		--additional-properties library=jvm-retrofit2,useCoroutines=true,serializationLibrary=kotlinx_serialization,enumPropertyNaming=UPPERCASE,sourceFolder=src/main/kotlin \
		--type-mappings object=JsonObject,AnyType=JsonElement,ByteArray=kotlin.String \
		--import-mappings JsonObject=kotlinx.serialization.json.JsonObject,JsonElement=kotlinx.serialization.json.JsonElement \
		--ignore-file-override /dev/null \
		2>&1 | tail -1
	@echo "==> Copying generated sources..."
	@mkdir -p $(KOTLIN_OUT)
	@for entry in $(KOTLIN_SERVICE_SPECS); do \
		svc=$${entry%%:*}; \
		cp -r kotlin/build/openapi-tmp/$$svc/src/main/kotlin/* $(KOTLIN_OUT)/; \
	done
	@cp -r kotlin/build/openapi-tmp/protocol/src/main/kotlin/* $(KOTLIN_OUT)/
	@rm -rf kotlin/build/openapi-tmp
	@echo "==> Kotlin generation complete"

# Bundle specs and generate Swift client code (committed to git)
generate-swift:
	@echo "==> Bundling OpenAPI specs for Swift..."
	pnpm exec redocly bundle openapi/auth-openapi.yaml -o swift/bundled/auth-openapi.yaml
	pnpm exec redocly bundle openapi/relay-openapi.yaml -o swift/bundled/relay-openapi.yaml
	pnpm exec redocly bundle openapi/blob-openapi.yaml -o swift/bundled/blob-openapi.yaml
	pnpm exec redocly bundle openapi/org-openapi.yaml -o swift/bundled/org-openapi.yaml
	pnpm exec redocly bundle openapi/e2ee-protocol-openapi.yaml -o swift/bundled/e2ee-protocol-openapi.json
	@echo "==> Generating Swift client code..."
	swift-openapi-generator generate \
		--config swift/configs/login-client.yaml \
		--output-directory swift/Sources/LoginClient \
		swift/bundled/auth-openapi.yaml
	swift-openapi-generator generate \
		--config swift/configs/relay-client.yaml \
		--output-directory swift/Sources/RelayClient \
		swift/bundled/relay-openapi.yaml
	swift-openapi-generator generate \
		--config swift/configs/blob-client.yaml \
		--output-directory swift/Sources/BlobClient \
		swift/bundled/blob-openapi.yaml
	swift-openapi-generator generate \
		--config swift/configs/org-client.yaml \
		--output-directory swift/Sources/OrgClient \
		swift/bundled/org-openapi.yaml
	swift-openapi-generator generate \
		--config swift/configs/protocol-types.yaml \
		--output-directory swift/Sources/ProtocolTypes \
		swift/bundled/e2ee-protocol-openapi.json
	@echo "==> Swift generation complete"

# Run all generation steps
generate: bundle-protocol strip-internal generate-go generate-ts generate-kotlin generate-swift

# Lint OpenAPI specs
lint:
	pnpm exec redocly lint openapi/e2ee-protocol-openapi.yaml
	pnpm exec redocly lint openapi/relay-openapi.yaml
	pnpm exec redocly lint openapi/auth-openapi.yaml
	pnpm exec redocly lint openapi/blob-openapi.yaml
	pnpm exec redocly lint openapi/keyauth-openapi.yaml
	pnpm exec redocly lint openapi/auditchain-openapi.yaml
	pnpm exec redocly lint openapi/credential-issuer-openapi.yaml

# Release: bump npm version, commit, tag, push
# Usage: make release VERSION=0.2.0
release:
ifndef VERSION
	$(error VERSION is required. Usage: make release VERSION=1.2.3 (or patch|minor|major))
endif
	@echo "Releasing v$(VERSION)$(if $(_LATEST_VER), (was v$(_LATEST_VER)),)"
	@node -e "const p=require('./ts/package.json'); p.version='$(VERSION)'; require('fs').writeFileSync('./ts/package.json', JSON.stringify(p,null,2)+'\n')"
	git add ts/package.json
	git commit -m "chore: release v$(VERSION)"
	git tag v$(VERSION)
	git push origin main v$(VERSION)

# Clean generated files
clean:
	rm -f go/*/gen.go
	rm -f ts/*.d.ts ts/*.js
	rm -rf ts/dist
	rm -rf swift/bundled/*
	rm -rf openapi/bundled/*
	rm -rf kotlin/src/main/kotlin/*
	rm -rf kotlin/build/
