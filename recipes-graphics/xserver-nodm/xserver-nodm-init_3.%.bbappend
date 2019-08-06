# I don't like using the xuser user, I want to user my own
# but still wire it in through the xuser-account recipe (with some changes)
XUSER ?= "pixelpoky"

do_install (){
    install -d ${D}${sysconfdir}/default
    install xserver-nodm.conf.in ${D}${sysconfdir}/default/xserver-nodm
    install -d ${D}${sysconfdir}/xserver-nodm
    install Xserver ${D}${sysconfdir}/xserver-nodm/Xserver
    install -d ${D}${sysconfdir}/X11/Xsession.d
    install X11/Xsession.d/* ${D}${sysconfdir}/X11/Xsession.d/
    install X11/Xsession ${D}${sysconfdir}/X11/

    BLANK_ARGS="${@bb.utils.contains('PACKAGECONFIG', 'blank', '', '-s 0 -dpms', d)}"
    NO_CURSOR_ARG="${@bb.utils.contains('PACKAGECONFIG', 'nocursor', '-nocursor', '', d)}"
    if [ "${ROOTLESS_X}" = "1" ] ; then
        XUSER_HOME="/home/${XUSER}"
        #XUSER="xuser"
    else
        XUSER_HOME=${ROOT_HOME}
        XUSER="root"
    fi
    sed -i "s:@HOME@:${XUSER_HOME}:; s:@USER@:${XUSER}:; s:@BLANK_ARGS@:${BLANK_ARGS}:" \
        ${D}${sysconfdir}/default/xserver-nodm
    sed -i "s:@NO_CURSOR_ARG@:${NO_CURSOR_ARG}:" ${D}${sysconfdir}/default/xserver-nodm

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/xserver-nodm.service.in ${D}${systemd_unitdir}/system/xserver-nodm.service
        sed -i "s:@USER@:${XUSER}:" ${D}${systemd_unitdir}/system/xserver-nodm.service
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES','sysvinit','true','false',d)}; then
        install -d ${D}${sysconfdir}/init.d
        install xserver-nodm ${D}${sysconfdir}/init.d
    fi
}
