# See README.kernel.config for details on how the kernel configuration is built

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

KMACHINE_eve-chromebook = "intel-corei7-64"
COMPATIBLE_MACHINE_eve-chromebook = "eve-chromebook"

KMACHINE_x86-chromebook = "intel-corei7-64"
COMPATIBLE_MACHINE_x86-chromebook = "x86-chromebook"

SRCREV_machine_eve-chromebook ?= "06356153574af37fccb30f5e632edeb54ddd1f7b"
SRCREV_meta_eve-chromebook ?= "b8c82ba37370e4698ff0c42f3e54b8b4f2fb4ac0"
KBRANCH_eve-chromebook  = "v5.4/standard/base"
LINUX_VERSION_eve-chromebook = "5.4.23"

SRCREV_machine_x86-chromebook ?= "06356153574af37fccb30f5e632edeb54ddd1f7b"
SRCREV_meta_x86-chromebook ?= "b8c82ba37370e4698ff0c42f3e54b8b4f2fb4ac0"
KBRANCH_x86-chromebook  = "v5.4/standard/base"
LINUX_VERSION_x86-chromebook = "5.4.23"


SRC_URI_append_eve-chromebook = " file://chromium-x86-64-defconfig-R77-4.19-no-chromium.cfg \
    file://extra_pixelbook-linux.cfg \
"

SRC_URI_append_x86-chromebook = " file://chromium-x86-64-defconfig-R77-4.19-no-chromium.cfg \
    file://extra_chromebook.cfg \
"

SRC_URI_append = " file://fix_modules.cfg"
