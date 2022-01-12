require recipes-kernel/linux/mpfs-linux-common.inc

LINUX_VERSION ?= "5.12.1"
KERNEL_VERSION_SANITY_SKIP="1"

SRCREV="c9869ee80e3c0d5dd21f34fe32bd9339502d7618"

BRANCH = "mpfs-linux-5.12.x"

SRC_URI = " \
    git://git@bitbucket.microchip.com/fpga_pfsoc_es/linux.git;branch=${BRANCH};protocol=ssh \
"

SRC_URI_append_icicle-kit-es = " \
    file://icicle-kit-es-microchip.dts \
    file://0001-v1-uio-can-clear-interrupt-in-kernel-not-user-space.patch \
    file://0002-v1-gpio-irq-fix.patch \
    file://0003-v2-gpio-bit31-fix.patch \
    file://0004-v2-uio-two-devices.patch \
 "

SRC_URI_append_icicle-kit-es-amp = " \
    file://icicle-kit-es-microchip-context-a.dts \
 "

do_configure_prepend_icicle-kit-es() {
    cp -f ${WORKDIR}/icicle-kit-es-microchip.dts ${S}/arch/riscv/boot/dts/microchip/microchip-mpfs-icicle-kit.dts
}

do_configure_prepend_icicle-kit-es-amp() {
    cp -f ${WORKDIR}/icicle-kit-es-microchip-context-a.dts ${S}/arch/riscv/boot/dts/microchip/microchip-mpfs-icicle-kit-context-a.dts
}

SRC_URI_append_icicle-kit-es = " file://defconfig"
SRC_URI_append_icicle-kit-es-amp = " file://defconfig"




