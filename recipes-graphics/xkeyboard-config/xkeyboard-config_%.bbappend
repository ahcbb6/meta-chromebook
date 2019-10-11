FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " file://add-pixelbook-keyboard-map.patch"
SRC_URI_append = " file://fix-chromebook-keyboard-map.patch"
