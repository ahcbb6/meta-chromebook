This README file contains information on the contents of the meta-pixelbook layer.

This layer allows you to build a Linux distribution for the Pixelbook (Chromebook EVE)
using the OpenEmbedded Infrastructure.

There is no reason why this can't be expanded to work on other chromebooks,
the Chromebook EVE is simply the hardware I have available to test at this point.


Please see the corresponding sections below for details.

Dependencies
============

        OE-core (meta)
        meta-poky
        meta-yocto-bsp
        meta-intel
        meta-oe
        meta-multimedia
        meta-gnome
        meta-python
        meta-networking
        meta-xfce
        meta-browser

Patches
=======

Please submit any patches against the meta-pixelbook layer to the github repo https://github.com/aehs29/meta-pixelbook
and cc: the maintainer: aehs29 at gmail

Table of Contents
=================

  I. Adding the meta-pixelbook layer to your build
 II. Misc


I. Adding the meta-pixelbook layer to your build
=================================================

Run 'bitbake-layers add-layer meta-pixelbook'