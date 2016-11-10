.. _dataadmin.jp2k:

Working with JPEG 2000 (JP2K) data
==================================

Boundless Suite supports data saved in `JPEG 2000 <https://jpeg.org/jpeg2000/index.html>`_ format. This image format utilizes wavelet compression for more efficient storage.

This data can be loaded and published through GeoServer.

Installing JPEG 2000 support
-----------------------------

JPEG 2000 support isn't enabled by default, so it must be separately installed through an extension.

.. include:: ../include/ext_install_links.txt

Verifying installation
----------------------

.. include:: ../../install/include/ext/jp2k_verify.txt

For more information on adding a store and publishing layers, please see the `GeoServer documentation for JPEG 2000 <../../geoserver/extensions/jp2k/>`_.

Caveats
-------

JPEG 2000 support is also available through the :ref:`GDAL formats extension <dataadmin.gdal>`. Having both the JPEG 2000 ("Direct") extension installed and GDAL JPEG 2000 support installed at the same time creates an issue with the :ref:`GeoServer Importer extension<dataadmin.importer>`, which is unable to determine which store to use. This is only an issue with Importer; stores can still be created through regular means (`GeoServer UI <../../geoserver/data/webadmin/stores.html>`_ or `REST API <../../geoserver/rest/api/datastores.html>`_).

That said, the GDAL version of JPEG 2000 has better performance from the standard (Java-based) extension.

.. Add tutorials here