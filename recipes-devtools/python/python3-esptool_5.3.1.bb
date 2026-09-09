SUMMARY = "Espressif chip flashing/provisioning tool — flashes the Heltec V3 (ESP32-S3) Meshtastic radio"
HOMEPAGE = "https://github.com/espressif/esptool/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PYPI_PACKAGE = "esptool"
SRC_URI[sha256sum] = "125781f36e6a2d08c484524a45f340694675368b5eeead9d0cb21b2034a91d98"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "\
    python3-bitstring \
    python3-cryptography \
    python3-pyserial \
    python3-reedsolo \
    python3-pyyaml \
    python3-intelhex \
    python3-rich-click \
    python3-click \
"
