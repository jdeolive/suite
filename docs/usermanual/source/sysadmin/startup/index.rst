.. _sysadmin.startup:

How to add startup parameters for GeoServer
===========================================

GeoServer allows global configuration settings to be provided as Java system properties for use during the startup process. Java system properties can be supplied on the command line using ``-D`` and are of one of the following forms:

* ``-Dproperty=value``
* ``-D property=value``

This section shows how to set the Java system properties used during startup.

.. note:: We recommend using Tomcat for deployment. Other application servers may have similar configuration.

.. note:: You can view existing Java options (:guilabel:`system-properties`) and environment variables (:guilabel:`system-environment`) on the GeoServer Detailed Status Page at http://localhost:8080/geoserver/rest/about/status.


Linux packages
--------------

To set Java options, create a file in the :file:`/etc/tomcat8/suite-opts` directory.  

For example, to revert to the legacy handling of CRS values in GeoJSON WFS output:

#. Create the file :file:`legacyCRS` in :file:`/etc/tomcat8/suite-opts`

#. Add the line :guilabel:`-DGEOSERVER_GEOJSON_LEGACY_CRS=true`

#. Save the file and restart Tomcat.

Windows Tomcat
--------------

To set Java options, use the Windows Tomcat Configuration Manager (see :ref:`install.windows.tomcat`), :guilabel:`Java` Tab, :guilabel:`Java Options` section.

For example, to revert to the legacy handling of CRS values in GeoJSON WFS output:

#. Open the Windows Tomcat Configuration Manager and go to the :guilabel:`Java` Tab.

#. In the :guilabel:`Java Options` add :guilabel:`-DGEOSERVER_GEOJSON_LEGACY_CRS=true`, then click :guilabel:`Apply`.

    .. figure:: /sysadmin/startup/img/win_tomcat_add_java_opt.png

#. Stop and Start Tomcat (via the :guilabel:`General` tab).

