DESCRIPTION = "(Chromium Fork) flashrom is a utility for identifying, reading, writing, verifying and erasing flash chips"
LICENSE = "GPLv2"
HOMEPAGE = "http://flashrom.org"

LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
DEPENDS = "pciutils libusb libusb-compat"

SRC_URI = "git://chromium.googlesource.com/chromiumos/third_party/flashrom;branch=factory-eve-9667.B;protocol=https"

SRCREV = "b6e26e656c0696ccd1715a6d2c9acee75ab0c092"

S="${WORKDIR}/git"

inherit pkgconfig

do_install() {
    oe_runmake PREFIX=${prefix} DESTDIR=${D} install
}

CFLAGS_append = " -Wno-format-overflow"
