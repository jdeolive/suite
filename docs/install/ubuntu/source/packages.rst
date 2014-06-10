.. _installation.ubuntu.packages:

Ubuntu Linux packages
=====================

OpenGeo Suite for Ubuntu is broken up into a number of discrete packages. This section describes all of the available packages.

The packages are managed through the standard package management system for Ubuntu called `APT <https://help.ubuntu.com/community/AptGet/Howto>`_. All packages can be installed with the following command::

  sudo apt-get install <package>

where ``<package>`` is any one of the package names listed below.

Top level packages
------------------

The following packages are considered "meta" packages that only depend on other upstream packages, and contain no libraries or binaries themselves.

.. list-table::
   :header-rows: 1
   :widths: 20 40 40
   :class: table-leftwise

   * - Package
     - Description
     - Dependencies
   * - ``opengeo``
     - Top level package, installs everything
     - ``opengeo-server`` ``opengeo-server`` 
   * - ``opengeo-server``
     - Installs all server packages
     - ``postgis21-postgresql93`` ``geoserver`` ``geowebcache`` ``geoexplorer`` ``opengeo-dashboard`` ``opengeo-docs`` ``opengeo-tomcat`` 
   * - ``opengeo-client``
     - Installs all client packages
     - ``postgis21`` ``pgadmin3`` ``pdal``

Server packages
---------------

The following packages contain the server components of OpenGeo Suite. 

.. list-table::
   :header-rows: 1
   :widths: 20 40 40
   :class: table-leftwise

   * - Package
     - Description
     - Dependencies
   * - ``postgresql-9.3-postgis-2.1``
     - PostGIS 2.1 extensions for PostgreSQL 9.3 
     - ``postgis-2.1`` ``postgresql-9.3``
   * - ``postgresql-9.3-pointcloud``
     - Point cloud extensions for PostgreSQL 9.3 
     - ``postgresql93`` ``libght``
   * - ``geoserver``
     - GeoServer geospatial data server
     - 
   * - ``geowebcache``
     - GeoWebCache tile caching server
     - 
   * - ``geoexplorer``
     - GeoExplorer map composing application
     - 
   * - ``opengeo-dashboard``
     - OpenGeo Suite dashboard
     - 
   * - ``opengeo-docs``
     - OpenGeo Suite documentation
     - 
   * - ``opengeo-tomcat6``
     - OpenGeo Suite webapps for Tomcat 6
     - ``tomcat6``

Client/library packages
-----------------------

The following packages contain the client components of OpenGeo Suite.

.. list-table::
   :header-rows: 1
   :widths: 20 40 40
   :class: table-leftwise

   * - Package
     - Description
     - Dependencies
   * - ``postgis-2.1``
     - PostGIS 2.1 userland binaries and libraries
     - ``libpq5`` ``geos`` ``proj`` ``gdal``
   * - ``pgadmin3``
     - pgAdmin database manager for PostgreSQL
     - ``wxGTK``
   * - ``libght``
     - GeoHash Tree library for point cloud data 
     - 
   * - ``pdal``
     - Point Cloud format library
     - ``libgeotiff`` ``laszip`` ``gdal`` ``geos`` ``postgresql93-libs``
   * - ``gdal``
     - GDAL/OGR format library
     - ``proj`` ``geos`` ``postgresql93-libs``

GeoServer add-ons
-----------------

The following packages add additional functionality to GeoServer. After installing any of these packages, you will need to restart Tomcat:

.. code-block:: console

   sudo service tomcat6 restart

.. list-table::
   :header-rows: 1
   :widths: 20 40 40
   :class: table-leftwise

   * - Package
     - Description
     - Dependencies
   * - ``geoserver-mapmeter``
     - Mapmeter extension to GeoServer (see http://mapmeter.com)
     - ``geoserver``
   * - ``geoserver-cluster``
     - Clustering extension for GeoServer
     - ``geoserver``
   * - ``geoserver-jdbcconfig``
     - Database catalog and configuration extension
     - ``geoserver``
   * - ``geoserver-css``
     - CSS styling extension for GeoServer
     - ``geoserver``
   * - ``geoserver-csw``
     - Catalogue Service for Web (CSW) extension for GeoServer
     - ``geoserver``
   * - ``geoserver-wps``
     - Web Processing Service (WPS) extension for GeoServer
     - ``geoserver``
   * - ``geoserver-script``
     - Scripting extension for GeoServer
     - ``geoserver``

