#!/bin/sh
# For some reason this bug only happens when the root user starts
# the X server, ROOTLESS_X seems to work properly
if [ "$USER" == "root" ]; then
    rm /home/root/.config/xfce4/xfconf/xfce-perchannel-xml/xfwm4.xml
fi
