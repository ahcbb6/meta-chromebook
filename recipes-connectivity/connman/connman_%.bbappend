PACKAGECONFIG[openconnect] = "--enable-openconnect --with-openconnect=${sbindir}/openconnect,--disable-openconnect,,openconnect"

# Enable VPN support
PACKAGECONFIG_append = " openvpn vpnc l2tp pptp openconnect"

SUMMARY_${PN}-plugin-vpn-openconnect = "An Openconnect plugin for ConnMan VPN"
DESCRIPTION_${PN}-plugin-vpn-openconnect = "The ConnMan Openconnect plugin uses openconnect client \
    to create a VPN connection to Openconnect server."
FILES_${PN}-plugin-vpn-openconnect += "${libdir}/connman/scripts/openconnect-script \
    ${libdir}/connman/plugins-vpn/openconnect.so"
RDEPENDS_${PN}-plugin-vpn-openconnect += "${PN}-vpn"
RRECOMMENDS_${PN} += "${@bb.utils.contains('PACKAGECONFIG','openconnect','${PN}-plugin-vpn-openconnect', '', d)}"
