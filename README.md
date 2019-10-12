## Chromebook BSP layer for OpenEmbedded/Yocto
========================================================================

Chromebooks are widely used nowadays, providing quality hardware mostly
used for lightweight workloads.

While ChromeOS provides many interesting capabilities, it simply does not
match a Linux Operating System when it comes to be used as a dev environment,
it also does not mean a product on "chromebook HW" cannot be based on pure
Linux either.

This layer allows you to build a Linux distribution for Chromebook devices
using the OpenEmbedded Infrastructure.


Please see the corresponding sections below for details.


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

The layer contains MACHINEs (BSPs) for:
* **X86 based Chromebooks**
* **ARM64 based Chromebooks**
* **Pixelbook (EVE)**
   * A specific tune for Skylake devices is added since the tune coming
   from meta-intel uses a very old TUNE at this point (Nehalem).

Specific Chromebook recipes for:
* **flashrom**
* **gbb_utility**
* **rootdev**
* **crossystem**
* **cbfstool**
* **Seabios (MrChromeBox)**
* **MrChromeBox Firmware Utility Scripts**

**Kernel:**
 - Images work properly on both **linux-yocto** and **linux-intel**, and are updated
 to their latest version.
 - **linux-chromium** Can also be used, although its only been tested on Chromebook EVE
 and it is currently using the latest upstream version (4.19).
 To use it, setting:
```
PREFERRED_PROVIDER_virtual/kernel = "linux-chromium"
PREFERRED_VERSION_linux-chromium = "4.19.%"
```
Should suffice.


**Images:**
* **chromebook-image-minimal:** Console image with network and firmware update capabilities
* **chromebook-image-xfce:** A full image, capable to boot using a graphics environment (XFCE),
   by definition it contains more cmdline utilities, network utilities, and it meant to be
   used mostly as a dev environment, it also contains python, vim and obviously CHROMIUM.

Tweaks:
 - An extra user is used on both images (chronospoky)
 - Keymaps: a keymap is provided both for console en X11 to be used on Chromebooks
   * xkeyboard-config already provides a chromebook model, but cant be used without X11
   * A chromebook keymap is provided to be used on the console without X11
   * A pixelbook-eve keymap model is added to the xkeyboard-config to allow thep pixelbook
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

Assumptions:
- All the layers mentioned above have been cloned already

Source the OE environment
 ```bash
$ source oe-init-build-env
 ```
Run 'bitbake-layers add-layer meta-chromebook' (This process is repeated for each of the above layers)
```bash
$ bitbake-layers add-layer meta-chromebook
 ```
Create an image (e.g.)
```bash
$ MACHINE=eve-chromebook bitbake chromebook-image-xfce
 ```
or
```bash
$ MACHINE=x86-chromebook bitbake chromebook-image-minimal
 ```
Flash the image to a storage device
```bash
dd if=tmp/deploy/images/eve-chromebook/pixelbook-image-xfce-eve-chromebook.wic of=/dev/<your-dev>
 ```
Boot from USB (it is assumed the BIOS has been flashed to allow legacy boot), for more information
on upgrading Coreboot or SeaBIOS please see: https://mrchromebox.tech/#chromeos


Seabios can be built from source as well from this layer and installed via an RPM, which will install
it on /usr/share/firmware/ and the necessary tools to flash it are also provided (flashrom).


IV. Submitting Patches
=============================================

Please submit any patches against the meta-chromebook layer to the github repo https://github.com/aehs29/meta-chromebook
and cc: the maintainer: aehs29 at gmail
