.. _install.windows.tomcat.gwc:

GeoWebCache install
===================

`GeoWebCache <../../../geowebcache>`_ is a map image caching application and server.

.. note:: GeoServer includes an built-in copy of GeoWebCache. Installation of this *separate* stand-alone GeoWebCache may be considered for caching external WMS services. For more information see :ref:`sysadmin.deploy`.

.. note:: During installation we will be editing text files that require Administrator access to modify. We recommend using `Notepad++ <https://notepad-plus-plus.org/>`__, though any text editor will do.

Directory setup
---------------

#. Using :guilabel:`Windows Explorer`, navigate to :file:`C:\\ProgramData\\Boundless\\geowebcache`.

   .. note:: You may need to create the :file:`geowebcache` directories if it doesn't exist.

#. Create the following subdirectories:
   
   * :file:`config` - to store application configuration
   * :file:`tilecache` - for tile cache storage

   .. figure:: img/gwc_dirs.png
  
      GeoWebCache subdirectories

Installation
------------

#. Use :menuselection:`Start --> Apache Tomcat --> Configure Tomcat` to open :guilabel:`Apache Tomcat Properties`. Change to the :guilabel:`General` tab and click :guilabel:`Stop` to stop the service.

#. Use :menuselection:`Start --> Apache Tomcat --> Tomcat Program Directory` to open the program directory. Open the :file:`conf\\Catalina\\localhost\\` directory, and create a :download:`geowebcache.xml <include/geowebcache_WINDOWS.xml>` file:

   .. literalinclude:: include/geowebcache_WINDOWS.xml
      :language: xml

#. From :menuselection:`Start --> Apache Tomcat --> Tomcat Program Directory` navigate to the :file:`webapps` folder.

#. Copy the :file:`geowebcache.war` file from the Boundless WAR bundle into :file:`webapps` to deploy.

#. From the :guilabel:`Apache Tomcat Properties` application, change to the :guilabel:`General` tab and click :guilabel:`Start` to restart Tomcat.

#. Use your browser to open the web application at http://localhost:8080/geowebcache.

   .. figure:: /img/gwc.png
      
      GeoWebCache

#. Confirm the :guilabel:`Storage Locations` are those configured above.
   
   .. figure:: img/gwc_storage_locations.png

      GeoWebCache storage locations

   .. note:: If the :guilabel:`Local storage` or :guilabel:`Config file` are listed in :file:`C:\\Windows\\TEMP\\`, double check that the :file:`C:\\ProgramData\\Boundless\\geowebcache\\tilecache` and :file:`C:\\ProgramData\\Boundless\\geowebcache\\config` directories exist, the :file:`geowebcache.xml` file is correct, and that Tomcat has been restarted.
