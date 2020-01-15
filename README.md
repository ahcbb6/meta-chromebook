## Chromebook layer compatible with OpenEmbedded/Yocto Project


Chromebooks are widely used nowadays, providing quality hardware mostly
used for lightweight workloads.

While ChromeOS provides many interesting capabilities, it simply does not
match a Linux Operating System when it comes to be used as a dev environment,
it also does not mean a product on "chromebook HW" cannot be based on pure
Linux either.

This layer allows you to build a Linux distribution for Chromebook devices
using the OpenEmbedded Infrastructure.


Please see the corresponding sections below for details.


### Build Status

**Master**

|Kernel   	| Pixelbook  	|X86 Chromebook   	|Mediatek 8173 Chromebook   	|
|---	|---	|---	|---	|
| Linux Yocto	|[![Build Status](https://dev.azure.com/aehs29/meta-chromebook/_apis/build/status/eve/eve-yocto?branchName=master)](https://dev.azure.com/aehs29/meta-chromebook/_build/latest?definitionId=14&branchName=master)|[![Build Status](https://dev.azure.com/aehs29/meta-chromebook/_apis/build/status/x86/x86-yocto?branchName=master)](https://dev.azure.com/aehs29/meta-chromebook/_build/latest?definitionId=19&branchName=master)| N/A |
| Linux Intel   |[![Build Status](https://dev.azure.com/aehs29/meta-chromebook/_apis/build/status/eve/eve-intel?branchName=master)](https://dev.azure.com/aehs29/meta-chromebook/_build/latest?definitionId=15&branchName=master)|[![Build Status](https://dev.azure.com/aehs29/meta-chromebook/_apis/build/status/x86/x86-intel?branchName=master)](https://dev.azure.com/aehs29/meta-chromebook/_build/latest?definitionId=20&branchName=master)| N/A |
| Linux Chromium   	|[![Build Status](https://dev.azure.com/aehs29/meta-chromebook/_apis/build/status/eve/eve-chromium?branchName=master)](https://dev.azure.com/aehs29/meta-chromebook/_build/latest?definitionId=16&branchName=master)|[![Build Status](https://dev.azure.com/aehs29/meta-chromebook/_apis/build/status/x86/x86-chromium?branchName=master)](https://dev.azure.com/aehs29/meta-chromebook/_build/latest?definitionId=21&branchName=master)|[![Build Status](https://dev.azure.com/aehs29/meta-chromebook/_apis/build/status/arm/mediatek8173?branchName=master)](https://dev.azure.com/aehs29/meta-chromebook/_build/latest?definitionId=28&branchName=master)|

**Zeus**

|Kernel   	| Pixelbook  	|X86 Chromebook   	|
|---	|---	|---	|
| Linux Yocto	|[![Build Status](https://dev.azure.com/aehs29/meta-chromebook/_apis/build/status/eve/eve-yocto?branchName=zeus)](https://dev.azure.com/aehs29/meta-chromebook/_build/latest?definitionId=14&branchName=zeus)|[![Build Status](https://dev.azure.com/aehs29/meta-chromebook/_apis/build/status/x86/x86-yocto?branchName=zeus)](https://dev.azure.com/aehs29/meta-chromebook/_build/latest?definitionId=19&branchName=zeus)|
| Linux Intel   |[![Build Status](https://dev.azure.com/aehs29/meta-chromebook/_apis/build/status/eve/eve-intel?branchName=zeus)](https://dev.azure.com/aehs29/meta-chromebook/_build/latest?definitionId=15&branchName=zeus)|[![Build Status](https://dev.azure.com/aehs29/meta-chromebook/_apis/build/status/x86/x86-intel?branchName=zeus)](https://dev.azure.com/aehs29/meta-chromebook/_build/latest?definitionId=20&branchName=zeus)|
| Linux Chromium   	|[![Build Status](https://dev.azure.com/aehs29/meta-chromebook/_apis/build/status/eve/eve-chromium?branchName=zeus)](https://dev.azure.com/aehs29/meta-chromebook/_build/latest?definitionId=16&branchName=zeus)|[![Build Status](https://dev.azure.com/aehs29/meta-chromebook/_apis/build/status/x86/x86-chromium?branchName=zeus)](https://dev.azure.com/aehs29/meta-chromebook/_build/latest?definitionId=21&branchName=zeus)|



Table of Contents
=============================================

### [I. What is this layer for?](https://github.com/aehs29/meta-chromebook/tree/master#i-what-is-this-layer-for)
### [II. Layer Dependencies](https://github.com/aehs29/meta-chromebook/tree/master#ii-dependencies)
### [III. Usage](https://github.com/aehs29/meta-chromebook/tree/master#iii-usage)
### [IV. Submitting Patches](https://github.com/aehs29/meta-chromebook/tree/master#iv-submitting-patches)



I. What is this layer for?
=============================================

This layer allows users to build a customized Linux Distribution
for Chromebook devices.

The layer contains:

#### MACHINEs (BSPs):
- **X86 based Chromebooks**
- **ARM64 (Mediatek 8173) based Chromebooks** See [README.ARM](https://github.com/aehs29/meta-chromebook/blob/master/README.ARM.md) for details.

- **Pixelbook (Chromebook EVE)** * A specific tune for Skylake devices is added since the tune coming
   from meta-intel uses a very old TUNE at this point (Nehalem).

#### Kernels:
 - Images work properly with **Linux-Yocto**, **Linux-Chromium** and **Linux-Intel**, and are updated
 to their latest version (to the day this was written).
 
To change the default setting adding the following to your *local.conf* should suffice, e.g.:
```bash
PREFERRED_PROVIDER_virtual/kernel = "linux-chromium"
PREFERRED_VERSION_linux-chromium = "4.19.%"
```

#### Images:
* **chromebook-image-minimal:** Console image with network, VPN and firmware update capabilities
* **chromebook-image-xfce:** A full image, capable to boot using a graphics environment (XFCE),
   by definition it contains more cmdline utilities, network utilities, and its meant to be
   used mostly as a dev environment, it also contains python, vim and obviously the CHROMIUM web browser.

#### Chromebook Specific Recipes:
- **flashrom**
- **futility**
- **vbutil**
- **rootdev**
- **crossystem**
- **cbfstool**
- **Seabios (MrChromeBox Fork)**
- **MrChromeBox Firmware Utility Scripts**

Which can be used to update the firmware of chromebook devices and such.


#### Tweaks:
 - An extra user is used on both images (chronospoky)
 - Keymaps: a keymap is provided both for console en X11 to be used on Chromebooks
   * xkeyboard-config already provides a chromebook model, but cant be used without X11
   * A chromebook keymap is provided to be used on the console without X11
   * A pixelbook-eve keymap model is added to the xkeyboard-config to allow the pixelbook
     keyboard to work properly, since its keyboard is a little different than most chromebooks.
 - Touchpad: libinput is used for both the touchpad and touchscreen devices, a few
   customizations are added both for Chromebooks in general and the Pixelbook


II. Dependencies
=============================================

        OE-core (meta)
        meta-poky
        meta-intel
        meta-oe
        meta-python
        meta-networking


For a full GUI image using xfce and featuring the chromium browser, the following layers
are also required:

        meta-multimedia
        meta-gnome
        meta-xfce
        meta-browser
        meta-clang


III. Usage
=============================================
1.- Clone the required repositories
```bash
$ git clone https://git.yoctoproject.org/git/poky -b zeus
$ cd poky
$ git clone https://git.yoctoproject.org/git/meta-intel -b zeus
$ git clone https://git.openembedded.org/meta-openembedded -b zeus
$ git clone https://github.com/aehs29/meta-chromebook.git -b zeus
```
2.- Add layers to your *bblayers.conf*:
```bash
$ source oe-init-build-env
$ bitbake-layers add-layer ../meta-intel
$ bitbake-layers add-layer ../meta-openembedded/meta-oe
$ bitbake-layers add-layer ../meta-openembedded/meta-python
$ bitbake-layers add-layer ../meta-openembedded/meta-networking
$ bitbake-layers add-layer ../meta-openembedded/meta-chromebook
```
3.- Add the required variables to your local.conf
```bash
$ echo "MACHINE = \"eve-chromebook\"" >> ./conf/local.conf
```
4.- Build an image
```bash
$ bitbake chromebook-image-minimal
```
5.- Flash the image to a storage device
```bash
$ dd if=tmp/deploy/images/eve-chromebook/chromebook-image-minimal-eve-chromebook.wic of=/dev/<your-dev>
```
 
Boot from USB (it is assumed the BIOS has been flashed to allow legacy boot), for more information
on upgrading Coreboot or SeaBIOS please see: https://mrchromebox.tech/#chromeos

>Seabios can be built from source as well from this layer and installed via an RPM, which will install
it on /usr/share/firmware/ and the necessary tools to flash it are also provided (flashrom).

6.- Login as chronospoky (Reference to the chronos user used by Chromebooks running Chrome/Chromium OS)
```bash
Poky (Yocto Project Reference Distro) 3.0 eve-chromebook /dev/ttyS0
eve-chromebook login: chronospoky
```

***
#### Mix it up
To use a different kernel:
```bash
$ echo "PREFERRED_PROVIDER_virtual/kernel = \"linux-chromium\"" >> ./conf/local.conf
```
or
```bash
$ echo "PREFERRED_PROVIDER_virtual/kernel = \"linux-intel\"" >> ./conf/local.conf
```
To use a different MACHINE:
```bash
$ echo "MACHINE = \"x86-chromebook\"" >> ./conf/local.conf
```
To build a full GUI Linux Image which includes XFCE and Chromium (Repeat step 1 and 2 to add extra required layers: meta-clang, meta-browser, meta-xfce, etc. see [II. Layer Dependencies](https://github.com/aehs29/meta-chromebook/tree/master#ii-dependencies).):
```bash
$ bitbake chromebook-image-xfce
```
and
```bash
$ dd if=tmp/deploy/images/eve-chromebook/chromebook-image-xfce-eve-chromebook.wic of=/dev/<your-dev>
```

IV. Submitting Patches
=============================================

Please submit any patches against the meta-chromebook layer to the github repo https://github.com/aehs29/meta-chromebook
and cc: the maintainer: aehs29 at gmail
