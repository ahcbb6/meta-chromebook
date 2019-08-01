DESCRIPTION = "A small image just capable of allowing the pixelbook to boot to console, with minimal network capabilities"

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

# Console Utils
# Todo for blind people terminus-consolefonts maybe
# Get Pixelbook keyboard working on console
IMAGE_INSTALL_append = " kbd kbd-keymaps terminus-font-consolefonts"

# Network Utils
IMAGE_INSTALL_append = " dhcp-client iw iproute2 wpa-supplicant connman connman-client"

# HW Utils
IMAGE_INSTALL_append = " pciutils"

# Add more locales?
IMAGE_LINGUAS = "en-us"

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"


EXTRA_IMAGE_FEATURES_append = " ssh-server-openssh"

