.. _dataadmin.oracle:

Working with Oracle data
========================

Boundless Suite can read and publish data from an Oracle Spatial database. Any table that has a valid geometry column can be published as a layer in GeoServer.

Installing Oracle support
-------------------------

Oracle support isn't enabled by default, so it must be separately installed through an extension.

.. include:: ../include/ext_install_links.txt

.. warning:: Because of licensing issues, **an additional file is required to be installed** that is not part of the extension bundle. This file, the Oracle JDBC driver, is included as part of Oracle installations.

Verifying installation
----------------------

.. include:: ../../install/include/ext/oracle_verify.txt

For more information on adding a store and publishing layers, please see the `GeoServer documentation for Oracle <../../geoserver/data/database/oracle.html>`_.

Caveats
-------

Oracle data will be assumed to be point geometries, so new layers will be styled accordingly. Data can be rendered as intended by changing the styling of the layer to use the correct geometry.

.. Add tutorials here
