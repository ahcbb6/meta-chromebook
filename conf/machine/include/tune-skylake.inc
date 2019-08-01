# Settings for the GCC(1) cpu-type "skylake":
#
#     Intel Skylake CPU with 64-bit extensions, MMX, SSE, SSE2, SSE3, SSSE3, SSE4.1,
#     SSE4.2 and POPCNT instruction set support.
#
# This tune is recommended for Intel Skylake architectures (and beyond).
#

DEFAULTTUNE ?= "coreix-64-skylake"

# Include the previous tune to pull in PACKAGE_EXTRA_ARCHS
require conf/machine/include/tune-corei7.inc # tunei7 includes tune core2

# Extra tune features
TUNEVALID[skylake] = "Enable corei7 specific processor optimizations"
TUNE_CCARGS .= "${@bb.utils.contains('TUNE_FEATURES', 'skylake', ' -march=skylake -mtune=skylake -mfpmath=sse -msse4.2', '', d)}"


# Only support 64 bit at this point


# Extra tune selections
# AVAILTUNES += "corei7-32"
# TUNE_FEATURES_tune-corei7-32 = "${TUNE_FEATURES_tune-x86} corei7"
# BASE_LIB_tune-corei7-32 = "lib"
# TUNE_PKGARCH_tune-corei7-32 = "corei7-32"
# PACKAGE_EXTRA_ARCHS_tune-corei7-32 = "${PACKAGE_EXTRA_ARCHS_tune-core2-32} corei7-32"
# QEMU_EXTRAOPTIONS_corei7-32 = " -cpu Skylake,check=false"

AVAILTUNES += "coreix-64-skylake"
TUNE_FEATURES_tune-coreix-64-skylake = "${TUNE_FEATURES_tune-x86-64} skylake"
BASE_LIB_tune-coreix-64-skylake = "lib64"
TUNE_PKGARCH_tune-coreix-64-skylake = "coreix-64-skylake"
PACKAGE_EXTRA_ARCHS_tune-coreix-64-skylake = "${PACKAGE_EXTRA_ARCHS_tune-core2-64} coreix-64-skylake"

# Keep Skylake for emulation
QEMU_EXTRAOPTIONS_coreix-64-skylake = " -cpu Skylake-Client,check=false"

# AVAILTUNES += "corei7-64-x32"
# TUNE_FEATURES_tune-corei7-64-x32 = "${TUNE_FEATURES_tune-x86-64-x32} corei7"
# BASE_LIB_tune-corei7-64-x32 = "libx32"
# TUNE_PKGARCH_tune-corei7-64-x32 = "corei7-64-x32"
# PACKAGE_EXTRA_ARCHS_tune-corei7-64-x32 = "${PACKAGE_EXTRA_ARCHS_tune-core2-64-x32} corei7-64-x32"
# QEMU_EXTRAOPTIONS_corei7-64-x32 = " -cpu Skylake,check=false"
