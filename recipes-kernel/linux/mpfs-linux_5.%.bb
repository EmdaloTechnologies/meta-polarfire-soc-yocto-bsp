require recipes-kernel/linux/mpfs-linux-common.inc

LINUX_VERSION ?= "5.15"
KERNEL_VERSION_SANITY_SKIP="1"

BRANCH = "skycorp-linux-5.15-mchp+fpga"
SRCREV="b8c93bc5723dfef0e79ccda03332b9111a02c9b4"
SRC_URI = " \
    git://git@github.com/EmdaloTechnologies/linux.git;protocol=ssh;branch=${BRANCH} \
"

do_assemble_fitimage[depends] += "dt-overlay-mchp:do_deploy"

SRC_URI:append:icicle-kit-es = " file://bsp_cmdline.cfg \
    file://rpi_sense_hat.cfg \
    file://qspi_flash.cfg \
"
SRC_URI:append:icicle-kit-es-amp = " file://bsp_cmdline.cfg \
    file://rpi_sense_hat.cfg \
    file://qspi_flash.cfg \
"

SRC_URI:append:m100pfsevp = "file://m100pfsevp_configs.cfg"

SRC_URI:append:sev-kit-es = " \
    file://bsp_cmdline.cfg \
    file://mpfs-v4l2.cfg \
"

do_deploy:append() {

    if [ -n "${INITRAMFS_IMAGE}" ]; then

        if [ "${INITRAMFS_IMAGE_BUNDLE}" != "1" ]; then
                ln -snf fitImage-${INITRAMFS_IMAGE_NAME}-${KERNEL_FIT_NAME}${KERNEL_FIT_BIN_EXT} "$deployDir/fitImage"
        fi
    fi
}

addtask deploy after do_install

