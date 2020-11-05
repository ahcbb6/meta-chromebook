DESCRIPTION = "A full image capable of allowing the pixelbook to boot using a graphics environment, adding more mis utilities in the process"

require chromebook-image-minimal.bb

CHROMEBOOK_NETMANAGER = "networkmanager networkmanager-openvpn network-manager-applet"

# Command Utils
IMAGE_INSTALL_append = " bash-completion util-linux-fdisk util-linux-resizepart procps"

# HW Utils
IMAGE_INSTALL_append = " usbutils util-linux-lsblk util-linux-dmesg kmod udev-extraconf hwdata lshw cryptsetup lvm2"

# Misc Utils:
IMAGE_INSTALL_append = " python3 python3-pip vim grep htop tmux cronie emacs-full patchelf"

# XFCE:
IMAGE_INSTALL_append = " packagegroup-core-x11 packagegroup-xfce-base gedit xfce4-appfinder xfce4-battery-plugin xfce4-netload-plugin gvfs gvfsd-trash thunar-volman xfce4-power-manager xfce4-pulseaudio-plugin pavucontrol"

# EXTRA GUI Stuff
IMAGE_INSTALL_append_eve-chromebook = " gparted ristretto chromium-x11 chromium-x11-chromedriver remmina"
IMAGE_INSTALL_append_x86-chromebook = " gparted ristretto chromium-x11 chromium-x11-chromedriver remmina"
IMAGE_INSTALL_append_mediatek8173-chromebook = " gparted ristretto epiphany"

DISTRO_FEATURES_append = " x11"

# Input
IMAGE_INSTALL_append = " xf86-input-libinput libinput setxkbmap xkeyboard-config"

# To debug install xev

# TODO is it better to use synaptics for touchpad?
# xf86-input-synaptics


# Solve XFCE bug for now
# It seems that this only happens when starting X as root
# IMAGE_INSTALL_append = " xfwm4-config-rm"

# Since this is a more complete image, install built kernel-modules for more functionality
IMAGE_INSTALL_append = " kernel-modules"

EXTRA_IMAGE_FEATURES += "package-management"
