.. _install.redhat.packages:

Package install on Red Hat
==========================

This page describes how to perform a package installation of **Boundless Suite** |version| on Red hat-based Linux distributions.

.. note:: For upgrades, see the below section on :ref:`Upgrading <install.redhat.packages.upgrade>`.

.. include:: include/sysreq.txt

.. _install.redhat.packages.install:

New installation
----------------

This section describes how to perform a clean package installation of **Boundless Suite** |version| on Red hat-based Linux distributions.

See the :ref:`install.redhat.packages.list` for details about the possible packages to install.

.. warning:: Mixing repositories is not recommended. If you already have a community (non-Boundless) repository that contains some of the components of Boundless Suite (such as PostgreSQL) please remove them before installing Boundless Suite.

#. Change to the ``root`` user:

   .. code-block:: bash

      sudo su -

#. Download the Boundless key

   .. code-block:: bash

      mkdir -p /etc/pki/boundless
      wget -O /etc/pki/boundless/GPG-KEY-Boundless https://downloads-repo.boundlessgeo.com/GPG-KEY-Boundless

#. Add the Boundless Suite repository by creating the file :file:`/etc/yum.repos.d/Boundless.repo` and adding the following contents:

   .. code-block:: none

      [boundless-suite]
      name=Boundless Suite Repository
      baseurl=https://<username>:<password>@downloads-repo.boundlessgeo.com/suite-repo/4.9.1/el/6/x86_64
      enabled=1
      gpgcheck=1
      gpgkey=file:///etc/pki/boundless/GPG-KEY-Boundless

   Make sure to replace ``<username>`` and ``<password>`` with the user name and password supplied to you.

   .. note:: Your username is your email address. When entering your username into the ``Boundless.repo`` file, replace the ``@`` in your username with ``%40``.

   .. note:: Please `contact us <http://boundlessgeo.com/about/contact-us/>`__ if you have purchased Boundless Suite and do not have a user name and password.

#. Search for Boundless packages to verify that the repository list is correct.

   .. code-block:: bash

      yum list suite-*

   If the command does not return any results, examine the output of the ``yum`` command for any errors or warnings.

#. You have options on what packages to install. A standard installation including common packages is as follows:

   * A simple installation including GeoServer, documentation, and the :ref:`intro.dashboard`:

     .. code-block:: bash

        yum install suite-geoserver suite-docs suite-dashboard

   * A more complete install, including all the web applications, PostGIS, GDAL, and NetCDF:

     .. code-block:: bash

        yum install suite-dashboard \
                    suite-geoserver \
                    suite-geowebcache \
                    suite-composer \
                    suite-docs \
                    suite-quickview  \
                    suite-wpsbuilder \
                    postgis21-postgresql93 \
                    suite-gs-gdal \
                    suite-gs-netcdf \
                    suite-gs-netcdf-out

   .. note::  See the :ref:`install.redhat.packages.list` for details of individual packages.

   .. note::  If you already have an existing JVM installed, you may need to change the *default* java. Run ``java -version`` to check the default java version. If it is less than 1.8, use :ref:`update-alternatives<sysadmin.jvm.alternatives>` to change the default to the newly installed java 8 JVM.

#. Restart the server.

     .. code-block:: bash

        service tomcat8 restart

#. Verify that the installation succeeded by opening your browser and navigating to one of the following URLs:

   * http://localhost:8080/dashboard
   * http://localhost:8080/geoserver


   .. note:: Please see the section on :ref:`sysadmin.redhat` for additional information and best practices.

.. _install.redhat.packages.upgrade:

Upgrade
-------

Upgrading from 4.9.0 to |version|
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

This section describes how to upgrade Boundless Suite 4.9.0 to |version| on Red Hat-based Linux distributions.

.. note::

   If you made changes to the tomcat context files located in ``/etc/tomcat8/Catalina/localhost/``, please back them up now or your changes will be lost. After completing the upgrade process, restore the backed up files.

#. Change to the ``root`` user:

   .. code-block:: bash

      sudo su -

#. Remove the 4.9.0 packages:

   .. code-block:: bash

      yum remove suite-*

   Make note of which packages were removed this way.

#. Download the Boundless key:

   .. code-block:: bash

      wget -O /etc/pki/boundless/GPG-KEY-Boundless https://downloads-repo.boundlessgeo.com/GPG-KEY-Boundless

#. Replace the 4.9.0 repo definition with the new repo definition. Open ``/etc/yum.repos.d/Boundless.repo`` and replace the contents with:

   .. code-block:: none

      [boundless-suite]
      name=Boundless Suite Repository
      baseurl=https://<username>:<password>@downloads-repo.boundlessgeo.com/suite-repo/4.9.1/el/6/x86_64
      enabled=1
      gpgcheck=1
      gpgkey=file:///etc/pki/boundless/GPG-KEY-Boundless

   Make sure to replace each instance of ``<username>`` and ``<password>`` with the user name and password supplied to you.

   .. note:: Your username is your email address. When entering your username into the ``Boundless.repo`` file, replace the ``@`` in your username with ``%40``.

#. Refresh the yum repo data:

   .. code-block:: bash

      yum clean all

#. Install all Boundless Suite 4.9.1 packages corresponding to the ``suite-*`` packages which were removed in step 1. For example:

   .. code-block:: bash

      yum install suite-geoserver suite-docs suite-dashboard


Upgrading from 4.8 and older
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

This section describes how to upgrade Boundless Suite 4.8 and earlier to |version| on Red Hat-based Linux distributions.

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

      yum --setopt=clean_requirements_on_remove=1 remove \
                      tomcat \
                      tomcat-* \
                      geoexplorer \
                      geos-* \
                      geoserver \
                      geoserver-* \
                      geowebcache \
                      laszip \
                      laszip-devel \
                      gdal-devel \
                      gdal-mrsid \
                      gdal \
                      ght \
                      ght-devel \
                      laszip \
                      laszip-devel \
                      libgeotiff \
                      libgeotiff-devel \
                      libjpeg-turbo-official \
                      opengeo \
                      opengeo-* \
                      pdal \
                      pdal-devel \
                      pgadmin3 \
                      pgadmin3-docs \
                      pointcloud-postgresql93 \
                      postgis21 \
                      postgis21-* \
                      postgresql93 \
                      postgresql93-* \
                      proj-* \
                      proj \
                      wxBase \
                      wxGTK \
                      wxGTK-*

#. Remove the reference to the Suite 4.8 repository:

   .. code-block:: bash

      rm /etc/yum.repos.d/OpenGeo.repo

#. Continue above in the :ref:`install.redhat.packages.install` section. When finished, change your :guilabel:`GEOSERVER_DATA_DIR` environment variable to point to the correct location.

   .. note:: A default installation of Boundless Suite, will install a sample GeoServer data directory. Make sure to update the :guilabel:`GEOSERVER_DATA_DIR` environment variable to point to your old data directory, if desired.


.. _install.redhat.packages.list:

Package list
------------

Boundless Suite is broken up into a number of discrete packages. This section describes all of the available packages.

The packages use the standard `RPM format <http://www.rpm.org/>`_ used in Red Hat-based systems. All packages can be installed with the following command::

  yum install <package>

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
   * - ``gdal``
     - Main GDAL/OGR binary package
   * - ``gdal-java``
     - Java support for GDAL
   * - ``gdal-devel``
     - Development support for GDAL
   * - ``gdal-mrsid``
     - MrSID plugin for GDAL
   * - ``libjpeg-turbo-official``
     - Libjpeg turbo binaries (version 1.4.2)
   * - ``netcdf``
     - NetCDF Binary packages
   * - ``netcdf-devel``
     - Development support for the NetCDF Binary
   * - ``postgis21-postgresql93``
     - Postgresql 9.3 and PostGIS 2.1
   * - ``pointcloud-postgresql93``
     - Pointcloud for postgresql
   * - ``pdal``
     - PDAL (Point Data Abstraction Library)
   * - ``pdal-devel``
     - Development support for PDAL
   * - ``proj``
     - PROJ.4 libary
   * - ``geos-3.5.0``
     - GEOS (Geometry Engine, Open Source) library
