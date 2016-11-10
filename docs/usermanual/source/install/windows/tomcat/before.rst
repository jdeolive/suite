.. _install.windows.tomcat.before:

Before you start
================

If you are upgrading from a previous version, please be aware that you must **locate and backup the data directory and remove prior versions of the application** first.

.. include:: include/sysreq.txt

Upgrading from OpenGeo Suite 4.8 or earlier
-------------------------------------------

.. warning:: PostGIS is not available with the application server-based installation of Boundless Suite. These installation instructions do not cover the migration of PostGIS databases at this time.

#. Please make a backup of all your data and configuration prior to upgrading.

#. Shutdown all OpenGeo Suite services by navigating to :menuselection:`Start --> All Programs --> OpenGeo Suite` and using the :guilabel:`Stop` shortcuts. 

#. Navigate to your existing GeoServer data directory located inside :file:`C:\\ProgramData\\Boundless\\OpenGeo\\`.

   .. note:: The :file:`C:\\ProgramData` directory is hidden, but accessible.

#. Create an archive of the :file:`geoserver` folder. You can do this by selecting the :file:`geoserver` folder and right click to :menuselection:`Send to --> Compressed (zipped) folder` action.

#. Backup this archive to a safe location.

#. Navigate to :menuselection:`Start --> Programs --> OpenGeo Suite --> Uninstall`.

   .. note:: Uninstallation is also available via the standard Windows program removal workflow (**Programs and Features** Control Panel entry.)

#. Uninstalling will not delete your settings and data, which by default is located at :file:`C:\\ProgramData\\Boundless\\OpenGeo`

   .. note:: Your existing data directory can be used with Boundless Suite as-is. The default location will be at :file:`C:\\ProgramData\\Boundless\\geoserver\\data`, though this can be change. If you copy your existing data directory to this new directory, please review and correct any references to spatial data stored in your data directory.

Upgrading from Boundless Suite 4.9 and above
--------------------------------------------

When upgrading from a **prior version**, your GeoServer data directory can be used as-is, without migration. The configuration files will be updated in place the first time GeoServer runs.

However, we advise making a backup of all your data and configuration prior to upgrading:

#. Using :guilabel:`Windows Explorer` navigate to your Boundless Suite data directory (usually :file:`C:\\ProgramData\\Boundless\\geoserver`) to show your :file:`data` directory, which is the GeoServer data directory.

   .. figure:: img/upgrade_data.png
      
      Boundless Suite data directory

#. Select the :file:`data` folder and right click to :menuselection:`Send to --> Compressed (zipped) folder`.

   .. figure:: img/upgrade_compressed.png
      
      Compressed (zipped) folder

#. Confirm the file name for your new backup and store this file in a safe location.

   .. figure:: img/upgrade_backup.png
      
      Backup data directory
