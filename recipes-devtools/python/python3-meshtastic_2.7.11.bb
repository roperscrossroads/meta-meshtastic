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
