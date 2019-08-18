# Base our linux-yocto on the coreix-64-skylake-intel-common MACHINE from meta-yocto-bsp

KBRANCH_coreix-64-skylake-intel-common  = "v5.2/standard/base"
KMACHINE_coreix-64-skylake-intel-common ?= "common-pc-64"
SRCREV_machine_coreix-64-skylake-intel-common ?= "97956dd1930f3213f685ce9875df6f3418cef6db"

COMPATIBLE_MACHINE_coreix-64-skylake-intel-common = "coreix-64-skylake-intel-common"

LINUX_VERSION_coreix-64-skylake-intel-common = "5.2.8"


SRCREV_machine_eve-chromebook ?= "97956dd1930f3213f685ce9875df6f3418cef6db"
SRCREV_meta_eve-chromebook ?= "4d9e181cd82c067e10f4a70e4bae93df66bacb62"

# At this point we need to use linux-intel config (sadly), just because we know
# it provides the necessary configs analyzing this furhter would allow us to
# base our config on genericx86-64, but at least this allows us to keep up with
# newer kernel versions coming from linux-yocto, e.g. as of today 4.19 vs 5.0.13

# Add some extra stuff (mainly graphics due to graphics and HID)
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append_eve-chromebook = " file://defconfig \
    file://extra_pixelbook.cfg \
"

KMETA_BRANCH ?= "yocto-5.2"
SRC_URI = "git://git.yoctoproject.org/linux-yocto.git;name=machine;branch=${KBRANCH}; \
           git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${KMETA_BRANCH};destsuffix=${KMETA}"




SRC_URI_append_x86-chromebook = " file://defconfig"

KBRANCH_arm64-chromebook  = "v5.2/standard/qemuarm64"
KMACHINE_arm64-chromebook ?= "qemuarm64"
SRCREV_arm64-chromebook ?= "97956dd1930f3213f685ce9875df6f3418cef6db"

COMPATIBLE_MACHINE_arm64-chromebook = "arm64-chromebook"

LINUX_VERSION_arm64-chromebook = "5.2.8"
SRCREV_machine_arm64-chromebook ?= "97956dd1930f3213f685ce9875df6f3418cef6db"
SRCREV_meta_arm64-chromebook ?= "4d9e181cd82c067e10f4a70e4bae93df66bacb62"
