# Should we set LANG here?

SET_ALIASES ?= "1"

do_install_append(){
    # etc/profile
    echo 'export TERM="xterm-256color"' >> ${D}/${sysconfdir}/profile
    echo 'export PATH="$PATH:/usr/local/sbin:/usr/sbin:/sbin"' >> ${D}/${sysconfdir}/profile

    # Bashrc
    echo 'export PS1="[\[$(tput sgr0)\]\[\033[38;5;22m\]\d \@\[$(tput sgr0)\]\[\033[38;5;15m\]] [\[$(tput sgr0)\]\[\033[38;5;172m\]\u@\h\[$(tput sgr0)\]\[\033[38;5;15m\]] [\[$(tput sgr0)\]\[\033[38;5;57m\]\w\[$(tput sgr0)\]\[\033[38;5;15m\]]\n\\$ \[$(tput sgr0)\]"' >> ${D}/${sysconfdir}/skel/.bashrc


    # The following is a bit intrusive since its set for all users and its simply my personal preference
    if [ "${SET_ALIASES}" -eq "1" ]; then
        echo "alias l='ls -lh'" >> ${D}/${sysconfdir}/skel/.bashrc
        echo "alias ls='ls --color=auto'" >> ${D}/${sysconfdir}/skel/.bashrc
        echo "alias ..='cd ..'" >> ${D}/${sysconfdir}/skel/.bashrc
        echo "alias grep='grep --color=auto'" >> ${D}/${sysconfdir}/skel/.bashrc
    fi
}
