.. _installation.mac.misc:

Working with OpenGeo Suite for Mac
==================================

This document contains information about various tasks specific to OpenGeo 
Suite for Mac. 

Starting and stopping OpenGeo services
--------------------------------------


Service port configuration
--------------------------

The Jetty and PostgreSQL services run on ports **8080** and **5432** respectively. 
These ports can often conflict with existing services on the system. In this case
the ports must be changed. 

Changing the Jetty port
^^^^^^^^^^^^^^^^^^^^^^^

To change the Jetty port:

#. Edit file :file:`~/Library/Application Support/GeoServer/geoserver/start.ini`.

#. Search for the Java system property named ``jetty.port`` and change its value to a number that does not conflict with any existing services on the machine. 

#. Optionally change the ``STOP.PORT`` property in the same manner.

.. note:: It is a common convention for Java services like Jetty to use port values greater than 8000. 

Changing the PostgreSQL port
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

To change the PostgreSQL port:

#. Edit the file :file:`~/Library/Application Support/PostGIS/var/postgresql.conf`.

#. Search or the ``port`` property (around line 63), uncomment it and change its value from 5432 to a number that does not conflict with any existing services on the machine.

GeoServer Data Directory
------------------------

The *GeoServer Data Directory* is the location on the file system where GeoServer
stores all of its configuration, and optionally data. When working with GeoServer
it is often necessary to know where this directory is. It is located at 
:file:`~/Library/Application Support/GeoServer/data_dir`. 

You may wish to change this location to an alternate location, perhaps to use an 
existing GeoServer configuration. To do so:

#. Edit the file :file:`~/Library/Application Support/GeoServer/geoserver/start.ini`.

.. todo: finish this


