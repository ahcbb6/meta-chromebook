# Allow modules to be built on linux-chromium 3.18, otherwise all warnings cause errors
KERNEL_CC_append_mediatek8173-chromebook = " -Wno-maybe-uninitialized -Wno-attributes"
