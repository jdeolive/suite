.. _installation.ubuntu.install:

New installation on Ubuntu Linux
================================

.. note:: 

   If upgrading from a previous version of OpenGeo Suite see the :ref:`Upgrade <installation.ubuntu.upgrade>` section.

This section describes how to install OpenGeo Suite on Ubuntu Linux. The following Ubuntu versions are supported:

* `Lucid Lynx <http://releases.ubuntu.com/lucid/>`_ (10.04 LTS)
* `Precise Pangolin <http://releases.ubuntu.com/precise/>`_ (12.04 LTS)

.. warning::

   OpenGeo Suite is not supported on any other versions (such as 13.04 or later). If running an unsupported installation, dependency conflicts may occur.


System requirements
-------------------

OpenGeo Suite for Ubuntu has the following system requirements:

* Memory: 512MB minimum (1GB recommended)
* Disk space: 750MB minimum (plus extra space for any loaded data)
* Browser: Any modern web browser is supported
* Permissions: Super user privileges are required for installation

Installation
------------

.. note:: The commands in this section require root privileges. 

#. Change to the ``root`` user:

   .. code-block:: bash

      sudo su - 

#. Import the OpenGeo GPG key:

   .. code-block:: bash

      wget -qO- http://apt.opengeo.org/gpg.key | apt-key add - 

#. Add the OpenGeo repository. If installing for Precise:

   .. code-block:: bash

      echo "deb http://apt.opengeo.org/suite/v4/ubuntu/ precise main" > /etc/apt/sources.list.d/opengeo.list

   If installing for Lucid:

   .. code-block:: bash

      echo "deb http://apt.opengeo.org/suite/v4/ubuntu/ lucid main" > /etc/apt/sources.list.d/opengeo.list

#. Update:

   .. code-block:: bash

      apt-get update

#. Search for OpenGeo packages:

   .. code-block:: bash

      apt-cache search opengeo

   If the search command does not return any results, the repository was not added properly. Examine the output of the ``apt`` commands for any errors or warnings.

#. Install:

   .. code-block:: bash

      apt-get install opengeo

   .. note:: The above will install all OpenGeo Suite packages. See the :ref:`Packages <installation.ubuntu.packages>` section for details of individual packages. 

After installation
------------------

Installation is now complete. Please see the section on :ref:`installation.ubuntu.misc`.

