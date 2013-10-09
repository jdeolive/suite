.. _installation.linux.redhat.packages:

OpenGeo Suite RPM Packages
==========================

OpenGeo Suite for Red Hat distributions is broken up into a number of discrete
packages. This section describes all of the available packages.

Top Level Packages
------------------

The following packages are considered "meta" packages that only depend on other
upstream packages, and contain no libraries or binaries themselves.

.. list-table::
   :header-rows: 1
   :widths: 20 40 40
   :class: table-leftwise

   * - Package
     - Description
     - Dependencies

   * - opengeo
     - Top level package that installs everything.
     - opengeo-server opengeo-server 

   * - opengeo-server
     - Installs all server packages.
     - postgis21-postgresql93 geoserver geowebcache geoexplorer opengeo-dashboard opengeo-docs  opengeo-tomcat 

   * - opengeo-client
     - Installs all client packages.
     - postgis21 pgadmin3 pdal

Server Packages
---------------

The following packages contain the server components of OpenGeo Suite. 

.. list-table::
   :header-rows: 1
   :widths: 20 40 40
   :class: table-leftwise

   * - Package
     - Description
     - Dependencies

   * - postgis21-postgresql93
     - PostGIS 2.1 extensions for PostgreSQL 9.3. 
     - postgis21 postgresql93-server

   * - pointcloud-postgresql93
     - Point cloud extensions for PostgreSQL 9.3. 
     - postgresql93-server ght

   * - geoserver
     - GeoServer geospatial data server.
     - 

   * - geowebcache
     - GeoWebCache tile caching server.
     - 

   * - geoexplorer
     - GeoExplorer map composing application.
     - 

   * - opengeo-dashboard 
     - OpenGeo Suite dashboard.
     - 

   * - opengeo-dashboard 
     - OpenGeo Suite dashboard.
     - 

   * - opengeo-docs 
     - OpenGeo Suite documentation.
     - 

   * - opengeo-tomcat 
     - OpenGeo Suite webapps for Tomcat.
     - tomcat (fedora) | tomcat6 (centos/rhel 6) | tomcat5 (centos/rhel 5)


Client/Library Packages
-----------------------

The following packages contain the client components of OpenGeo Suite.

.. list-table::
   :header-rows: 1
   :widths: 20 40 40
   :class: table-leftwise

   * - Package
     - Description
     - Dependencies

   * - postgis21
     - PostGIS 2.1 userland binaries and libraries.
     - postgresql93-libs geos proj gdal

   * - pgadmin3
     - pgAdmin database manager for PostgreSQL
     - wxGTK

   * - ght
     - GeoHash Tree library for point cloud data. 
     - 

   * - pdal
     - Point Cloud format library.
     - libgeotiff laszip gdal geos postgresql93-libs 

   * - gdal
     - GDAL/OGR format library.
     - proj geos postgresql93-libs

GeoServer Add-ons
-----------------

The following packages add additional functionality to GeoServer.

.. list-table::
   :header-rows: 1
   :widths: 20 40 40
   :class: table-leftwise

   * - Package
     - Description
     - Dependencies

   * - geoserver-mapmeter
     - Mapmeter extension to GeoServer (see http://mapmeter.com).
     - geoserver
   * - geoserver-cluster
     - Clustering extension for GeoServer.
     - geoserver
   * - geoserver-jdbc
     - Database catalog and configuration extension.
     - geoserver
   * - geoserver-css
     - CSS styling extension for GeoServer.
     - geoserver
   * - geoserver-csw
     - Catalogue Service for Web (CSW) extension for GeoServer.
     - geoserver
   * - geoserver-wps
     - Web Processing Service (WPS) extension for GeoServer.
     - geoserver
   * - geoserver-script
     - Scripting extension for GeoServer.
     - geoserver
