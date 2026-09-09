SUMMARY = "Rich-formatted click CLI output — esptool's terminal UI"
HOMEPAGE = "https://github.com/ewels/rich-click"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5372b77c3720be60b7eff9a9a5c0000d"

PYPI_PACKAGE = "rich_click"
SRC_URI[sha256sum] = "4008f921da88b5d91646c134ec881c1500e5a6b3f093e90e8f29400e09608371"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "python3-click python3-rich"
