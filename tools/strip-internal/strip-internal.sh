#!/usr/bin/env bash
#
# strip-internal.sh
#
# Removes all OpenAPI paths where any operation has x-internal: true.
#
# Usage:
#   ./strip-internal.sh <input.yaml> [output.yaml]
#
# If output is omitted, the file is modified in-place.

set -euo pipefail

if [[ $# -lt 1 ]]; then
  echo "Usage: $0 <input.yaml> [output.yaml]" >&2
  exit 1
fi

INPUT="$1"
OUTPUT="${2:-$1}"

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/../.." && pwd)"

node -e "
const fs = require('fs');
const yaml = require('$REPO_ROOT/node_modules/js-yaml');

const input = '$INPUT';
const output = '$OUTPUT';

const spec = yaml.load(fs.readFileSync(input, 'utf8'));

if (!spec.paths) {
  console.log('  No paths found in ' + input + ', skipping');
  process.exit(0);
}

const pathsToRemove = [];
for (const [path, methods] of Object.entries(spec.paths)) {
  if (typeof methods !== 'object' || methods === null) continue;
  for (const [method, operation] of Object.entries(methods)) {
    if (typeof operation === 'object' && operation !== null && operation['x-internal'] === true) {
      pathsToRemove.push(path);
      break;
    }
  }
}

for (const path of pathsToRemove) {
  delete spec.paths[path];
  console.log('  Removed internal path: ' + path);
}

if (pathsToRemove.length === 0) {
  console.log('  No internal paths found in ' + input);
}

fs.writeFileSync(output, yaml.dump(spec, { lineWidth: 120, noRefs: true, sortKeys: false }));
console.log('  Stripped ' + pathsToRemove.length + ' internal path(s) from ' + input);
"
