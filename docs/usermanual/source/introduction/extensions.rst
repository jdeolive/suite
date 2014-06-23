.. _intro.extensions:

OpenGeo Suite extensions
========================

OpenGeo Suite comes with a number of optional extensions or "add-ons". These add functionality to OpenGeo Suite, but may not be needed by most users. We recommend that you choose only the extensions that you need.

These optional components can be selected in different ways depending on your operating system:

* **Windows**: Extensions can be selected during the :ref:`Windows installation process <installation.windows.install.components>` or by rerunning the installer after OpenGeo Suite is installed.
* **OS X**: Extensions can be added by :ref:`manually copying the extension files <installation.mac.install.extensions>` after OpenGeo Suite is installed.
* **Ubuntu Linux**: Extensions can be installed via :ref:`packages <installation.ubuntu.packages>`.
* **Red Hat-based Linux**: Extensions can be installed by via :ref:`packages <installation.redhat.packages>`.

For users of OpenGeo Suite for Application Servers, please `contact Boundless <http://boundlessgeo.com/about-us/contact/>`_ to receive access to the extensions and information on how to install them.

.. _intro.extensions.clustering:

Clustering
----------

The Clustering extension for GeoServer adds the ability to automatically set up multiple instances of GeoServer that can pool resources.

This extension consists of two parts:

* Database-backed configuration
* Server connection and communication

.. note::

   On Windows, this extension is known simply as "Clustering". However, on Linux, it is divided into two separate packages, both required:

   * ``geoserver-jdbcconfig``
   * ``geoserver-clustering``

For more information about Clustering, please see the :ref:`sysadmin.clustering` section.

Scripts for setting up clusters either on Amazon Web Services (AWS) or locally-hosted virtual machines are available to OpenGeo Suite Enterprise clients. Please `contact Boundless <http://boundlessgeo.com/about-us/contact/>`_ to access these scripts.


.. _intro.extensions.css:

CSS Styling
-----------

The CSS Styling extension adds functionality to GeoServer to be able to style layers using a syntax that is similar to CSS (Cascading Style Sheets). CSS can be preferable to the standard Styled Layer Descriptor (SLD) styling method, due to the compactness of CSS as well as greater familiarity.

For more information about CSS styling in GeoServer, please see the `GeoServer CSS documentation <../geoserver/extensions/css/>`_.

.. warning:: The installation instructions in the GeoServer documentation do not apply to users of OpenGeo Suite. Users of OpenGeo Suite can install the extension in the standard way as indicated above.


.. _intro.extensions.csw:

CSW
---

The CSW extension adds the ability for GeoServer to publish information conforming to the Catalog Service for Web (CSW) protocol.

For more information about CSW in GeoServer, please see the `GeoServer CSW documentation <../geoserver/extensions/csw/>`_.

.. warning:: The installation instructions in the GeoServer documentation do not apply to users of OpenGeo Suite. Users of OpenGeo Suite can install the extension in the standard way as indicated above.


.. _intro.extensions.gdal:

GDAL Image Formats
------------------

The GDAL Image Formats extension adds the ability for GeoServer to publish data from extra raster data sources, through the use of `GDAL <http://www.gdal.org/>`_. These formats include, but are not limited to DTED, EHdr, AIG, and ENVIHdr.

The instructions for enabling these formats may require a few additional steps from the standard extension installation instructions as indicated above. Please see the section on :ref:`installing GDAL image formats <installation.gdal>` for more information.


.. _intro.extensions.geopackage:

GeoPackage
----------

The GeoPackage extension adds the ability for GeoServer to publish data from `GeoPackage <http://www.geopackage.org/>`_ sources (a data format based on SQLite). Once this extension is added, GeoServer will show GeoPackage as one of its available data sources when adding a new store.

.. _intro.extensions.mapmeter:

Mapmeter
--------

The Mapmeter extension adds the ability to connect to Mapmeter. `Mapmeter <http://mapmeter.com>`_ is a cloud-based service that allows you to monitor and analyze your geospatial deployments in real-time.

Mapmeter is available for OpenGeo Suite Enterprise clients, though free users can access a two-week trial of the service.

For more information on using Mapmeter, see the :ref:`sysadmin.mapmeter` section.


.. _intro.extensions.mongodb:

MongoDB
-------

The MongoDB extension adds the ability for GeoServer to publish data from `MongoDB <http://www.mongodb.org/>`_ sources. Once this extension is added, GeoServer will show MongoDB as one of its available data sources when adding a new store.

.. note:: This extension will only work when connecting to MongoDB databases at version 2.4 and above.


.. _intro.extensions.wps:

WPS
---

The WPS extension adds the ability for GeoServer to support and publish the Web Processing Service (WPS). WPS is a protocol for hosting and executing geospatial processes, bringing geospatial anaysis to the client/server model.

For more information of WPS in OpenGeo Suite, please see the :ref:`processing` section.


.. _intro.extensions.pointcloud:

Point Cloud
-----------

The Point Cloud extension adds support for storing and working with point cloud data in PostgreSQL/PostGIS.

For more information on Point Cloud, please see the :ref:`dataadmin.pointcloud` section.
