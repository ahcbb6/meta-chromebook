DESCRIPTION = "coreboot tools"
SECTION = "coreboot"
HOMEPAGE = "https://github.com/coreboot/coreboot/README.md"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = " \
    gitsm://github.com/coreboot/coreboot.git;protocol=https;branch=${COREBOOT_BRANCH} \
    file://0001-utils-do-not-override-compiler-variables.patch \
"

COREBOOT_VERSION = "4.9"
COREBOOT_BRANCH = "master"
SRCREV = "7f520c8fe6fc991df2c4e91f42843d4290744ebb"

PV = "${COREBOOT_VERSION}+${SRCREV}"

# this indicates the folder to run do_compile from.
S="${WORKDIR}/git"

PACKAGES =+ "${PN}-cbfstool ${PN}-ifdtool"

FILES_${PN}-cbfstool = "${bindir}/cbfstool"
FILES_${PN}-ifdtool = "${bindir}/ifdtool"

# INSANE_SKIP_cbfstool = "ldflags"
# INSANE_SKIP_ifdtool = "ldflags"

# this command will be run to compile your source code. it assumes you are in the
# directory indicated by S. i'm just going to use make and rely on my Makefile
do_compile () {
    oe_runmake -C util/cbfstool TOOLLDFLAGS="${LDFLAGS}"
    oe_runmake -C util/ifdtool TOOLLDFLAGS="${LDFLAGS}"
}
 
# this will copy the compiled file and place it in ${bindir}, which is /usr/bin
do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${S}/util/cbfstool/cbfstool ${D}${bindir}
    install -m 0755 ${S}/util/ifdtool/ifdtool ${D}${bindir}
}


BBCLASSEXTEND = "native nativesdk"
