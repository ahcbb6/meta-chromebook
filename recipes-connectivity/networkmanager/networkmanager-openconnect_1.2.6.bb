SUMMARY = "NetworkManager-openconnect-plugin"
SECTION = "net/misc"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=186e8b54342da4f753a62b7748c947db"

DEPENDS = "dbus dbus-glib networkmanager intltool-native glib-2.0-native openconnect"

inherit gnomebase useradd gettext systemd

SRC_URI = "${GNOME_MIRROR}/NetworkManager-openconnect/${@gnome_verdir("${PV}")}/NetworkManager-openconnect-${PV}.tar.xz"

SRC_URI[sha256sum] = "95109803596a9782680a5dca3b51c4ad8ff7e126169d5431278cab694112975a"


S = "${WORKDIR}/NetworkManager-openconnect-${PV}"

PACKAGECONFIG[gnome] = "--with-gnome,--without-gnome"

do_install_append () {
    rm -rf ${D}${libdir}/NetworkManager/*.la
}

# Create user and group nm-openconnect that are needed since version 1.0.6
USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system nm-openconnect"

FILES_${PN} += " \
    ${libdir}/NetworkManager/*.so \
    ${nonarch_libdir}/NetworkManager/VPN/nm-openconnect-service.name \
"

FILES_${PN}-staticdev += " \
    ${libdir}/NetworkManager/*.a \
"

RDEPENDS_${PN} = " \
    networkmanager \
    openconnect \
"
