.. _dataadmin.mrsid:

Enabling MrSID image support
============================

Boundless Suite comes with support for publishing data from many formats supported by the `Geospatial Data Abstraction Library <http://gdal.org>`_ (GDAL). These formats include DTED, EHdr, AIG, ENVIHdr, and much more.

`MrSID <http://www.gdal.org/frmt_mrsid.html>`_ is available as an optional extension as well, but due to licensing issues, it is not enabled by default. The steps to enable support will differ depending on how Boundless Suite is installed, and on what platform.

Find the method of installation below and continue there.

Installation
------------

Installation instructions are dependent on your operating system and method of install:

* **Tomcat**: :ref:`Ubuntu <install.ubuntu.tomcat.geoserver.binary>`, :ref:`Red Hat <install.redhat.tomcat.geoserver.binary>`, :ref:`Windows <install.windows.tomcat.extensions.gdal>`
* **Packages**: Install the ``gdal-mrsid`` package: :ref:`Ubuntu <install.ubuntu.packages.list>`, :ref:`Red Hat <install.redhat.packages.list>` 

.. note:: The Boundless Suite virtual machine has most extensions pre-installed.


Verification
------------

#. To verify that the MrSID extension was installed properly, navigate to the GeoServer web admin interface and log in with administrator credentials.

#. Click on :guilabel:`Stores` and then :guilabel:`Add new Store`. There should be a :guilabel:`MrSID` option under :guilabel:`Raster Data Formats`.

   .. figure:: img/mrsid_verify.png
      
      Verifying that MrSID is an option in the Raster Data Sources

