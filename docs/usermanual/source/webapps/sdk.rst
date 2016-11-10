.. _webapps.sdk:

Creating web apps with Web SDK
==============================

Web SDK is used to create JavaScript-based web mapping applications backed by Boundless Suite.
It makes use of the `JavaScript React framework <https://facebook.github.io/react/>`_ to provide modular components, which can be used to create complete web mapping applications with only a few lines of code.

.. attention:: Web SDK is coming soon. Currently, only the Web SDK QuickView demo application is available.

Install Web SDK
---------------

Web SDK can be obtained from Boundless Connect.

Demo Applications
-----------------

Boundless Suite includes Web SDK QuickView, a demo application built using Web SDK. QuickView is a basic map viewer that can be used to view and modify layers and maps. QuickView is accessible at ``http://localhost:8080/quickview/``.  

For more information about using quickview, see :ref:`webapps.sdk.quickview`.

QuickView Installation
^^^^^^^^^^^^^^^^^^^^^^

Please choose the appropriate installation method for your platform:

* **RedHat Package**: Install the :file:`suite-quickview` package. For details, see :ref:`RedHat Packages <install.redhat.packages>`.

* **Ubuntu Package**: Install the :file:`suite-quickview` package. For details, see :ref:`Ubuntu Packages <install.ubuntu.packages>`.

* **RedHat WAR**: See QuickView in :ref:`install.redhat.tomcat.otherapps`.

* **Ubuntu WAR**: See QuickView in :ref:`install.ubuntu.tomcat.otherapps`.

* **OS X WAR**: See QuickView in :ref:`install.mac.tomcat.otherapps`.

* **Windows WAR**: See :ref:`install.windows.tomcat.quickview`


.. API Documentation
.. -----------------

.. For details on all the components provided by Web SDK and how to use them, refer to the `Web SDK component manual <../../sdk-api>`_.

.. toctree::
   :hidden:

   quickview/index
