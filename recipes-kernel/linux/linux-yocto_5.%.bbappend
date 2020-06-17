# See README.kernel.config for details on how the kernel configuration is built

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

KMACHINE_eve-chromebook = "intel-corei7-64"
COMPATIBLE_MACHINE_eve-chromebook = "eve-chromebook"

KMACHINE_x86-chromebook = "intel-corei7-64"
COMPATIBLE_MACHINE_x86-chromebook = "x86-chromebook"

SRCREV_machine_eve-chromebook ?= "37a68a90035cd2ac28005ed4136a4d376359bd17"
SRCREV_meta_eve-chromebook ?= "627191aa87d971e153f95beac4d9e45aea0e9b65"
KBRANCH_eve-chromebook  = "v5.4/standard/base"
LINUX_VERSION_eve-chromebook = "5.4.46"

SRCREV_machine_x86-chromebook ?= "37a68a90035cd2ac28005ed4136a4d376359bd17"
SRCREV_meta_x86-chromebook ?= "627191aa87d971e153f95beac4d9e45aea0e9b65"
KBRANCH_x86-chromebook  = "v5.4/standard/base"
LINUX_VERSION_x86-chromebook = "5.4.46"


SRC_URI_append_eve-chromebook = " file://chromium-x86-64-defconfig-R77-4.19-no-chromium.cfg \
    file://extra_pixelbook-linux.cfg \
"

SRC_URI_append_x86-chromebook = " file://chromium-x86-64-defconfig-R77-4.19-no-chromium.cfg \
    file://extra_chromebook.cfg \
"

SRC_URI_append = " file://fix_modules.cfg"
