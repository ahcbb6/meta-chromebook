# See README.kernel.config for details on how the kernel configuration is built

require recipes-kernel/linux/linux-yocto.inc

# Get Kernel from Chomium's own fork, get kernel-meta from linux-yocto
SRC_URI = "git://chromium.googlesource.com/chromiumos/third_party/kernel;name=machine;branch=${KBRANCH};protocol=https"

KMETA = "kernel-meta"

PV = "${LINUX_VERSION}+git${SRCPV}"

DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
DEPENDS += "openssl-native util-linux-native"

