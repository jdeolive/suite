.. _install.windows.tomcat.java:

Installing Java
===============

This page will show how to install the proper version of Java on your system.

#. Boundless Suite on Windows requires a 32-bit Java Runtime Environment, available from the `Oracle Java download page <https://java.com/en/download/manual.jsp>`__.

   .. figure:: img/java_download.png
      
      Java 32-bit Windows download
   
   .. note:: Boundless Suite requires Java 8, and is no longer compatible with Java 7.
   
   .. warning:: While some functionality will work with a 64-bit Java, use of GDAL and NetCDF binaries requires 32-bit Java.

#. Run the installer, agreeing to the license terms.

   .. figure:: img/java_install.png
      
      Java installation welcome page

   .. note:: The installer may ask permission to uninstall prior versions of Java.

#. Wait while Java installs.

   .. figure:: img/java_wait.png
      
      Java installation progress
   
#. When complete, Java will be available in a subdirectory inside :file:`C:\\Program Files (x86)\\Java`. This location will be referred to as ``JRE_HOME`` in subsequent documentation (for example: :file:`C:\\Program Files (x86)\\Java\\jre1.8.0_101`).

   .. figure:: img/java_done.png
      
      Java installation completed
