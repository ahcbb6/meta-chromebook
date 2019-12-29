DESCRIPTION = "(Chromium Fork) Discover & annotate the root device"
LICENSE = "GPLv2"
HOMEPAGE = "https://chromium.googlesource.com/chromiumos/third_party/rootdev/+/refs/heads/master/README.chromium"

LIC_FILES_CHKSUM = "file://LICENSE;md5=562c740877935f40b262db8af30bca36"

SRC_URI = "git://chromium.googlesource.com/chromiumos/third_party/rootdev;branch=${CHROMIUM_THIRDPARTY_BRANCH};protocol=https"

SRCREV ?= "9b7d26a6a54f5f9e02b93a5d5ebf266fd748e06c"

S="${WORKDIR}/git"

inherit pkgconfig

do_install() {
    install -d ${D}/${bindir}
    install -m 755 ${B}/rootdev ${D}/${bindir}/rootdev
    install -d ${D}/${libdir}
    install -m 755 ${B}/librootdev.so.1.0 ${D}/${libdir}/librootdev.so.1.0
}
