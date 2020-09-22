KBRANCH ?= "release-R80-12739.B-chromeos-3.18"

require recipes-kernel/linux/linux-chromium.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

# Techincally we should use the 3.19 repo for the yocto kernel cache
SRC_URI_append = " git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-4.19;destsuffix=${KMETA}"

SRCREV_machine ?= "5da699e3897ccf7accfb75e679de1bebaeb08124"
SRCREV_meta = "ad6f8b357720ca8167a090713b7746230cf4b314"


LINUX_VERSION ?= "3.18"

DEPENDS_append_mediatek8173-chromebook = " vboot-utils-chromium-native"

COMPATIBLE_MACHINE = "mediatek8173-chromebook"

# linux-chromium 3.18 is meant only to be supported on mediatek chromebooks for now
# This config should work, but it doesnt for some unknown reason at this point
# /arm64/chromiumos-mediatek.flavour.config

# Use defconfig from arch linux-oak since it works properly
SRC_URI_append_mediatek8173-chromebook = " file://defconfig \
    file://fix_modules.cfg \
"

# Workaround to linux-chromium that expects an environment variable and for some
# reason it doesnt expand it on the sources, then, while running kernel_configcheck
# the python script isnt able to expand it
do_configure_append(){
    sed -i 's/$WIFIVERSION//g' ${S}/drivers/net/Kconfig
    sed -i 's/$WIFIVERSION//g' ${S}/net/Kconfig
}

do_compile_prepend(){
    # Allow GCC9 to build
    cp ${S}/include/linux/compiler-gcc5.h ${S}/include/linux/compiler-gcc9.h
    cp ${S}/include/linux/compiler-gcc5.h ${S}/include/linux/compiler-gcc10.h
    # Fix race condition on dtbs bby creating the dtbs first
    oe_runmake CC="${CC} -Wno-attributes" dtbs
}

# Silence kernel compilation warnings since this is an old kernel
KERNEL_CC_append = " -Wno-attributes -Wno-format-truncation -Wno-stringop-overflow -Wno-attribute-alias -Wno-array-bounds -Wno-unused-const-variable -Wno-packed-not-aligned -Wno-address-of-packed-member -Wno-duplicate-decl-specifier -Wno-sizeof-pointer-memaccess -Wno-int-in-bool-context -Wno-discarded-array-qualifiers -Wno-format-overflow -Wno-tautological-compare -Wno-missing-attributes -Wno-stringop-truncation -Wno-nonnull -Wno-logical-not-parentheses -Wno-bool-operation -Wno-implicit-function-declaration -Wno-misleading-indentation -Wno-switch -Wno-unused-variable -Wno-builtin-declaration-mismatch -Wno-restrict"

# Device tree blobs for mt8173
KERNEL_DEVICETREE = " \
mediatek/mt8173-elm-rev0.dtb \
mediatek/mt8173-elm-rev1.dtb \
mediatek/mt8173-elm-rev3.dtb \
mediatek/mt8173-hana-rev0.dtb \
mediatek/mt8173-oak-rev2.dtb \
mediatek/mt8173-oak-rev3.dtb \
mediatek/mt8173-oak-rev4.dtb \
mediatek/mt8173-oak-rev5.dtb \
mediatek/mt8173-oak-rev6.dtb \
"

# The whole function needs to be replaced since its hardcoded to
# use linux.bin on oe-core
fitimage_assemble() {

    kernelcount=1
    dtbcount=""
    DTBS=""
    ramdiskcount=${3}
    setupcount=""
    rm -f ${1} arch/${ARCH}/boot/${2}

    fitimage_emit_fit_header ${1}

    #
    # Step 1: Prepare a kernel image section.
    #
    fitimage_emit_section_maint ${1} imagestart

    uboot_prep_kimage
    fitimage_emit_section_kernel ${1} "${kernelcount}" arch/arm64/boot/Image "${linux_comp}"

    #
    # Step 2: Prepare a DTB image section
    #

    if [ -z "${EXTERNAL_KERNEL_DEVICETREE}" ] && [ -n "${KERNEL_DEVICETREE}" ]; then
    dtbcount=1
        for DTB in ${KERNEL_DEVICETREE}; do
            if echo ${DTB} | grep -q '/dts/'; then
    bbwarn "${DTB} contains the full path to the the dts file, but only the dtb name should be used."
    DTB=`basename ${DTB} | sed 's,\.dts$,.dtb,g'`
    fi
    DTB_PATH="arch/${ARCH}/boot/dts/${DTB}"
			if [ ! -e "${DTB_PATH}" ]; then
    DTB_PATH="arch/${ARCH}/boot/${DTB}"
    fi

			DTB=$(echo "${DTB}" | tr '/' '_')
    DTBS="${DTBS} ${DTB}"
    fitimage_emit_section_dtb ${1} ${DTB} ${DTB_PATH}
    done
    fi

	if [ -n "${EXTERNAL_KERNEL_DEVICETREE}" ]; then
    dtbcount=1
		for DTBFILE in ${EXTERNAL_KERNEL_DEVICETREE}/*.dtb; do
    DTB=`basename ${DTBFILE}`
    DTB=$(echo "${DTB}" | tr '/' '_')
    DTBS="${DTBS} ${DTB}"
    fitimage_emit_section_dtb ${1} ${DTB} ${DTBFILE}
    done
    fi

	#
    # Step 3: Prepare a setup section. (For x86)
	#
	if [ -e arch/${ARCH}/boot/setup.bin ]; then
    setupcount=1
    fitimage_emit_section_setup ${1} "${setupcount}" arch/${ARCH}/boot/setup.bin
    fi

	#
    # Step 4: Prepare a ramdisk section.
	#
	if [ "x${ramdiskcount}" = "x1" ] ; then
    # Find and use the first initramfs image archive type we find
		for img in cpio.lz4 cpio.lzo cpio.lzma cpio.xz cpio.gz ext2.gz cpio; do
    initramfs_path="${DEPLOY_DIR_IMAGE}/${INITRAMFS_IMAGE_NAME}.${img}"
    echo "Using $initramfs_path"
			if [ -e "${initramfs_path}" ]; then
    fitimage_emit_section_ramdisk ${1} "${ramdiskcount}" "${initramfs_path}"
				break
    fi
    done
    fi

	fitimage_emit_section_maint ${1} sectend

	# Force the first Kernel and DTB in the default config
    kernelcount=1
	if [ -n "${dtbcount}" ]; then
    dtbcount=1
    fi

	#
    # Step 5: Prepare a configurations section
	#
    fitimage_emit_section_maint ${1} confstart

	if [ -n "${DTBS}" ]; then
    i=1
		for DTB in ${DTBS}; do
    dtb_ext=${DTB##*.}
			if [ "${dtb_ext}" = "dtbo" ]; then
	      fitimage_emit_section_config ${1} "" "${DTB}" "" "" "`expr ${i} = ${dtbcount}`"
			else
	      fitimage_emit_section_config ${1} "${kernelcount}" "${DTB}" "${ramdiskcount}" "${setupcount}" "`expr ${i} = ${dtbcount}`"
	      fi
	      i=`expr ${i} + 1`
	      done
	      fi

	fitimage_emit_section_maint ${1} sectend

	fitimage_emit_section_maint ${1} fitend

	#
	      # Step 6: Assemble the image
	      #
	      uboot-mkimage \
	      ${@'-D "${UBOOT_MKIMAGE_DTCOPTS}"' if len('${UBOOT_MKIMAGE_DTCOPTS}') else ''} \
	      -f ${1} \
	      arch/${ARCH}/boot/${2}

	#
	      # Step 7: Sign the image and add public key to U-Boot dtb
	      #
	if [ "x${UBOOT_SIGN_ENABLE}" = "x1" ] ; then
	      add_key_to_u_boot=""
		if [ -n "${UBOOT_DTB_BINARY}" ]; then
	      # The u-boot.dtb is a symlink to UBOOT_DTB_IMAGE, so we need copy
	      # both of them, and don't dereference the symlink.
	      cp -P ${STAGING_DATADIR}/u-boot*.dtb ${B}
	      add_key_to_u_boot="-K ${B}/${UBOOT_DTB_BINARY}"
	      fi
	      uboot-mkimage \
	      ${@'-D "${UBOOT_MKIMAGE_DTCOPTS}"' if len('${UBOOT_MKIMAGE_DTCOPTS}') else ''} \
	      -F -k "${UBOOT_SIGN_KEYDIR}" \
	      $add_key_to_u_boot \
	      -r arch/${ARCH}/boot/${2}
	      fi
}


do_assemble_fitimage_append() {
    # U-boot only understands signed kernel images
    # A fitImage has to be assembled using the kernel
    # and the flattened device tree blobs, and then
    # it has to be signed using the vbutil_kernel
    # command (futility) from chromiums vboot_reference
    dd if=/dev/zero of=bootloader.bin bs=512 count=1
    echo "console=tty1 init=/sbin/init root=PARTUUID=%U/PARTNROFF=1 rootwait rw noinitrd" > cmdline
    vbutil_kernel \
    --pack vmlinux-signed-fitmage.kpart \
    --version 1 \
    --vmlinuz arch/arm64/boot/fitImage \
    --arch aarch64 \
    --keyblock ${RECIPE_SYSROOT_NATIVE}/${datadir}/devkeys/kernel.keyblock \
    --signprivate ${RECIPE_SYSROOT_NATIVE}/${datadir}/devkeys/kernel_data_key.vbprivk \
    --config cmdline \
    --bootloader bootloader.bin
}

# Since we are replacing a function to create the fitImage
# we need the linux.bin file to exist which usually would
kernel_do_deploy_prepend() {
    touch ${B}/linux.bin
}
    
# Append from fitimage
kernel_do_deploy_append(){
    if echo ${KERNEL_IMAGETYPES} | grep -wq "fitImage"; then
    echo "Copying uncompressed Image file..."
    install -m 0644 ${B}/arch/arm64/boot/Image $deployDir/fitImage-img-${KERNEL_FIT_NAME}
    echo "Copying Signed chromium kernel image source file..."
    install -m 0644 ${B}/vmlinux-signed-fitmage.kpart "$deployDir/vmlinux-signed-fitmage.kpart"
    fi
}

# Replace from kernel-uboot to set compression to none
uboot_prep_kimage() {
    vmlinux_path=""
    linux_suffix=""
    linux_comp="none"
}