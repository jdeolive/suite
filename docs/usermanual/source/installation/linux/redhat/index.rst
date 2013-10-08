.. _installation.linux.redhat:


Installing OpenGeo Suite for Red Hat
====================================

.. toctree:: 
   :hidden:

   packages
   misc

This section describes how to install OpenGeo Suite on Red Hat based 
distributions. The following distributions are supported:

* Fedora 18 and 19
* CentOS 5 and 6
* RHEL 5 and 6

Installation
------------

.. note:: The commands in this section require root privileges. 

#. Change to the ``root`` user:

   .. code-block:: bash

      sudo su - 

#. Add the OpenGeo repository by creating the file :file:`/etc/yum.repos.d/OpenGeo.repo` with the following contents::

      [opengeo]
      name=opengeo
      baseurl=http://yum.opengeo.org/suite/v4/<OS>/$releasever/$arch
      enabled=1
      gpgcheck=0

   Replace ``<OS>`` with one of "fedora", "centos", or "rhel" based on the 
   distribution.

#. Search for OpenGeo packages:

   .. code-block:: bash

      yum search opengeo

   If the search command does not return any results, the repository was not added properly. Examine the output of the search command for any errors. 

#. Install:

   .. code-block:: bash

      yum install opengeo

   .. note:: The above will install all OpenGeo Suite packages. See the :ref:`Packages <installation.linux.redhat.packages>` section for details of individual packages. 

Installation is now complete. It is recommend that you read through the 
:ref:`installation.linux.redhat.misc` section that contains information about 
working with OpenGeo Suite on Red Hat based distributions. 
