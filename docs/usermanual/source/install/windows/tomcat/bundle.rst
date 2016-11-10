.. _install.windows.tomcat.bundle:

Boundless Suite bundles
=======================

There are two bundles (files) that comprise a Boundless Suite installation for an application server:

* **WAR bundle**, containing applications and a sample GeoServer data directory
* **Extension bundle**, containing optional extensions to add functionality to GeoServer

WAR bundle
----------

#. Download the :file:`BoundlessSuite-war.zip` file.

   .. note:: You can get this file through Boundless Connect.

#. Extract the contents by right-clicking the file and selecting :menuselection:`Extract All`.

   .. figure:: img/war_extract.png
      
      Extract all

#. Extract to the default :file:`Download` folder location.

   .. figure:: img/war_contents.png
      
      Boundless WAR bundle

#. In addition to web applications, a sample GeoServer data directory in the file :file:`suite-data-dir.zip` is included for the GeoServer installation.

Extension bundle
----------------

GeoServer extensions are included in a separate bundle. Each extension is included in its own folder in the bundle. Please see the section on extensions as the installation instructions differ for each extension.

#. Download the :file:`BoundlessSuite-ext.zip` file.

   .. note:: You can get this file through Boundless Connect.

#. Extract the contents by right-clicking the file and selecting :menuselection:`Extract All`.

#. Extract to the default :file:`Download` folder location.
