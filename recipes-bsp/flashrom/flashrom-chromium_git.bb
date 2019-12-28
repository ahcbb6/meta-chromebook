DESCRIPTION = "(Chromium Fork) flashrom is a utility for identifying, reading, writing, verifying and erasing flash chips"
LICENSE = "GPLv2"
HOMEPAGE = "http://flashrom.org"

LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
DEPENDS = "pciutils libusb libusb-compat"

DEPENDS_append_mediatek8173-chromebook = " dtc"

SRC_URI = "git://chromium.googlesource.com/chromiumos/third_party/flashrom;branch=${CHROMIUM_THIRDPARTY_BRANCH};protocol=https"

SRCREV ?= "c4748b9f7661725ed17960c589c88540da92074d"

S="${WORKDIR}/git"

inherit pkgconfig

do_install() {
    oe_runmake PREFIX=${prefix} DESTDIR=${D} install
}

CFLAGS_append = " -Wno-format-overflow -Wno-implicit-function-declaration"
