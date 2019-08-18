DESCRIPTION = "Reference implementation for Chrome OS verified boot in firmware."
HOMEPAGE = "https://chromium.googlesource.com/chromiumos/platform/vboot_reference/"
LICENSE = "BSD-3-Clause"
SECTION = "firmware"

SRC_URI = "git://chromium.googlesource.com/chromiumos/platform/vboot_reference.git;branch=master;protocol=https \
"

# Play it safe by using same version as MrChromeBox (doesnt build)
#SRCREV = "bbdd62f9b030db7ad8eef789aaf58a7ff9a25656"

# This one points to master at this point and at least, gbb_utility and crossystem work properly on EVE
SRCREV = "eb10ebf76d78a7ac7cb6b66c6f1bba747d4e10ca"
S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://LICENSE;md5=562c740877935f40b262db8af30bca36"


do_compile(){
    oe_runmake futil
    oe_runmake utils
}

do_install() {
    install -d ${D}/${sbindir}/
    install -m 755  ${S}/build/futility/futility ${D}/${sbindir}/gbb_utility
    install -m 755  ${S}/build/utility/crossystem ${D}/${sbindir}/crossystem
}


# There are more binaries here that might be interesting, additional packages
# can be added (e.b. enable_dev_boot)
FILES_${PN} = ""
FILES_${PN}-crossystem = "${sbindir}/crossystem"
FILES_${PN}-gbb-utility = "${sbindir}/gbb_utility"

DEPENDS += "openssl xz libyaml pkgconfig-native"

PACKAGES += "${PN}-gbb-utility ${PN}-crossystem"
