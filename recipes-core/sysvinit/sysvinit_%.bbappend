# Bootlogd for some reason is hurting performance and hanging at boot
# we dont actually need it but it came with sysvinit
do_install_append(){
    rm -rf ${D}/${sysconfdir}/init.d/bootlogd
    rm -rf ${D}/${sbindir}/bootlogd
}