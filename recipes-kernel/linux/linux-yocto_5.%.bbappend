# Base our linux-yocto on the coreix-64-skylake-intel-common MACHINE from meta-yocto-bsp

KBRANCH_coreix-64-skylake-intel-common  = "v5.0/standard/base"
KMACHINE_coreix-64-skylake-intel-common ?= "common-pc-64"
SRCREV_machine_coreix-64-skylake-intel-common ?= "f990fd0ce123aa6035042efad09b2ddc3e7d48f4"

COMPATIBLE_MACHINE_coreix-64-skylake-intel-common = "coreix-64-skylake-intel-common"

LINUX_VERSION_coreix-64-skylake-intel-common = "5.0.13"


# At this point we need to use linux-intel config (sadly), just because we know
# it provides the necessary configs analyzing this furhter would allow us to
# base our config on genericx86-64, but at least this allows us to keep up with
# newer kernel versions coming from linux-yocto, e.g. as of today 4.19 vs 5.0.13

SRC_URI_append = " file://defconfig"



# Add some extra stuff (mainly graphics due to graphics and HID)
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " file://extra_pixelbook.cfg"
