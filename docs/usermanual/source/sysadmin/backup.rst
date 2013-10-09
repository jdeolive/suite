.. _sysadmin.backup:


Backing up
==========

Backing up is a vital task of any system administrator.  It should be done on a regular basis, and especially before doing any in-place upgrades.  This section discusses how to backup the various components of the OpenGeo Suite.

The location of the OpenGeo Suite data and settings are dependent on the operating system, but typically are in the home directory of the user account where the software was installed.  For example, given an account called "admin", the data and settings are located here:

.. list-table::
   :header-rows: 1

   * - Operating System
     - Directory
   * - Linux
     - ``/home/admin/.opengeo/``
   * - Mac
     - ``/opt/opengeo/suite/``
   * - Windows XP
     - ``C:\Documents and Settings\admin\.opengeo\``
   * - Windows Vista/7
     - ``C:\Users\admin\.opengeo\``

.. _sysadmin.backup.geoserver:

Backing up the GeoServer Data Directory
---------------------------------------

The GeoServer data directory is located by default here:

.. list-table::
   :header-rows: 1

   * - Installation Target
     - GeoServer Data directory
   * - Linux
     - ``/home/admin/.opengeo/data_dir``
   * - Mac
     - ``/opt/opengeo/suite/data/``
   * - Windows XP
     - ``C:\Documents and Settings\admin\.opengeo\data_dir``
   * - Windows Vista/7
     - ``C:\Users\admin\.opengeo\data_dir``
   * - Production WAR
     - ``<SERVLET_ROOT>/webapps/geoserver/data/``

In general, the directory can safely be copied or archived to another location, and can be restored by reversing the process.  Please be sure to shut down the OpenGeo Suite before making any backups or restores.

.. _sysadmin.backup.postgis:

Backing up the PostGIS database system
--------------------------------------

.. note:: Please see the :ref:`installation` section for your particular operating system if backing up in preparation for an upgrade to OpenGeo Suite 3.x.

The PostGIS databases can be backed up with the built-in utilities `pg_dump <http://www.postgresql.org/docs/9.1/static/app-pgdump.html>`_ (for a single database) and `pg_dumpall <http://www.postgresql.org/docs/9.1/static/app-pg-dumpall.html>`_ (for multiple databases).

Miscellaneous backup tasks
--------------------------

GeoExplorer saves its map configurations in a file called :file:`geoexplorer.db`.  This file is typically located in the same location as the GeoServer data directory.  This file can be safely backed up and restored as necessary without any special utilities.

Custom content
--------------

Make sure to backup all data and custom applications hosted on the server but not contained in the GeoServer data directory. The details are dependent on the content and where it was placed.
