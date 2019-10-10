# See README.kernel.config for details on how the kernel configuration is built

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

KMACHINE_eve-chromebook = "intel-corei7-64"
COMPATIBLE_MACHINE_eve-chromebook = "eve-chromebook"

KMACHINE_x86-chromebook = "intel-corei7-64"
COMPATIBLE_MACHINE_x86-chromebook = "x86-chromebook"

KMACHINE_arm64-chromebook ?= "qemuarm64"
COMPATIBLE_MACHINE_arm64-chromebook = "arm64-chromebook"


SRCREV_machine_eve-chromebook ?= "dd25a04fc5e2e4549fc9b86157a01e0c72b53b03"
SRCREV_meta_eve-chromebook ?= "b867b78b5019ae455af97dec7794cff7527d7624"
KBRANCH_eve-chromebook  = "v5.2/standard/base"
LINUX_VERSION_eve-chromebook = "5.2.20"

SRCREV_machine_x86-chromebook ?= "dd25a04fc5e2e4549fc9b86157a01e0c72b53b03"
SRCREV_meta_x86-chromebook ?= "b867b78b5019ae455af97dec7794cff7527d7624"
KBRANCH_x86-chromebook  = "v5.2/standard/base"
LINUX_VERSION_x86-chromebook = "5.2.20"


SRCREV_machine_arm64-chromebook ?= "97956dd1930f3213f685ce9875df6f3418cef6db"
SRCREV_meta_arm64-chromebook ?= "4d9e181cd82c067e10f4a70e4bae93df66bacb62"
KBRANCH_arm64-chromebook  = "v5.2/standard/qemuarm64"
LINUX_VERSION_arm64-chromebook = "5.2.8"


SRC_URI_append_eve-chromebook = " file://chromium-x86-64-defconfig-R77-4.19-no-chromium.cfg \
    file://extra_pixelbook-linux.cfg \
"

SRC_URI_append_x86-chromebook = " file://chromium-x86-64-defconfig-R77-4.19-no-chromium.cfg \
    file://extra_chromebook.cfg \
"

SRC_URI_append = " file://fix_modules.cfg"
