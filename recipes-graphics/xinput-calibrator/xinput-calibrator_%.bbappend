# The xinput-calibrator seems to be executing twice at every boot
# Delete it from autostart to avoid this behavior
do_install_append(){
    rm ${D}${sysconfdir}/xdg/autostart/xinput_calibrator.desktop
}
