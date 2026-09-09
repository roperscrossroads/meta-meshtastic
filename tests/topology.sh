_ROOT="${LAYER_DIR:-${LAYER:-}}"
[ -n "$_ROOT" ] || _ROOT=$(CDPATH='' cd -- "$(dirname -- "$0")/.." && pwd)

PYDIR="$_ROOT/recipes-devtools/python"

LINT_ROOTS="$_ROOT"

LINT_EXPECT_UNITS=0
