# See README.kernel.config for details on how the kernel configuration is built

KBRANCH ?= "chromeos-4.19"

require recipes-kernel/linux/linux-yocto.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

# Get Kernel from Chomium's own fork, get kernel-meta from linux-yocto
SRC_URI = "git://chromium.googlesource.com/chromiumos/third_party/kernel;name=machine;branch=${KBRANCH};protocol=https \
    git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-4.19;destsuffix=${KMETA}"


SRCREV_machine = "cd591e6ddaea7c77cd2686f3e4b3552e4574eec6"
SRCREV_meta = "ad6f8b357720ca8167a090713b7746230cf4b314"
KMETA = "kernel-meta"


LINUX_VERSION ?= "4.19.70"
PV = "${LINUX_VERSION}+git${SRCPV}"

DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
DEPENDS += "openssl-native util-linux-native"

COMPATIBLE_MACHINE = "eve-chromebook|x86-chromebook|intel-corei7-64|qemux86-64"

# Linux-chromium is currently only available for eve-chromebook
SRC_URI_append = " file://chromium-x86-64-defconfig-R77-4.19-no-unset.cfg \
    file://extra_pixelbook-linux.cfg \
    file://extra_pixelbook-chromium.cfg \
    file://fix_modules.cfg"
