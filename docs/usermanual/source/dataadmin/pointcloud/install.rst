.. _dataadmin.pointcloud.install:

Installing Point Cloud
======================

The Point Cloud extension is available as a part of OpenGeo Suite.

Windows
-------

During the installation process, on the :guilabel:`Components` page, expand the :guilabel:`PostGIS Add-ons` tree and select :guilabel:`Point Cloud`.

Then continue below at :ref:`dataadmin.pointcloud.install.activate`.

OS X
----

The Point Cloud extension is already installed by default on OS X. No further configuration is necessary.

Continue below at :ref:`dataadmin.pointcloud.install.activate`.

Ubuntu Linux
------------

A package for Ubuntu Linux is available called ``postgresql-9.3-pointcloud``. It can be installed via standard package management, assuming the OpenGeo Suite repository has been added. (See the :ref:`Ubuntu installation page <installation.ubuntu.install>` for more information.)

.. code-block:: console

   apt-get install postgresql-9.3-pointcloud
   apt-get install pdal

Then continue below at :ref:`dataadmin.pointcloud.install.activate`.

Red Hat-based Linux
-------------------

A package for Red Hat-based Linux distributions such as RHEL, CentOS, and Fedora is available, called ``pointcloud-postgresql93``. It can be installed via standard package management, assuming the OpenGeo Suite repository has been added. (See the :ref:`Red Hat installation page <installation.redhat.install>` for more information.)
  
.. code-block:: console

   yum install pointcloud-postgresql93
   yum install pdal

Then continue below at :ref:`dataadmin.pointcloud.install.activate`.

Application Servers
-------------------

For installation when using OpenGeo Suite for Application Servers, please `contact us <http://boundlessgeo.com/about/contact-us/>`_.


.. _dataadmin.pointcloud.install.activate:

Activating the Point Cloud extension
------------------------------------

The Point Cloud extension works like any other extension in PostgreSQL. It is applied to a given database via the ``CREATE EXTENSION`` SQL command.

#. Create/connect to a database (either through the command line or through :ref:`pgAdmin <dataadmin.pgGettingStarted.pgadmin>`.)

#. Run the following command::

     CREATE EXTENSION pointcloud

If the command is successful, the database now has the Point Cloud extension activated.
