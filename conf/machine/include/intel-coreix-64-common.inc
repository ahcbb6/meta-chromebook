#
# Common configuration for all MACHINES of the intel-corei7-64 type
#

# Based on Intel-corei7 but support newer architectures

DEFAULTTUNE ?= "coreix-64-skylake"
require conf/machine/include/tune-skylake.inc
require conf/machine/include/x86-base.inc
