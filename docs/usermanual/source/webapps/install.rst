.. _webapps.sdk.install:

Installing Boundless SDK
========================

To create an application with Boundless SDK, you will need:

* Boundless SDK installed. See the next section for installation details.

* A `Java Development Kit (JDK) <http://www.oracle.com/technetwork/java/javase/downloads/index.html>`_. A standard Java Runtime Environment (JRE) is not sufficient.

* `Apache Ant <http://ant.apache.org>`_ installed and on the ``PATH``. To verify this, execute the command ``ant -version`` from a command prompt.

Installation with OpenGeo Suite
-------------------------------

Boundless SDK is easiest to install as part of OpenGeo Suite.

Windows
~~~~~~~

Boundless SDK is included in OpenGeo Suite for Windows as an optional component. During the :ref:`installation.windows` process, on the :guilabel:`Components` page, expand the :guilabel:`Dev Tools` tree and select :guilabel:`Boundless SDK`.

.. todo:: Figure

OS X
~~~~

Boundless SDK is part of the OS X OpenGeo Suite CLI tools.

#. In the OpenGeo folder, double-click :guilabel:`OpenGeo CLI Tools`.

   .. figure:: ../installation/mac/img/apps.png

#. Follow the instllation process to install the CLI tools, including Boundless SDK.

#. Add :file:`/usr/local/opengeo/bin` to your ``PATH`` as described in the :ref:`OS X CLI tools installation section <installation.mac.install.cli>`.

#. To verify installation, type :command:`suite-sdk` on a terminal.

Ubuntu Linux
~~~~~~~~~~~~

Boundless SDK is available as an individual package, installed through standard package management. The package is called ``opengeo-webapp-sdk``.

.. code-block:: console

   sudo apt-get install opengeo-webapp-sdk

Red Hat-based Linux
~~~~~~~~~~~~~~~~~~~

Boundless SDK is available as an individual package, installed through standard package management. The package is called ``opengeo-webapp-sdk``.

.. code-block:: console

   sudo yum install opengeo-webapp-sdk

Application Servers
~~~~~~~~~~~~~~~~~~~

For installation when using OpenGeo Suite for Application Servers, please see the section on standalone installation.

Standalone installation
-----------------------

Boundless SDK can also be installed on a machine that does not have OpenGeo Suite.

#. Download Boundless SDK from http://boundlessgeo.com/solutions/solutions-software/software/, making sure to match the version of the SDK with the version of OpenGeo Suite.

#. Extract the archive to a suitable location on the file system.

#. Add the SDK :file:`bin` directory on the ``PATH``.

To verify the SDK is installed properly, execute the command ``suite-sdk`` from a command prompt.

Troubleshooting
---------------

If you receive the following error when running ``suite-sdk``::

  Unable to locate tools.jar.

This means that your system is using a JRE and not a JDK. Make sure that you have downloaded and installed a JDK, and set the ``JAVA_HOME`` environment variable to point to the JDK. For example, if you installed a JDK in :file:`C:\\Program Files\\Java\\jdk1.7.0`, then set the ``JAVA_HOME`` variable to ``C:\Program Files\Java\jdk1.7.0\jre``.
