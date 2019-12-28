# See README.kernel.config for details on how the kernel configuration is built

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

KMACHINE_eve-chromebook = "intel-corei7-64"
COMPATIBLE_MACHINE_eve-chromebook = "eve-chromebook"

KMACHINE_x86-chromebook = "intel-corei7-64"
COMPATIBLE_MACHINE_x86-chromebook = "x86-chromebook"

SRCREV_machine_eve-chromebook ?= "ef54f0ac8d7caf7cc69f24b69de4633f796a4850"
SRCREV_meta_eve-chromebook ?= "01aea505853474fc863c5efe8a18706f11d8197c"
KBRANCH_eve-chromebook  = "v5.2/standard/base"
LINUX_VERSION_eve-chromebook = "5.2.26"

SRCREV_machine_x86-chromebook ?= "ef54f0ac8d7caf7cc69f24b69de4633f796a4850"
SRCREV_meta_x86-chromebook ?= "01aea505853474fc863c5efe8a18706f11d8197c"
KBRANCH_x86-chromebook  = "v5.2/standard/base"
LINUX_VERSION_x86-chromebook = "5.2.26"


SRC_URI_append_eve-chromebook = " file://chromium-x86-64-defconfig-R77-4.19-no-chromium.cfg \
    file://extra_pixelbook-linux.cfg \
"

SRC_URI_append_x86-chromebook = " file://chromium-x86-64-defconfig-R77-4.19-no-chromium.cfg \
    file://extra_chromebook.cfg \
"

SRC_URI_append = " file://fix_modules.cfg"
