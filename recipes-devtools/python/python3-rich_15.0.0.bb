SUMMARY = "Rich text and formatting in the terminal — rich-click's rendering backend"
HOMEPAGE = "https://github.com/Textualize/rich"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b5f0b94fbc94f5ad9ae4efcf8a778303"

PYPI_PACKAGE = "rich"
SRC_URI[sha256sum] = "edd07a4824c6b40189fb7ac9bc4c52536e9780fbbfbddf6f1e2502c31b068c36"

inherit pypi python_poetry_core

RDEPENDS:${PN} += "python3-markdown-it-py python3-pygments"
