DESCRIPTION = "MrChromeBox Scripts: Scripts for setup/install/firmware update for ChromeOS devices"
HOMEPAGE = "https://mrchromebox.tech/#fwscript"
# MrChromebox doesnt specify a license
LICENSE = "CLOSED"
SECTION = "firmware"

SRC_URI = " \
    git://github.com/MrChromebox/scripts.git;branch=master;protocol=https \
    file://avoid_network_fetch.patch \
"
SRCREV = "836ac87ecc8c1dca6fc24650352a5c047f7fe570"

S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://README.md;md5=c1e4dcae02399a68d7684799fca8e60e"

do_install() {
    install -d ${D}/opt/fw_scripts/
    install -m 0744 ${S}/cbmodels.json ${D}/opt/fw_scripts/
    install -m 0744 ${S}/firmware-util.sh ${D}/opt/fw_scripts/
    install -m 0744 ${S}/firmware.sh ${D}/opt/fw_scripts/
    install -m 0744 ${S}/functions.sh ${D}/opt/fw_scripts/
    install -m 0744 ${S}/sources.sh ${D}/opt/fw_scripts/
}

RDEPENDS_${PN} = "bash dmidecode coreboot-utils-cbfstool flashrom-chromium vboot-utils-chromium-gbb-utility vboot-utils-chromium-crossystem rootdev-chromium"

FILES_${PN} = "/opt/fw_scripts/"
