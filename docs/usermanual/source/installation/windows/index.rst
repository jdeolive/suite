.. _installation.windows:

Installing OpenGeo Suite for Windows
====================================

.. toctree::
   :hidden:

   uninstall
   upgrade
   misc

This document describes how to install OpenGeo Suite for Windows. 

.. warning:: 

   If upgrading from a previous version see the 
   :ref:`Upgrade <installation.windows.upgrade>` section.

Prerequisites
-------------

OpenGeo Suite has the following system requirements:

* **Operating System**: Vista, 7, 8, Server 2003 or newer, XP (client and dev tools only) 
* **Memory**: 1GB minimum (2GB recommended)
* **Disk space**: 600MB minimum (plus extra space for data)
* **Browser**: Any modern web browser is supported
* **Permissions**: Administrative rights
* **Software**: .NET Framework 4 (`Download <http://www.microsoft.com/en-us/download/details.aspx?id=17851>`_)

Installation
------------

.. note:: OpenGeo Suite for Windows requires `.NET Framework 4 <http://www.microsoft.com/en-us/download/details.aspx?id=17851>`_. Installation will fail if not present.

#. Double-click the :file:`OpenGeo.exe` file.

#. At the **Welcome** screen, click :guilabel:`Next`.

   .. figure:: img/welcome.png

      Welcome screen

#. Read the **License Agreement** then click :guilabel:`I Agree`.

   .. figure:: img/license.png

      License Agreement

#. Select the **Destination folder** where you would like to install OpenGeo Suite, and click :guilabel:`Next`.

   .. figure:: img/directory.png

      Destination folder for the installation

#. Select the name and location of the **Start Menu folder** to be created, and click :guilabel:`Next`.

   .. figure:: img/startmenu.png

      Start Menu folder to be created

#. Select the components you wish to install, and click :guilabel:`Next`.

   .. figure:: img/components.png

      Component selection

   See :ref:`installation.windows.components` for more details. 

#. Click :guilabel:`Install` to perform the installation.

   .. figure:: img/ready.png

      Ready to install

#. Please wait while the installation proceeds.

   .. figure:: img/install.png

      Installation

#. After installation, click :guilabel:`Finish`.

   .. figure:: img/finish.png

      OpenGeo Suite successfully installed

Installation is now complete. It is recommended that you now read through the 
:ref:`installation.windows.misc` section that contains some valuable information
for working with OpenGeo Suite on Windows. 

For more information, please see the **User Manual**.

.. _installation.windows.components:

Components
----------

The following is a list of the components available to install.

.. list-table::
   :widths: 20 80
   :stub-columns: 1
   :class: table-leftwise

   * - PostGIS
     - The PostgreSQL/PostGIS spatial database.
   * - GeoServer
     - Server implementing OGC compliant map and feature services.
   * - GeoWebCache
     - Tile caching server.
   * - GeoExplorer
     - Map viewing and editing application.

Client Tools
^^^^^^^^^^^^

.. list-table::
   :widths: 20 80
   :stub-columns: 1
   :class: table-leftwise

   * - PostGIS
     - PostGIS data loading utilities.
   * - pgAdmin
     - Graphical PostGIS/PostgreSQL database manager.
   * - GDAL/OGR
     - Spatial data manipulation library.

Dev Tools
^^^^^^^^^

.. list-table::
   :widths: 20 80
   :stub-columns: 1
   :class: table-leftwise

   * - Webapp SDK
     - Toolkit for building web map applications.
   * - GeoScript
     - Scripting extension for GeoServer.

PostGIS Add-ons
^^^^^^^^^^^^^^^

.. list-table::
   :widths: 20 80
   :stub-columns: 1
   :class: table-leftwise

   * - PointCloud
     - PostgreSQL LIDAR extension. 

GeoServer Add-ons
^^^^^^^^^^^^^^^^^

.. list-table::
   :widths: 20 80
   :stub-columns: 1
   :class: table-leftwise

   * - Mapmeter
     - `Mapmeter <http://mapmeter.com>`_ monitoring service.
   * - CSS Styling
     - CSS map styling support.
   * - WPS
     - Web Processing Service (WPS) support.
   * - CSW
     - Catalogue Service for Web (CSW) support.
   * - Clustering
     - Clustering plug-ins. 

Uninstallation
--------------

To uninstall OpenGeo Suite run the uninstaller from the start menu. See
the :ref:`installation.windows.uninstall` section for more details.


