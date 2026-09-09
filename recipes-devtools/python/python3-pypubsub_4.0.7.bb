SUMMARY = "Python publish-subscribe library — meshtastic's internal event bus"
HOMEPAGE = "https://github.com/schollii/pypubsub"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2fedfd31700f60e5c8d6499d70311882"

PYPI_PACKAGE = "pypubsub"
SRC_URI[sha256sum] = "ec8b5cb147624958320e992602380cc5d0e4b36b1c59844d05e425a3003c09dc"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-setuptools-scm-native"
do_configure:prepend() {
    sed -i -e 's/setuptools<77,>=68/setuptools>=68/' \
           -e 's/setuptools>=68,<77/setuptools>=68/' ${S}/pyproject.toml
}

export SETUPTOOLS_SCM_PRETEND_VERSION = "${PV}"
