.. _install.windows.tomcat.geoserver.data:

GeoServer Data Directory
========================

.. note:: If you have an existing GeoServer, follow the "Upgrading" instructions below.

New installations
-----------------

#. Extract the contents of the sample GeoServer data directory (included in the Boundless WAR bundle as :file:`suite-data-dir.zip`) into :file:`C:\\ProgramData\\Boundless\\geoserver\\data`. You can right-click the file and select :menuselection:`Extract All`.

   .. figure:: ../img/data_extract_all.png
      
      Extract all

   .. figure:: ../img/data_extract_destination.png
      
      Extract destination

#. The new data directory is now ready for use with Boundless Suite.

   .. note:: This location will sometimes be referred to as the ``GEOSERVER_DATA_DIRECTORY`` or ``GEOSERVER_DATA_DIR``.

   .. figure:: ../img/data_default.png
      
      New data directory

Upgrading
---------

.. warning:: Please uninstall previous releases before proceeding, and be sure to backup your data directory. For more information see :ref:`install.windows.tomcat.before`.

When upgrading, you will want to make use of your existing data directory:

* OpenGeo Suite 4.8 and earlier stored the geoServer data directory in :file:`C:\\ProgramData\\Boundless\\OpenGeo\\geoserver`. This can be used as-is. The directory can be moved to :file:`C:\\ProgramData\\Boundless\\geoserver\\data`, which is the expected location for Boundless Suite. If you take this step please review and correct any file references that may have changed.

* Boundless Suite 4.9 and later data directories can be used as-is, with no modifications. This directory is typically found at :file:`C:\\ProgramData\\Boundless\\geoserver\\data`.

