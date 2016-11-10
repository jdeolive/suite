.. _sysadmin.mac:

Administration on Mac OS X
==========================

This document contains information about various tasks specific to Boundless Suite for Mac OS X. 

Boundless services
------------------

Boundless Suite is comprised of two services:

#. The `Tomcat <http://tomcat.apache.org/>`_ web server that contains all the web applications such as GeoServer and GeoWebCache.

#. The `PostgreSQL <http://www.postgresql.org/>`_ database server with the PostGIS spatial extensions.

   .. note:: The Boundless PostgreSQL service is only available through the :ref:`virtual machine <install.mac.vm>`.

Service port configuration
--------------------------

The Tomcat and PostgreSQL services run on ports **8080** and **5432** respectively. These ports can often conflict with existing services on the system, in which case the ports must be changed.

Changing the Tomcat port
^^^^^^^^^^^^^^^^^^^^^^^^

The Tomcat port can be changed on installation.

To change the Tomcat port after installation:

#. Open the :file:`server.xml` file, typically located in your Tomcat :file:`conf` directory.

#. Search for the following line::

    <Connector port="8080" protocol="HTTP/1.1"

#. Change the port value.

#. Save the file.

#. Restart Tomcat.

.. note:: To change the port on the Boundless Suite virtual machine, connect to the virtual machine with a Terminal, and follow the :ref:`Ubuntu <sysadmin.ubuntu>` instructions.

Changing the PostgreSQL port
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. note:: The Boundless PostgreSQL service is only available through the :ref:`virtual machine <install.mac.vm>`. To change the port on the Boundless Suite Virtual Machine, connect to the virtual machine with a Terminal, and follow the :ref:`Ubuntu <sysadmin.ubuntu>` instructions. 

GeoServer data directory
------------------------

The **GeoServer data directory** is the location on the file system where GeoServer stores all of its configuration, and (optionally) file-based data.

By default, this directory is located at :file:`~/Library/Application Support/GeoServer` (or :file:`/Library/Application Support/GeoServer`).

To point GeoServer to an alternate location:

#. Edit the :file:`geoserver.xml` file inside your Tomcat configuration.

#. Define ``GEOSERVER_DATA_DIR`` with an appropriate value accordingly.
   
   .. code-block:: xml
      
      <Parameter name="GEOSERVER_DATA_DIR" 
        value="/Library/Application Support/GeoServer" override="false"/>

