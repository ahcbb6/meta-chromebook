# This class is simply a place holder to be able to run anon python properly from
# a MACHINE conf file, due to the following reasons:
#
# qemu-usermode is removed from MACHINE_FEATURES on the tune-skylake.inc file,
# we need to add it back to be able to build all components properly
#
# This class is a temporary solution and should be deleted once meta-intel fixes its tune

python () {
    machft = d.getVar('MACHINE_FEATURES')
    machft += ' qemu-usermode'
    d.delVar('MACHINE_FEATURES')
    d.setVar('MACHINE_FEATURES', machft)
}
