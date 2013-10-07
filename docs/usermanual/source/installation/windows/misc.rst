.. _installation.windows.misc:

Working with OpenGeo Suite for Windows
======================================

This document contains information about various tasks specific to OpenGeo 
Suite for Windows. 

Starting and stopping OpenGeo services
--------------------------------------

OpenGeo Suite is comprised of two services:

#. **OpenGeo Jetty** - The `Jetty <http://www.eclipse.org/jetty/>`_ web server that contains all the OpenGeo web applications such as GeoServer, GeoWebCache, and GeoExplorer. 

#. **OpenGeo PostgreSQL** - The `PostgreSQL <http://www.postgresql.org/>`_ database server with the PostGIS spatial extensions. 

The services can be started and stopped directly from the Start Menu by 
navigating to :menuselection:`All Programs --> OpenGeo Suite` and using the 
:guilabel:`Start` and :guilabel:`Stop` short cuts. They must be run as Adminstrator.

   .. figure:: img/startstop_services.png

      Starting and Stopping OpenGeo Services

.. note:: Alternatively services can also be controlled from the Windows :guilabel:`Services` dialog available by navigating to :menuselection:`Administrative Tools --> Services` from the Windows :guilabel:`Control Panel`. 

Service port configuration
--------------------------

The Jetty and PostgreSQL services run on ports **8080** and **5432** respectively. 
These ports can often conflict with existing services on the system. In this case
the ports must be changed. 

Changing the Jetty port
^^^^^^^^^^^^^^^^^^^^^^^

To change the Jetty port:

#. Open the file :file:`C:\\Program Files\\Boundless\\OpenGeo\\bin\\jetty.cmd` in a text editor such as Notepad.

#. Edit the variable named ``PORT`` near the top of the file changing its value
   from ``8080`` to a number that that does not conflict with any existing services on the machine. 

#. Optionally change the ``STOP_PORT`` variable value in the same manner.

.. note:: It is a common convention for Java services like Jetty to use port values greater than 8000. 

Changing the PostgreSQL port
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

To change the PostgreSQL port:

#. Open the file :file:`C:\\ProgramData\\Boundless\\OpenGeo\\pgsql\\postgresql.conf` in a text editor such as Notepad. 

#. Search or the ``port`` property (around line 63), uncomment it and change its value from 5432 to a number that does not conflict with any existing services on the machine.

GeoServer Data Directory
------------------------

The *GeoServer Data Directory* is the location on the file system where GeoServer
stores all of its configuration, and optionally data. When working with GeoServer
it is often necessary to know where this directory is. It is located at 
:file:`C:\\ProgramData\\Boundless\\OpenGeo\\geoserver`. 

You may wish to change this location to an alternate location, perhaps to use an 
existing GeoServer configuration. To do so:

#. Create a new **system** environment variable named ``GEOSERVER_DATA_DIR``.
#. Sets its value to the desired geoserver data directory.

   .. figure:: img/gs_data_dir.png

      Setting GeoServer Data Directory  

#. Restart the OpenGeo Jetty service.

.. note:: You may have to restart Windows itself for the environment variable change to be picked up.



