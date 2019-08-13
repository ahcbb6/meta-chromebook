DESCRIPTION = "A full image capable of allowing the pixelbook to boot using a graphics environment, adding more mis utilities in the process"

require pixelbook-image-minimal.bb

# Command Utils
IMAGE_INSTALL_append = " bash-completion util-linux-fdisk util-linux-resizepart procps"

# Network Utils
IMAGE_INSTALL_append = " bind git nmap mosh curl netcat tcpdump traceroute"

# HW Utils
IMAGE_INSTALL_append = " usbutils util-linux-lsblk util-linux-dmesg kmod udev-extraconf hwdata lshw"

# Misc Utils:
IMAGE_INSTALL_append = " python3 python3-pip vim grep htop tmux"

# XFCE:
IMAGE_INSTALL_append = " packagegroup-core-x11 packagegroup-xfce-base mousepad xfce4-appfinder xfce4-battery-plugin xfce4-netload-plugin xfce4-wavelan-plugin "

# EXTRA GUI Stuff
IMAGE_INSTALL_append = " gparted epiphany chromium-x11"
# evince requires gnome-desktop?
# ristretto require poppler, poppler does not play nice with Gobject-introspection

DISTRO_FEATURES_append = " x11"

# Input
IMAGE_INSTALL_append = " xf86-input-libinput libinput setxkbmap xkeyboard-config"

# To debug install xev

# TODO is it better to use synaptics for touchpad?
# xf86-input-synaptics


# Solve XFCE bug for now
# It seems that this only happens when starting X as root
# IMAGE_INSTALL_append = " xfwm4-config-rm"


# Could probably install kernel-modules here and call it a day

# Yet to be tested
#EXTRA_IMAGE_FEATURES += "package-management"
