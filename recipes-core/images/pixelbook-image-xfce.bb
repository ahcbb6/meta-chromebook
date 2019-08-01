DESCRIPTION = "A full image capable of allowing the pixelbook to boot using a graphics environment, adding more mis utilities in the process"

require pixelbook-image-minimal.bb

# Network Utils
IMAGE_INSTALL_append = " bind git nmap mosh curl"

# HW Utils
IMAGE_INSTALL_append = " usbutils util-linux-lsblk util-linux-dmesg kmod udev-extraconf hwdata lshw"

# Misc Utils:
IMAGE_INSTALL_append = " python3 vim grep bash htop tmux"

# XFCE:
IMAGE_INSTALL_append = " packagegroup-core-x11 packagegroup-xfce-base mousepad xfce4-appfinder epiphany"

DISTRO_FEATURES_append = " x11"

# Input
IMAGE_INSTALL_append = " xf86-input-libinput libinput setxkbmap xkeyboard-config"
# Debug install xev
# TODO is it better to use synaptics for touchpad?
# xf86-input-synaptics


# Solve XFCE bug for now
IMAGE_INSTALL_append = " xfwm4-config-rm"


# Could probably install kernel-modules here and call it a day

# Yet to be tested
#EXTRA_IMAGE_FEATURES += "package-management"
