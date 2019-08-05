
inherit update-rc.d

LICENSE="CLOSED"

INITSCRIPT_NAME = "set-chromebook-keymap.sh"
INITSCRIPT_PARAMS = "start 90 1 3 5 ."

do_install (){
    install -d ${D}/${sysconfdir}/init.d/
    # Fallback to Pixelbook custom keymap
    echo "" >> ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
    echo "loadkeys ${KMAP}" >> ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
    echo "setfont ${BLINDFONT}" >> ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
    chmod 755 ${D}/${sysconfdir}/init.d/${INITSCRIPT_NAME}
}

FILES_${PN} = "${sysconfdir}/init.d/set-chromebook-keymap.sh"


KMAP ?= "${datadir}/keymaps/i386/qwerty/pixelbook.map.gz"

# The following technically creates a runtime dependency to terminus
# but I'm becoming blind so I can live with that dependency
BLINDFONT ?= "${datadir}/consolefonts/ter-v32n.psf.gz"

RDEPENDS_${PN} = "kbd terminus-font-consolefonts"
