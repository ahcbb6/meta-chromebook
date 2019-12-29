DESCRIPTION = "MrChromeBox Scripts: Scripts for setup/install/firmware update for ChromeOS devices"
HOMEPAGE = "https://mrchromebox.tech/#fwscript"
# MrChromebox doesnt specify a license
LICENSE = "Unlicense-mrchromebox"
SECTION = "firmware"

NO_GENERIC_LICENSE[Unlicense-mrchromebox] = "LICENSE"

SRC_URI = " \
    git://github.com/MrChromebox/scripts.git;branch=master;protocol=https \
    file://avoid_network_fetch.patch \
"
SRCREV = "2a23b36457e27c203cb661df001eb40f3997139e"

S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://LICENSE;md5=911690f51af322440237a253d695d19f"

do_install() {
    install -d ${D}/opt/fw_scripts/
    install -m 0744 ${S}/cbmodels.json ${D}/opt/fw_scripts/
    install -m 0744 ${S}/firmware-util.sh ${D}/opt/fw_scripts/
    install -m 0744 ${S}/firmware.sh ${D}/opt/fw_scripts/
    install -m 0744 ${S}/functions.sh ${D}/opt/fw_scripts/
    install -m 0744 ${S}/sources.sh ${D}/opt/fw_scripts/
}

RDEPENDS_${PN} = "bash dmidecode coreboot-utils-cbfstool flashrom-chromium vboot-utils-chromium rootdev-chromium"

FILES_${PN} = "/opt/fw_scripts/"
