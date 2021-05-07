DESCRIPTION = "Embedded Controller software includes a lightweight, multitasking OS with modules for power sequencing, keyboard control, thermal control, battery charging, and verified boot."
LICENSE = "BSD-3"
HOMEPAGE = "http://flashrom.org"

LIC_FILES_CHKSUM = "file://LICENSE;md5=562c740877935f40b262db8af30bca36"
DEPENDS = "libftdi libusb1"

EC_BRANCH_x86-chromebook = "firmware-link-2695.B"

SRC_URI = "git://chromium.googlesource.com/chromiumos/platform/ec;branch=${EC_BRANCH};protocol=https"

SRCREV_x86-chromebook ?= "352afa8e2ea3f51b8ae1d62d50e67a545746d1a7"

S="${WORKDIR}/git"

inherit pkgconfig

# This source code builds both the firmware and utilities, the firmware targets an arm architecture
# while the utils an x86-64 one, in this case we only want to build the host utilities so we
# override the main do_compile function to build ectool manually
COMPATIBLE_MACHINE = "x86-chromebook"

# Since we'er manually compiling its healthy to override the specifics per MACHINE
do_compile_x86-chromebook() {
    cd util
    ${CC} ${CFLAGS} ${LDFLAGS} ectool.c comm-lpc.c -I../include -I../board/link -I../chip/lm4/  -o ectool
}

do_install() {
    install -d ${D}/${bindir}
    install -m 755 ${S}/util/ectool ${D}/${bindir}/ectool
}
