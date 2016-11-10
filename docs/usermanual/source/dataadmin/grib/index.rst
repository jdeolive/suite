.. _dataadmin.grib:

Working with GRIB data
======================

Boundless Suite supports data saved in `GRIB <https://en.wikipedia.org/wiki/GRIB>`_ format. GRIB is a data format commonly used in meteorology to store weather data, both historical and predicted.

This data can be loaded and published through GeoServer. Both GRIB 1 and GRIB 2 formats are supported.

.. note:: :ref:`NetCDF <dataadmin.netcdf>` is also supported via a separate extension. 

Installing GRIB support
-----------------------

GRIB support isn't enabled by default, so it must be separately installed through an extension.

.. include:: ../include/ext_install_links.txt

Verifying installation
----------------------

.. include:: ../../install/include/ext/grib_verify.txt

For more information on adding a store and publishing layers, please see the `GeoServer documentation for GRIB <../../geoserver/extensions/grib/grib.html>`_.

Caveats
-------

Because of its relation to NetCDF, installing the GRIB extension will also add support for the :ref:`NetCDF <dataadmin.netcdf>` store as well.

GRIB2 files on a latitude/longitude grid (GDS template 0) now have longitudes in the range (0, 360). Previous releases presented these longitudes as being in the range (-180, 180).

.. Add tutorials here
