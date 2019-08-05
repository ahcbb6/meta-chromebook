FILESEXTRAPATHS_prepend := "${THISDIR}/files:"


# Important note:
# When the i915 driver is built in shows the following error:
#
# If the driver is built as module the firmware load correctly:
# [drm] Finished loading DMC firmware i915/kbl_dmc_ver1_04.bin (v1.4)

SRC_URI_append = " file://extra_pixelbook.cfg"

# Base Skylake/Kaby Lake on the config coming from meta-intel (Nehalem)
KMACHINE_coreix-64-skylake-intel-common = "intel-corei7-64"
