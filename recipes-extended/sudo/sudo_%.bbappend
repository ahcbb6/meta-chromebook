do_install_append(){
    # Make sudo available to users in the group sudo
    echo "%sudo ALL=(ALL) ALL" >>  ${D}/${sysconfdir}/sudoers
}
