SUMMARY = "Polarfire SoC Linux startup scripts"
DESCRIPTION = "rc.local systemd script \
- includes pppd \ 
"

LICENSE = "CLOSED"

DEPENDS = "systemd"
inherit systemd

SRC_URI = "file://rc.local \
          "

S = "${WORKDIR}"

do_install() {
    install -d ${D}/etc
    install -m 0755 ${S}/rc.local ${D}/etc
}

FILES_${PN} = "/etc/rc.local"

