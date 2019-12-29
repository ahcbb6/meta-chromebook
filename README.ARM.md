## Specific instructions for ARM chromebooks

At this point the only supported architecture is mediatek 8173 used by the
**mediatek8173-chromebook** machine.conf

For this architecture **(armv8; cortexa72 + cortexa53)** chromium OS uses a
64 bit kernel with a 32 bit userspace, since this is supported by the
architecture itself, but on this layer a 64 bit kernel and a 64 bit userspace
are used, at this point I see no reason why a 32 bit userspace is needed
but in any case, a multiconfig implementation can be used to support
that in the future if required.

The hardware that has been tested is an **ARM chromebook**:

**board = hana**

**baseboard = oak**

And it has been tested with a **linux-chromium 3.18** on the latest chromium
kernel release.


There's several chromebooks that use this same configuration, see
[developer-information-for-chrome-os-devices](https://www.chromium.org/chromium-os/developer-information-for-chrome-os-devices)
for more information.


I. How to build
=============================================
1.- Add the required variables to your local.conf
```bash
$ echo "MACHINE = \"mediatek8173-chromebook\"" >> ./conf/local.conf
```
2.- Build an image
```bash
$ bitbake chromebook-image-xfce
```

II. How to flash
=============================================
ARM Chromebooks require to boot through U-Boot by using a signed kernel image, there are
several ways to do that, in this case we'll use a self-signed fitImage, which bundles the
kernel along with the required device tree blobs, then signed during the build and it can
be found inside the deploy directory:
```bash
tmp/deploy/images/mediatek8173-chromebook/vmlinux-signed-fitmage.kpart
```
1.- Create partition table:

An SD card will be used in this case (hence mmcblk1 vs sdX)
```bash
$ fdisk /dev/mmcblk1
```
Type **g** to create a GPT partition table

Type **w** to write changes to the device

2.- Partition device - kernel partition:
```bash
$ cgpt create /dev/mmcblk1
$ cgpt add -i 1 -t kernel -b 8192 -s 65536 -l Kernel -S 1 -T 5 -P 10 /dev/mmcblk1
```
This creates a partition where the kernel will be flashed to.

3.- Partition device - rootfs partition:
```bash
$ cgpt show /dev/mmcblk1
```
Look at the number on the "Sec GPT table" section, e.g. **15633375**
```bash
$ cgpt add -i 2 -t data -b 73728 -s `expr 15633375 - 73728` -l Root /dev/mmcblk1
$ partx -a /dev/mmcblk1
$ mkfs.ext4 /dev/mmcblk1p2
```
4.- Flash the kernel to its respective partition:
```bash
$ dd if=tmp/deploy/images/mediatek8173-chromebook/vmlinux-signed-fitmage.kpart of=/dev/mmcblkp1
$ sync
```
5.- Extract the rootfs tar file on the rootfs partition:
```bash
$ mkdir /tmp/rootfs
$ mount /dev/mmcblk1p2 /tmp/rootfs
$ tar -xf if=tmp/deploy/images/mediatek8173-chromebook/chromebook-image-xfce.tar.gz -C /tmp/rootfs/
$ umount /dev/mmcblk1p2
$ sync
```
I. How to boot
=============================================
To make the Chromebook boot from external media Press **Ctrl + U** on the Chromebook splash screen during boot.
