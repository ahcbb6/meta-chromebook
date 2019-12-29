# This is apparently only needed on linux-chromium
# perhaps in the future it can be set as a DISTROOVERRIDE
KERNEL_CC_append_mediatek8173-chromebook = " -Wno-error=missing-attributes -Wno-attributes"
