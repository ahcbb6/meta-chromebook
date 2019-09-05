DESCRIPTION = "A small image just capable of allowing the pixelbook to boot to console, with minimal network capabilities"

CHROMEBOOK_NETMANAGER ?= "connman connman-client"

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

# Console Utils
IMAGE_INSTALL_append = " chromebook-console-keymap kbd kbd-keymaps terminus-font-consolefonts ncurses bash"

# Network Utils
IMAGE_INSTALL_append = " dhcp-client iw iproute2 wpa-supplicant ${CHROMEBOOK_NETMANAGER} wireguard-tools"

# HW Utils
IMAGE_INSTALL_append = " pciutils util-linux-lsblk util-linux-libuuid util-linux-lscpu util-linux-sfdisk util-linux-uuidd util-linux-uuidgen util-linux-uuidparse e2fsprogs-resize2fs coreutils mrchromebox-fw-utils seabios"

# Add more locales?
IMAGE_LINGUAS = "en-us"

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"


EXTRA_IMAGE_FEATURES_append = " ssh-server-openssh"

# We want another user to be created, the same user for both
# minimal and xfce images, and its easier to wire it in
# through the xuser-account recipe, to be able to launch
# launch Xorg from a non-root user, so instead of creating
# our own user here just install the xuser-account on the
# minimal image as well, AFAIC there should be no repercusions.

inherit extrausers
EXTRA_USERS_PARAMS = "\
    usermod -a -G sudo chronospoky; \
"

IMAGE_INSTALL_append = " sudo xuser-account"
