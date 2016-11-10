.. _dataadmin.mbtiles:

Working with MBTiles data
=========================

Boundless Suite supports data saved in `MBTiles <https://www.mapbox.com/help/an-open-platform/#mbtiles>`_ format, which is an efficient format for storing millions of tiles in a single SQLite database.

This data can be loaded and published through GeoServer.

Installing MBTiles support
--------------------------

MBTiles support isn't enabled by default, so it must be separately installed through an extension.

.. include:: ../include/ext_install_links.txt

Verifying installation
----------------------

.. include:: ../../install/include/ext/mbtiles_verify.txt

For more information on adding a store and publishing layers, please see the `GeoServer documentation for MBTiles <../../geoserver/community/mbtiles/>`_.

Caveats
-------

MBTiles requires the installation of Web Processing Service (WPS) support. Failure to install WPS support will result in GeoServer being unable to deploy.

If you do not need WPS functionality, we recommend that you disable WPS in GeoServer. You can do this in GeoServer by clicking :guilabel:`WPS` under :guilabel:`Services` and unchecking :guilabel:`Enable WPS`, then clicking :guilabel:`Submit`.

.. Add tutorials here