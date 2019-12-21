DESCRIPTION = "SeaBIOS"
HOMEPAGE = "http://www.coreboot.org/SeaBIOS"
LICENSE = "LGPLv3"
SECTION = "firmware"

SRC_URI = " \
    git://github.com/MrChromebox/SeaBIOS.git;branch=master;protocol=https \
    file://hostcc.patch \
    file://use_python_from_buildsystem.patch \
"
SRCREV = "1b63074bb336e36a5c8b83bc8a623a37a34e77a8"

S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504         \
                    file://COPYING.LESSER;md5=6a6a8e020838b23406c81b19c1d46df6  \
                    "

FILES_${PN} = "/usr/share/firmware"

DEPENDS = "util-linux-native file-native bison-native flex-native gettext-native acpica-native python-native"

TUNE_CCARGS = ""
EXTRA_OEMAKE += "HOSTCC='${BUILD_CC}'"
EXTRA_OEMAKE += "CROSS_PREFIX=${TARGET_PREFIX}"

COMPATIBLE_HOST = "(i.86|x86_64|aarch64|arm).*-linux"

# This should be set on the machine.conf
# SeaBIOS is not compatible with arm?
CHROMIUM_CPU_VARIANT ?= "kbl"
BIOSNAME ?= "Poky-MrChromebox-SeaBIOS-${CHROMIUM_CPU_VARIANT}"

do_configure(){
    cp ${S}/configs/.config-${CHROMIUM_CPU_VARIANT}-cros ${S}/.config
}

do_compile() {
    unset CPP
    unset CPPFLAGS
    oe_runmake EXTRAVERSION=-${BIOSNAME}-`date +"%Y.%m.%d"`
}


do_compile_append (){

    filename="${BIOSNAME}_`date +"%Y%m%d"`.bin"
    cbfstool ${filename} create -m x86 -s 0x00200000
    cbfstool ${filename} add-payload -f ./out/bios.bin.elf -n payload -b 0x0 -c lzma
    cbfstool ${filename} add -f ./out/vgabios.bin -n vgaroms/seavgabios.bin -t optionrom
    echo "/pci@i0cf8/*@1e,4/drive@0/disk@0\n" > /tmp/bootorder
    cbfstool ${filename} add -f /tmp/bootorder -n bootorder -t raw
    cbfstool ${filename} add-int -i 3000 -n etc/boot-menu-wait
    cbfstool ${filename} print
    md5sum ${filename} > ${filename}.md5
}

do_install() {
    filename="${BIOSNAME}_`date +"%Y%m%d"`.bin"
    oe_runmake
    install -d ${D}/usr/share/firmware
    install -m 0644 ${filename}* ${D}/${datadir}/firmware/
}


DEPENDS += "coreboot-utils-native make-native python3-native"
