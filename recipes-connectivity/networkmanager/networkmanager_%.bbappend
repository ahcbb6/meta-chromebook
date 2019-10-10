# Allow network manager to manage interfaces
pkg_postinst_ontarget_${PN} () {
    echo "" >  ${sysconfdir}/network/interfaces
}
