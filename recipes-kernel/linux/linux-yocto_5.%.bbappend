# See README.kernel.config for details on how the kernel configuration is built

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

KMACHINE_eve-chromebook = "intel-corei7-64"
COMPATIBLE_MACHINE_eve-chromebook = "eve-chromebook"

KMACHINE_x86-chromebook = "intel-corei7-64"
COMPATIBLE_MACHINE_x86-chromebook = "x86-chromebook"


SRC_URI_append_eve-chromebook = " file://chromium-x86-64-defconfig-R77-4.19-no-chromium.cfg \
    file://extra_pixelbook-linux.cfg \
"

SRC_URI_append_x86-chromebook = " file://chromium-x86-64-defconfig-R77-4.19-no-chromium.cfg \
    file://extra_chromebook.cfg \
"

SRC_URI_append = " file://fix_modules.cfg"
