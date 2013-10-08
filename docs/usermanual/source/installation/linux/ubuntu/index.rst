.. _installation.linux.ubuntu:


Installing OpenGeo Suite for Ubuntu
===================================

.. toctree:: 
   :hidden:

   misc

This section describes how to install OpenGeo Suite on Ubuntu. The following 
Ubuntu versions are supported. 

* `Lucid Lynx <http://releases.ubuntu.com/lucid/>`_ (10.04 LTS)
* `Precise Pangolin <http://releases.ubuntu.com/precise/>`_ (12.04 LTS)

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

   .. note:: The above will install all OpenGeo Suite packages. See the :ref:`Packages <installation.linux.ubuntu.packages>` section for details of individual packages. 

Installation is now complete. It is recommend that you read through the 
:ref:`installation.linux.ubuntu.misc` section that contains information about 
working with OpenGeo Suite on Ubuntu. 







