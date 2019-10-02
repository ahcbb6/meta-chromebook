# See README.kernel.config for details on how the kernel configuration is built

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"


# Important note:
# When the i915 driver is built in shows the following error:
#
# If the driver is built as module the firmware load correctly:
# [drm] Finished loading DMC firmware i915/kbl_dmc_ver1_04.bin (v1.4)

SRC_URI_eve-chromebook_append = " file://extra_pixelbook-linux.cfg"

# Base both x86-chromebook and eve-chromebook on the intel-corei7-64 config
KMACHINE_intel-x86-common = "intel-corei7-64"
