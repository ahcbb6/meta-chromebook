FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "file://network-manager-init.sh"

inherit update-rc.d
INITSCRIPT_NAME = "network-manager-init.sh"
INITSCRIPT_PARAMS = "start 80 5 ."

do_install_append (){
    install -d ${D}/${sysconfdir}/init.d/
    install -m 755 ${WORKDIR}/${INITSCRIPT_NAME} ${D}/${sysconfdir}/init.d/${INITSCRIPT_NAME}
}

FILES_${PN}_append = " ${sysconfdir}/init.d/${INITSCRIPT_NAME}"

# Allow network manager to manage interfaces
pkg_postinst_ontarget_${PN} () {
cat > ${sysconfdir}/network/interfaces < EOF
    # /etc/network/interfaces -- configuration file for ifup(8), ifdown(8)

    # The loopback interface
    auto lo
    iface lo inet loopback
EOF
}
