.. _webmaps.composer:

Making maps with Composer
=========================

Boundless Suite Composer is a tool for making web maps. Add data to GeoServer, publish layers, and make a map all in a clutter-free, easy-to-use interface.

Also included only with Composer is the ability to style layers with :ref:`YSLD <cartography.ysld>`, a compact, modern syntax that's 100% compatible with SLD.

.. figure:: img/composer.png

   Boundless Suite Composer

This section will detail how to use Composer.

.. warning:: Composer should not be used in a clustered environment. If you do attempt to use Composer with a :ref:`GeoServer cluster <sysadmin.clustering>`, please note that you will need a shared data directory, and be sure to verify any changes made.

.. toctree::
   :maxdepth: 2

   tour
   configuration
   styleview
   keys
   tutorial/index
