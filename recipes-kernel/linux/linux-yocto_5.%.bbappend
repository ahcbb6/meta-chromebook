# See README.kernel.config for details on how the kernel configuration is built

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

KMACHINE_eve-chromebook = "intel-corei7-64"
COMPATIBLE_MACHINE_eve-chromebook = "eve-chromebook"

KMACHINE_x86-chromebook = "intel-corei7-64"
COMPATIBLE_MACHINE_x86-chromebook = "x86-chromebook"

SRCREV_machine_eve-chromebook ?= "e378a5626133c1f46c7ee4ede3785dbb805dc5ab"
SRCREV_meta_eve-chromebook ?= "054d410d1a73e4729f1fe7540db058de69fe8cfe"
KBRANCH_eve-chromebook  = "v5.4/standard/base"
LINUX_VERSION_eve-chromebook = "5.4.34"

SRCREV_machine_x86-chromebook ?= "e378a5626133c1f46c7ee4ede3785dbb805dc5ab"
SRCREV_meta_x86-chromebook ?= "054d410d1a73e4729f1fe7540db058de69fe8cfe"
KBRANCH_x86-chromebook  = "v5.4/standard/base"
LINUX_VERSION_x86-chromebook = "5.4.34"


SRC_URI_append_eve-chromebook = " file://chromium-x86-64-defconfig-R77-4.19-no-chromium.cfg \
    file://extra_pixelbook-linux.cfg \
"

SRC_URI_append_x86-chromebook = " file://chromium-x86-64-defconfig-R77-4.19-no-chromium.cfg \
    file://extra_chromebook.cfg \
"

SRC_URI_append = " file://fix_modules.cfg"
