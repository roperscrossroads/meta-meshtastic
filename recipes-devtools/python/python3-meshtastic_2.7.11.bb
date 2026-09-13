SUMMARY = "Meshtastic Python library and CLI — configures the Heltec V3 radio over its serial port"
HOMEPAGE = "https://github.com/meshtastic/python"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=75d892af193fd5a298f724c4377d8f62"

PYPI_PACKAGE = "meshtastic"
SRC_URI[sha256sum] = "41a8c84b577daff173d36db9fdbc8bf2c7228b58e580474e9f53802ed8ea7d4a"

inherit pypi python_poetry_core

RDEPENDS:${PN} += "\
    python3-bleak \
    python3-packaging \
    python3-protobuf \
    python3-pypubsub \
    python3-pyserial \
    python3-pyyaml \
    python3-requests \
    python3-tabulate \
    python3-argcomplete \
    python3-dotmap \
    python3-print-color \
    python3-pyqrcode \
    python3-pypng \
    python3-wcwidth \
"

# ── mesh-analysis AND mesh-tunnel ARE NOT SHIPPED ────────────────────────────
# The package declares a `mesh-analysis` console script and ships the
# meshtastic.analysis package behind it. That is a desktop tool for reviewing
# power-monitor logs: it serves a Dash web app and imports pandas, numpy,
# pyarrow, plotly, dash and dash-bootstrap-components at module level — the
# upstream `analysis` extra, which this layer does not package and which does
# not belong on a small ARM target. Installed without them, `mesh-analysis`
# fails with ModuleNotFoundError the moment anyone runs it. Removing both the
# entry point and the package it points at means the command is simply absent
# rather than present and broken.
#
# `mesh-tunnel` is the same shape: meshtastic.tunnel imports pytap2 at module
# level, which this layer does not package, so the wrapper could only ever fail.
# An IP tunnel over the mesh is also not something this layer means to offer.
# The wrapper, the module, its bytecode and the upstream test that imports it
# all go. `meshtastic --tunnel` imports the module lazily, only when that option
# is given, so the rest of the CLI is unaffected. The option itself still fails
# with an ImportError, now naming meshtastic.tunnel instead of pytap2.
#
# meshtastic.powermon and meshtastic.slog stay: __main__ imports them inside a
# try/except ImportError, so their missing optional dependencies (ppk2_api,
# parse) only disable the power-monitor options instead of breaking the CLI.
do_install:append() {
    rm -f ${D}${bindir}/mesh-analysis
    rm -rf ${D}${PYTHON_SITEPACKAGES_DIR}/meshtastic/analysis

    rm -f ${D}${bindir}/mesh-tunnel
    rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/meshtastic/tunnel.py
    rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/meshtastic/__pycache__/tunnel.*.pyc
    rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/meshtastic/tests/test_tunnel.py
    rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/meshtastic/tests/__pycache__/test_tunnel.*.pyc
}
