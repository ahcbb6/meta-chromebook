KBRANCH ?= "chromeos-4.19"

require recipes-kernel/linux/linux-chromium.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"


SRC_URI_append = " git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-4.19;destsuffix=${KMETA}"
SRCREV_meta = "ad6f8b357720ca8167a090713b7746230cf4b314"

SRCREV_machine = "cd591e6ddaea7c77cd2686f3e4b3552e4574eec6"

LINUX_VERSION ?= "4.19.70"

# Linux-chromium 4.19 is currently only available for eve-chromebook
COMPATIBLE_MACHINE = "eve-chromebook|x86-chromebook"

SRC_URI_append = " file://chromium-x86-64-defconfig-R77-4.19-no-unset.cfg \
    file://extra_pixelbook-linux.cfg \
    file://extra_pixelbook-chromium.cfg \
"

# Kernel bypasses CFLAGS
KERNEL_CC_append = " -Wno-zero-length-bounds -Wno-array-bounds -Wno-builtin-declaration-mismatch"