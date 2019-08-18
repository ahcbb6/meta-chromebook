# Dont call our user xuser

XUSER ?= "chronospoky"

# xuser recipe uses useradd vs adduser,which requires the password to be encrypted
USERADD_PARAM_${PN} = "--create-home \
    --groups video,tty,audio,input,shutdown,disk \
    --user-group -s /bin/bash -p $(echo ${XUSER} | openssl passwd -stdin) ${XUSER}"

do_install_append(){
    sed -i 's/xuser/${XUSER}/' ${D}${sysconfdir}/dbus-1/system.d/system-xuser.conf
}


DEPENDS = "openssl-native"
