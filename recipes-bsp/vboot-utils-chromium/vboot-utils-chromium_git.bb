DESCRIPTION = "Reference implementation for Chrome OS verified boot in firmware."
HOMEPAGE = "https://chromium.googlesource.com/chromiumos/platform/vboot_reference/"
LICENSE = "BSD-3-Clause"
SECTION = "firmware"

SRC_URI = "git://chromium.googlesource.com/chromiumos/platform/vboot_reference.git;branch=${CHROMIUM_THIRDPARTY_BRANCH};protocol=https \
"

SRCREV ?= "695c56dc50a59e5c9098c94f41b3d86b8f99baf1"

DEPENDS += "openssl xz libyaml pkgconfig-native util-linux"

S = "${WORKDIR}/git"

SRC_URI_append = " \
    file://remove_fuzzer_option.patch \
"

LIC_FILES_CHKSUM = "file://LICENSE;md5=562c740877935f40b262db8af30bca36"

CFLAGS_append = " -Wno-array-bounds"

do_compile(){
    oe_runmake all
}

# Specifically install what we need to avoid messing with the Makefile
do_install() {

    install -d ${D}/${sbindir}/
    install -d ${D}/${datadir}/devkeys

    # Remove these for now since install wont allow recursive directories and we dont need them
    rm -rf ${B}/tests/devkeys/android
    rm -rf ${B}/tests/devkeys/uefi
    install -m644 ${B}/tests/devkeys/* ${D}/${datadir}/devkeys
    install -m 755  ${B}/build/utility/crossystem ${D}/${sbindir}/crossystem
    install -m 755  ${B}/build/futility/futility ${D}/${sbindir}/futility
    install -m 755  ${B}/build/utility/crossystem ${D}/${sbindir}/crossystem

    # All these utilities have been merged on futility, although it still uses the name of
    # the program being called as an argument, create symlinks for compatiblity
    futil_symlinks="dump_fmap dump_kernel_config gbb_utility vbutil_firmware vbutil_kernel vbutil_key vbutil_keyblock"
    cd  ${D}/${sbindir}/
    for prog in $futil_symlinks
    do
        ln -sf futility $prog
    done

}


FILES_${PN} += "${datadir}/devkeys/"

# We need a native version to be able to sign chromium kernels for ARM boards
BBCLASSEXTEND = "native"
