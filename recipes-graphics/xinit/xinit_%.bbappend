# Set permissions for a non root user to start X

do_install_append(){
    chmod u+s ${D}/${bindir}/xinit
}
