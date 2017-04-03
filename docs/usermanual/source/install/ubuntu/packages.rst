.. _install.ubuntu.packages:

Package install on Ubuntu
=========================

This page describes how to perform a package installation of **Boundless Suite** |version| on Ubuntu Linux.

.. note:: For upgrades, see the section on :ref:`Upgrading <install.ubuntu.packages.upgrade>` below.

.. include:: include/sysreq.txt

.. _install.ubuntu.packages.install:

New installation
----------------

This section describes how to perform a clean package installation of **Boundless Suite** |version| on Ubuntu Linux.

See the :ref:`install.ubuntu.packages.list` for details about the possible packages to install.

.. warning:: Mixing repositories is not recommended. If you already have a community (non-Boundless) repository that contains some of the components of Boundless Suite (such as PostgreSQL) please remove them before installing Boundless Suite.

#. Change to the ``root`` user:

   .. code-block:: bash

      sudo su -

#. Import the Boundless GPG key:

   .. code-block:: bash

      wget -qO- https://downloads-repo.boundlessgeo.com/GPG-KEY-Boundless | apt-key add -

#. Add the Boundless repository:

   .. code-block:: bash

      echo "deb [arch=amd64] https://<username>:<password>@downloads-repo.boundlessgeo.com/suite-repo/4.9.1/ubuntu trusty main" > /etc/apt/sources.list.d/boundless.list

   Make sure to replace each instance of ``<username>`` and ``<password>`` with the user name and password supplied to you.

   .. note:: Your username is your email address. When entering your username into the ``boundless.list`` file, replace the ``@`` in your username with ``%40``.

   .. note:: Please `contact us <http://boundlessgeo.com/about/contact-us/>`__ if you have purchased Boundless Suite and do not have a user name and password.

#. Update the repository list:

   .. code-block:: bash

      apt-get update

#. Search for Boundless packages to verify that the repository list is correct.

   .. code-block:: bash

      apt-cache search suite-

   If the command does not return any results, examine the output of the ``apt-cache`` command for any errors or warnings.

#. You have options on what packages to install.

   * A simple installation including GeoServer, documentation, and the :ref:`intro.dashboard`:

     .. code-block:: bash

        apt-get install suite-geoserver suite-docs suite-dashboard

   * A more complete install, including all the web applications, PostGIS, GDAL, and NetCDF:

     .. code-block:: bash

        apt-get install suite-dashboard \
                        suite-geoserver \
                        suite-geowebcache \
                        suite-composer \
                        suite-docs \
                        suite-quickview  \
                        suite-wpsbuilder \
                        postgresql-9.3-postgis-2.1 \
                        suite-gs-gdal \
                        suite-gs-netcdf \
                        suite-gs-netcdf-out

   .. note::  See the :ref:`install.ubuntu.packages.list` for details of individual packages.

   .. note::  If you already have an existing JVM installed, you may need to change the *default* java. Run ``java -version`` to check the default java version. If it is less than 1.8, use :ref:`update-alternatives<sysadmin.jvm.alternatives>` to change the default to the newly installed java 8 JVM.

#. Restart the server.

   .. code-block:: bash

      service tomcat8 restart

#. Verify that the installation succeeded by opening your browser and navigating to one of the following URLs:

   * http://localhost:8080/dashboard
   * http://localhost:8080/geoserver

   .. note:: Please see the section on :ref:`sysadmin.ubuntu` for additional information and best practices.

.. _install.ubuntu.packages.upgrade:

Upgrade
-------

Upgrading from 4.9.0 to |version|
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

This section describes how to upgrade Boundless Suite 4.9.0 to |version| on Ubuntu Linux.

.. note::

   If you made changes to the tomcat context files located in ``/etc/tomcat8/Catalina/localhost/``, please back them up now or your changes will be lost. After completing the upgrade process, restore the backed up files.

#. Change to the ``root`` user:

   .. code-block:: bash

      sudo su -

#. Remove the 4.9.0 packages:

   .. code-block:: bash

      apt-get remove suite-*

   Make note of which packages were removed this way.

#. Import the Boundless GPG key:

   .. code-block:: bash

      wget -qO- https://downloads-repo.boundlessgeo.com/GPG-KEY-Boundless | apt-key add -

#. Replace the 4.9.0 repo definition with the new repo definition. Open ``/etc/apt/sources.list.d/boundless.list`` and replace the contents with:

   .. code-block:: none

      deb [arch=amd64] https://<username>:<password>@downloads-repo.boundlessgeo.com/suite-repo/4.9.1/ubuntu trusty main

   Make sure to replace each instance of ``<username>`` and ``<password>`` with the user name and password supplied to you.

   .. note:: Your username is your email address. When entering your username into the ``boundless.list`` file, replace the ``@`` in your username with ``%40``.

#. Refresh the apt repo data:

   .. code-block:: bash

      apt-get update

#. Install all Boundless Suite 4.9.1 packages corresponding to the ``suite-*`` packages which were removed in step 1. For example:

   .. code-block:: bash

      apt-get install suite-geoserver suite-docs suite-dashboard


Upgrading from 4.8 and older
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

This section describes how to upgrade Boundless Suite 4.8 and earlier to |version| on Ubuntu Linux.

.. warning:: We do **not** recommend upgrading Boundless Suite on a production server. Instead, do a new install on new machine, then transfer your data and settings to the new machine.

.. warning::

   Because of the major package changes involved, if you have any version earlier than 4.9.0, it must be uninstalled first.  Make sure you backup your data, configuration, your old 4.8 install, and any other data/software on the system.

   The data directory at ``/var/lib/opengeo/geoserver`` will not be removed during uninstallation.

#. Backup your configuration and data

#. Change to the ``root`` user:

   .. code-block:: bash

      sudo su -

#. Uninstall old packages:

   .. warning:: This will uninstall many packages (including ``tomcat7``, ``postgresql/postgis``, and other common GIS tools). Verify that removing these packages will **not** interfere with other applications running on your system. Ensure you do not have another Tomcat (or other service) on port 8080.

   .. code-block:: bash

      apt-get remove gdal-mrsid \
                     geoexplorer \
                     geoserver \
                     geoserver-* \
                     geowebcache \
                     laszip \
                     laszip-dev \
                     libgdal-opengeo \
                     libgdal-opengeo-dev \
                     libgeos-3.5.0 \
                     libgeos-c1 \
                     libgeos-dev \
                     libgeos-doc \
                     libgeotiff \
                     libgeotiff-dev \
                     libght \
                     libght-dev \
                     libjpeg-turbo-official \
                     libpq5 \
                     libproj-dev \
                     libproj0 \
                     opengeo \
                     opengeo-* \
                     pdal \
                     pdal-dev \
                     pgadmin3 \
                     pgadmin3-data \
                     postgis-2.1 \
                     postgresql-9.3 \
                     postgresql-9.3-pointcloud \
                     postgresql-9.3-postgis-2.1 \
                     postgresql-client-9.3 \
                     postgresql-client-common \
                     postgresql-common \
                     proj \
                     proj-bin \
                     proj-data \
                     tomcat7
      apt-get autoremove

#. Remove the reference to the Suite 4.8 repository:

   .. code-block:: bash

      rm /etc/apt/sources.list.d/opengeo.list

#. Continue above in the :ref:`install.ubuntu.packages.install` section. When finished, change your :guilabel:`GEOSERVER_DATA_DIR` environment variable to point to the correct location.

   .. note:: A default installation of Boundless Suite, will install a sample GeoServer data directory. Make sure to update the :guilabel:`GEOSERVER_DATA_DIR` environment variable to point to your old data directory, if desired.


.. _install.ubuntu.packages.list:

Package list
------------

Boundless Suite is broken up into a number of discrete packages. This section describes all of the available packages.

The packages are managed through the standard package management system for Ubuntu called `apt <https://help.ubuntu.com/community/AptGet/Howto>`_. All packages can be installed with the following command::

  sudo apt-get install <package>

where ``<package>`` is any one of the package names listed below.

Boundless Suite web applications
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. list-table::
   :header-rows: 1
   :widths: 30 70
   :class: non-responsive

   * - Package
     - Description
   * - ``suite-composer``
     - :ref:`Composer application <webmaps.composer>`
   * - ``suite-dashboard``
     - :ref:`Boundless Suite Dashboard <intro.dashboard>`
   * - ``suite-docs``
     - Boundless Suite documentation
   * - ``suite-geoserver``
     - GeoServer application
   * - ``suite-geowebcache``
     - GeoWebCache application
   * - ``suite-quickview``
     - QuickView application showcasing the :ref:`WebSDK <webapps.sdk>`
   * - ``suite-wpsbuilder``
     - :ref:`Graphical utility <processing.wpsbuilder>` for executing WPS processes
   * - ``suite-tomcat8``
     - Apache Tomcat application server (automatically installed by these packages)

Boundless Suite GeoServer extensions
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

The following packages add additional functionality to GeoServer. After installing any of these packages, you will need to restart Tomcat:

.. code-block:: console

   service tomcat8 restart

For more information, please see the section on :ref:`GeoServer extensions <intro.extensions>`.

.. include:: /install/include/suite-gs-packages.txt


Binary packages
~~~~~~~~~~~~~~~

The following major binary packages are available:

.. list-table::
   :header-rows: 1
   :widths: 30 70
   :class: non-responsive

   * - Package
     - Description
   * - ``libgdal``
     - Main GDAL/OGR binary package
   * - ``libgdal-java``
     - Java support for GDAL
   * - ``libgdal-dev``
     - Development support for GDAL
   * - ``gdal-mrsid``
     - MrSID plugin for GDAL
   * - ``libjpeg-turbo-official``
     - Libjpeg turbo binaries (version 1.4.2)
   * - ``libnetcdf-bin``
     - NetCDF Binary packages
   * - ``libnetcdf-dev``
     - Development support for the NetCDF Binary
   * - ``postgresql-9.3-postgis-2.1``
     - Postgresql 9.3 and PostGIS 2.1
   * - ``postgresql-9.3-pointcloud``
     - Pointcloud for postgresql
   * - ``pdal``
     - PDAL (Point Data Abstraction Library)
   * - ``pdal-dev``
     - Development support for PDAL
   * - ``proj``
     - PROJ.4 libary
   * - ``libgeos-3.5.0``
     - GEOS (Geometry Engine, Open Source) library
