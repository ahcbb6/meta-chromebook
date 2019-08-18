DESCRIPTION = "(Chromium Fork) Discover & annotate the root device"
LICENSE = "GPLv2"
HOMEPAGE = "TODO"

LIC_FILES_CHKSUM = "file://LICENSE;md5=562c740877935f40b262db8af30bca36"

SRC_URI = "git://chromium.googlesource.com/chromiumos/third_party/rootdev;branch=factory-eve-9667.B;protocol=https \
    file://provide_glibc227.patch \
"

SRCREV = "b9b8f1de0ab9d4b0e1739d7c0dc4ec232b85a5c3"

S="${WORKDIR}/git"

inherit pkgconfig

do_install() {
    install -d ${D}/${bindir}
    install -m 755 ${B}/rootdev ${D}/${bindir}/rootdev
    install -d ${D}/${libdir}
    install -m 755 ${B}/librootdev.so.1.0 ${D}/${libdir}/librootdev.so.1.0
}

CFLAGS_append = " -Wno-implicit-function-declaration -Wno-deprecated-declarations"
