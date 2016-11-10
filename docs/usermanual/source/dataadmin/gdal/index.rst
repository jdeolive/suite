.. _dataadmin.gdal:

Enabling GDAL image formats support
===================================

.. note:: This document only concerns the extra image formats that can be made available in GeoServer via GDAL. The GDAL binaries, such as :command:`gdalinfo` are installed separately.

Boundless Suite comes with support for publishing data from many formats supported by the `Geospatial Data Abstraction Library <http://gdal.org>`_ (GDAL).  These formats include DTED, EHdr, AIG, ENVIHdr, and much more.

.. note:: See the :ref:`install` section for more information on the various ways to install Boundless Suite.

Installation
------------

Installation instructions are dependent on your operating system and method of install:

* **Tomcat**: :ref:`Ubuntu <install.ubuntu.tomcat.geoserver.binary>`, :ref:`Red Hat <install.redhat.tomcat.geoserver.binary>`, :ref:`Windows <install.windows.tomcat.extensions.gdal>`
* **Packages**: Install the ``suite-gs-gdal`` package: :ref:`Ubuntu <install.ubuntu.packages.list>`, :ref:`Red Hat <install.redhat.packages.list>` 

.. note:: The Boundless Suite virtual machine has most extensions pre-installed.

.. _dataadmin.gdal.verify:

Verification
------------

.. include:: /install/include/ext/gdal_verify.txt
