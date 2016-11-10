.. _sysadmin.clustering.install:

Installing the clustering extension
===================================

The clustering extension is not installed by default in :ref:`installations <install>` of Boundless Suite. It must be installed separately and then later :ref:`enabled <sysadmin.clustering.setup>`.

.. note:: Prior to installation of the clustering extension, it is important to ensure that you have set up an external data directory, located in a shared location that all GeoServer instances will be able to access.

Install
-------

Installing the clustering extension is the similar to most :ref:`Boundless Suite Extensions <intro.extensions>`. One notable exception is that clustering consists of multiple extensions which must be installed seperately.

Installation instructions are dependent on your operating system and method of install. The JDBCConfig and Clustering extensions are both **required**. JDBCStore is **optional**.

**JDBCConfig**:

* **Tomcat**: :ref:`Ubuntu<install.ubuntu.tomcat.extensions.jdbcconfig>`, :ref:`Red Hat<install.redhat.tomcat.extensions.jdbcconfig>`, :ref:`Windows<install.windows.tomcat.extensions.jdbcconfig>`
* **Packages**: :ref:`Ubuntu<install.ubuntu.packages.list>`, :ref:`Red Hat<install.redhat.packages.list>`

**Clustering**: 

* **Tomcat**: :ref:`Ubuntu<install.ubuntu.tomcat.extensions.clustering>`, :ref:`Red Hat<install.redhat.tomcat.extensions.clustering>`, :ref:`Windows<install.windows.tomcat.extensions.clustering>`
* **Packages**: :ref:`Ubuntu<install.ubuntu.packages.list>`, :ref:`Red Hat<install.redhat.packages.list>`

**JDBCStore**: 

* **Tomcat**: :ref:`Ubuntu<install.ubuntu.tomcat.extensions.jdbcstore>`, :ref:`Red Hat<install.redhat.tomcat.extensions.jdbcstore>`, :ref:`Windows<install.windows.tomcat.extensions.jdbcstore>`
* **Packages**: :ref:`Ubuntu<install.ubuntu.packages.list>`, :ref:`Red Hat<install.redhat.packages.list>`

.. warning:: **JDBCStore** is currently *experimental*, and not recommened for production use.
