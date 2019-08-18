FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# It'd be worth investigating if this would work better on udev instead (or along with)

# Generic Chromebook -  This assumes this layer at some point grows to support other chromebooks
# Also, at this point I'm not aware if ther chromebooks require touchpad tweaks as well (need HW).
#  Set the XKB model to chromebook to get Home, End, PgDn, PgUp, Del and such
SRC_URI_append = " file://90-chromebook-keyboard.conf"

do_install_append (){
    install -m 644 ${WORKDIR}/90-chromebook-keyboard.conf ${D}/${datadir}/X11/xorg.conf.d/90-chromebook-keyboard.conf
}


# Pixelbook Specific (relies on patch to xkeyboard-config)
SRC_URI_append_eve-chromebook = " \
    file://local-overrides.quirks \
    file://90-pixelbook-keyboard.conf \
    file://99-pixelbook-touchpad-tweaks.conf \
"

do_install_append_eve-chromebook (){
    rm ${D}/${datadir}/X11/xorg.conf.d/90-chromebook-keyboard.conf
    install -m 644 ${WORKDIR}/90-pixelbook-keyboard.conf ${D}/${datadir}/X11/xorg.conf.d/90-pixelbook-keyboard.conf
    install -m 644 ${WORKDIR}/99-pixelbook-touchpad-tweaks.conf ${D}/${datadir}/X11/xorg.conf.d/99-pixelbook-touchpad-tweaks.conf
    install -d ${D}${sysconfdir}/libinput/
    install -m 644 ${WORKDIR}/local-overrides.quirks ${D}${sysconfdir}/libinput/local-overrides.quirks
}
