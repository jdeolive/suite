.. _installation.linux.ubuntu.misc:

Working with OpenGeo Suite for Ubuntu
=====================================

This document contains information about various tasks specific to OpenGeo 
Suite for Ubuntu.

Starting and stopping OpenGeo services
--------------------------------------

OpenGeo Suite is comprised of two main services:

#. The `Tomcat <http://tomcat.apache.org/>`_ web server that contains all the OpenGeo web applications such as GeoServer, GeoWebCache, and GeoExplorer. 

#. The `PostgreSQL <http://www.postgresql.org/>`_ database server with the PostGIS spatial extensions. 

Controlling the Tomcat service
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

To start/stop/restart the Tomcat service:

  .. code-block:: bash
 
     /etc/init.d/tomcat6 start|stop|restart

Controlling the PostgreSQL service
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

To start/stop/restart the PostgreSQL service:

  .. code-block:: bash
 
     /etc/init.d/postgresql start|stop|restart

  .. note:: If you have multiple versions of PostgresSQL installed you can specify which version to control with a third argument. For example:

     .. code-block:: bash

         /etc/init.d/postgresql start 9.3 


Service port configuration
--------------------------

The Tomcat and PostgreSQL services run on ports **8080** and **5432** respectively.
In certain cases it is desirable to change these port values to resolve conflicts 
with other services on the system.

Changing the Tomcat port
^^^^^^^^^^^^^^^^^^^^^^^^

To change the Tomcat port:

#. Edit the file :file:`/etc/tomcat6/server.xml`. 

#. Search for "8080" (around line 71) and change the ``port`` attribute to the desired value.

#. Restart tomcat. 

Changing the PostgreSQL port
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

To change the PostgreSQL port:

#. Edit the file :file:`/etc/postgresql/9.3/main/postgresql.conf`.

#. Search or the ``port`` property (around line 63) and change it to the desired value.

#. Restart PostgreSQL.

GeoServer Data Directory
------------------------

The *GeoServer Data Directory* is the location on the file system where GeoServer
stores all of its configuration, and optionally data. When working with GeoServer
it is often necessary to know where this directory is. It is located at 
:file:`/var/lib/opengeo/geoserver`. 

You may wish to change this location to an alternate location, perhaps to use an 
existing GeoServer configuration. To do so:

#. Edit the file :file:`/usr/share/opengeo/geoserver/WEB-INF/web.xml`.
#. Search for ``GEOSERVER_DATA_DIR`` and change its value accordingly.
#. Restart Tomcat.

