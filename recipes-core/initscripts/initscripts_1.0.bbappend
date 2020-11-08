
do_install_prepend() {
    # Proper permissions for the XUSER created on the distro
    # Pulseaudio (and others) require user to be the owner of /tmp
    sed -i -e 's#root root 1777 /var/volatile/tmp#1000 1000 1777 /var/volatile/tmp#g' ${WORKDIR}/volatiles
    sed -i -e 's#root root 1777 /var/tmp#1000 1000 1777 /var/tmp#g' ${WORKDIR}/volatiles
    sed -i -e 's#root root 1777 /tmp#1000 1000 1777 /tmp#g' ${WORKDIR}/volatiles

    # Avoid creating the symlink for resolv.conf, NetworkManager will refuse to update it if its a symlink
    sed -i -e 's~l root root 0644 /etc/resolv.conf~#l root root 0644 /etc/resolv.conf~g' ${WORKDIR}/volatiles
}
