SUMMARY = "Polarfire SoC Linux example applications"
DESCRIPTION = "Linux Example applications, includes the following \
-iiohttpserver collects ADC measurements and displays them via a webserver.\
-LSRAM read and write example.\
-UIO DMA interrupt example. \ 
"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=06ec214e9fafe6d4515883d77674a453"

DEPENDS = "collectd"
inherit systemd



PV = "1.0+git${SRCPV}"
BRANCH = "master"
SRCREV="${AUTOREV}"
SRC_URI = "git://github.com/EmdaloTechnologies/polarfire-soc-linux-examples.git;branch=${BRANCH} \
          "
S = "${WORKDIR}/git"

do_compile() {
  :
}

INSANE_SKIP_${PN} += "file-rdeps"

do_install() {
    install -d ${D}/opt/microchip
    chmod a+x ${D}/opt/microchip

    cp -rfd ${S}/* ${D}/opt/microchip/
    ## Symbolic Link for iiohttpserver
    lnr ${D}/opt/microchip/ethernet/iio-http-server ${D}/opt/microchip/iiohttpserver
    
    rm -f ${D}/opt/microchip/.git
    rm -f ${D}/opt/microchip/Jenkinsfile
    ## Install the iio-http-server
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/ethernet/iio-http-server/collection/collectdiio.service ${D}${systemd_unitdir}/system
}

SYSTEMD_SERVICE_${PN} = "collectdiio.service"
FILES_${PN} += "/opt/microchip/"

