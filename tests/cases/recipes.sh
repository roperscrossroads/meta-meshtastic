#!/bin/sh
# shellcheck disable=SC2016
. "$(dirname "$0")/../lib.sh"

need_dir PYDIR
describe "all ten surveyed recipes ship, each with a pinned hash and a license checksum"
for r in \
    python3-pypubsub_4.0.7 python3-dotmap_1.3.30 python3-print-color_0.4.7 \
    python3-pyqrcode_1.2.1 python3-esptool_5.3.1 python3-wcwidth_0.8.2 \
    python3-reedsolo_1.7.0 python3-rich_15.0.0 python3-rich-click_1.9.8 \
    python3-meshtastic_2.7.11
do
    f="$PYDIR/$r.bb"
    assert_file_exists "$f" "$r ships"
    text=$(cat "$f" 2>/dev/null)
    assert_contains "$text" 'SRC_URI[sha256sum]' "$r pins its sdist hash"
    assert_contains "$text" 'LIC_FILES_CHKSUM' "$r pins its license"
done

describe "the build backends stay as the sdists declared them — six were wrong when guessed"
assert_contains "$(cat "$PYDIR/python3-meshtastic_2.7.11.bb")" 'inherit pypi python_poetry_core' "meshtastic is poetry-core"
assert_contains "$(cat "$PYDIR/python3-rich_15.0.0.bb")" 'inherit pypi python_poetry_core' "rich is poetry-core"
assert_contains "$(cat "$PYDIR/python3-esptool_5.3.1.bb")" 'inherit pypi python_setuptools_build_meta' "esptool is setuptools.build_meta"
assert_contains "$(cat "$PYDIR/python3-rich-click_1.9.8.bb")" 'inherit pypi python_setuptools_build_meta' "rich-click too — NOT classic setuptools3"
assert_contains "$(cat "$PYDIR/python3-wcwidth_0.8.2.bb")" 'inherit pypi python_hatchling' "wcwidth is hatchling"

describe "bleak stays a dependency even on a device with Bluetooth disabled"
assert_contains "$(cat "$PYDIR/python3-meshtastic_2.7.11.bb")" 'python3-bleak' "the CLI cannot start without it"

describe "esptool keeps its cryptography dependency — the >=43 floor is why wrynose was checked"
assert_contains "$(cat "$PYDIR/python3-esptool_5.3.1.bb")" 'python3-cryptography' "declared, not assumed"
describe "the sdist names stay as PyPI actually serves them — hash checks cannot see URL construction"
assert_contains "$(cat "$PYDIR/python3-pypubsub_4.0.7.bb")"    'PYPI_PACKAGE = "pypubsub"'    "pypubsub: post-PEP-625, normalized"
assert_contains "$(cat "$PYDIR/python3-pypubsub_4.0.7.bb")"    'python3-setuptools-scm-native' "pypubsub: the build-requires the backend extraction missed"
assert_contains "$(cat "$PYDIR/python3-print-color_0.4.7.bb")" 'PYPI_PACKAGE = "print_color"' "print-color: underscore"
assert_contains "$(cat "$PYDIR/python3-rich-click_1.9.8.bb")"  'PYPI_PACKAGE = "rich_click"'  "rich-click: underscore"
assert_contains "$(cat "$PYDIR/python3-pyqrcode_1.2.1.bb")"    'PYPI_PACKAGE = "PyQRCode"'    "pyqrcode: HISTORIC capitals — 2016 sdist, pre-normalization"

describe "the provenance survey lives with the recipes — their comments cite it by section"
assert_file_exists "$PYDIR/SURVEY-01.md" "SURVEY-01.md is in-tree, not a dangling reference to an untracked folder"
