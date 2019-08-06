# After the first boot, the ~/.config/xfce4/xfconf/xfce-perchannel-xml/xfwm4.xml file is created.
# This makes sense, but for some reason, either the file itself or one of the configs in it
# causes an Assertion error with the intel 915 driver, which kills the X server automatically

# TODO
# Debugging this would take more time than the one I have so sadly, for now, create a script
# that removes the file every time the device is turned off (or before starting)


inherit update-rc.d

LICENSE="CLOSED"

SRC_URI = "file://remove_xfconf.sh"

# Start and stop this accordingly to avoid the assertion error, again this should be a temporary solution).
INITSCRIPT_NAME = "remove_xfconf.sh"
# This needs to be started before the X server (which starts at 9)
INITSCRIPT_PARAMS = "start 8 5 . stop 20 0 1 2 3 6 ."
do_install (){
    install -d ${D}/${sysconfdir}/init.d/
    install -m 755 ${WORKDIR}/remove_xfconf.sh ${D}/${sysconfdir}/init.d/remove_xfconf.sh
}

FILES_${PN} = "${sysconfdir}/init.d/remove_xfconf.sh"
