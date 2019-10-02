# See README.kernel.config for details on how the kernel configuration is built

KBRANCH ?= "chromeos-4.19"

require recipes-kernel/linux/linux-yocto.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

# Get Kernel from Chomium's own fork
SRC_URI = "git://chromium.googlesource.com/chromiumos/third_party/kernel;branch=${KBRANCH};protocol=https"
SRCREV = "cd591e6ddaea7c77cd2686f3e4b3552e4574eec6"
LINUX_VERSION ?= "4.19.70"
PV = "${LINUX_VERSION}+git${SRCPV}"

DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
DEPENDS += "openssl-native util-linux-native"

COMPATIBLE_MACHINE = "eve-chromebook"

# Linux-chromium is currently only available for eve-chromebook
SRC_URI_append = " file://chromium-x86-64-defconfig-R77-4.19-no-unset.cfg \
    file://extra_pixelbook-linux.cfg \
    file://extra_pixelbook-chromium.cfg \
    file://fix_modules.cfg"
