SUMMARY = "Extremely Fast Compression algorithm"
DESCRIPTION = "LZ4 is a very fast lossless compression algorithm, providing compression speed at 400 MB/s per core, scalable with multi-cores CPU. It also features an extremely fast decoder, with speed in multiple GB/s per core, typically reaching RAM speed limits on multi-core systems."

LICENSE = "BSD | BSD-2-Clause | GPL-2.0"
LIC_FILES_CHKSUM = "file://lib/LICENSE;md5=ebc2ea4814a64de7708f1571904b32cc\
                    file://programs/COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://LICENSE;md5=7f2857d58beff6d04137bf9b09e5ffb6"

PE = "1"

SRCREV = "7bb64ff2b69a9f8367de9ab483cdadf42b4c1b65"

SRC_URI = "git://github.com/lz4/lz4.git \
           file://run-ptest \
"

S = "${WORKDIR}/git"
inherit ptest

EXTRA_OEMAKE = "PREFIX=${prefix} CC='${CC}' DESTDIR=${D} LIBDIR=${libdir} INCLUDEDIR=${includedir}" 

do_install() {
	oe_runmake install
}

do_install_ptest () {
	install -d ${D}${PTEST_PATH}/testsuite
	cp -rf ${S}/* ${D}${PTEST_PATH}/testsuite
}

RDEPENDS_${PN}-ptest += "make python3"

BBCLASSEXTEND = "native nativesdk"
