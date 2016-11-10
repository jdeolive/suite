.. _sysadmin.jaiext:

JAI-EXT
=======

The `JAI-EXT <https://github.com/geosolutions-it/jai-ext>`_ library is open-source project which aims to replace closed source JAI project provided by Sun. The main difference between *JAI* and *JAI-EXT* operations is the support for external **Region of Interests** (ROI) and image **NoData** in *JAI-EXT*.

If you are using the :ref:`Ubuntu<install.ubuntu.packages>` or :ref:`Red Hat<install.ubuntu.packages>` packages, or the Virtual machine, **JAI-EXT** operations will be enabled by default, and can be controlled from Image Processing page:

.. figure:: img/JAIEXT.png
   
   JAI/JAIEXT Setup panel

If you are using the Boundless Suite application server (WAR) bundle, **JAI-EXT** operations will be disabled. Refer to the `GeoServer documentation <../../geoserver/configuration/image_processing/index.html#jai-ext>`_ for instructions on how to enable **JAI-EXT**.
