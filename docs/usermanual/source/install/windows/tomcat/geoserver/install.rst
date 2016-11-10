.. _install.windows.tomcat.geoserver.install:

GeoServer WAR install
=====================

The next step is to install the GeoServer web application.

#. Use :menuselection:`Start --> Apache Tomcat --> Configure Tomcat` to open :guilabel:`Apache Tomcat Properties`. Switch to the :guilabel:`General` tab and click :guilabel:`Stop` to stop the service
   
   .. figure:: ../img/tomcat_stop.png
      
      Stopping Tomcat service
      
#. Switch to the :guilabel:`Java` tab and add the following to the :guilabel:`Java Options`:
  
   .. literalinclude:: ../include/java_opts.txt
      :language: none
      :start-after: # geoserver
      :end-before: # geoserver end
     
#. Click :guilabel:`Apply`.

   .. figure:: ../img/geoserver_system_properties.png
   
      Java system properties

#. Use :menuselection:`Start --> Apache Tomcat --> Tomcat Program Directory` to open the program directory. You can also use :guilabel:`Windows Explorer` to open the Tomcat Program Directory (such as: :file:`C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 8.5`)
   
   .. figure:: ../img/tomcat_program_directory.png
   
      Tomcat program directory

#. Use :guilabel:`Windows Explorer` to open the :file:`conf\\Catalina\\localhost\\` directory, and create a :download:`geoserver.xml <../include/geoserver_WINDOWS.xml>` with the following content:
   
   .. literalinclude:: ../include/geoserver_WINDOWS.xml
      :language: xml
   
   .. note:: When upgrading from OpenGeo Suite you can make  use of your existing ``GEOSERVER_DATA_DIRECTORY`` location with a different :download:`geoserver.xml <../include/geoserver_upgrade_WINDOWS.xml>`.
   
      .. literalinclude:: ../include/geoserver_upgrade_WINDOWS.xml
         :language: xml

#. Create the folder :file:`C:\\ProgramData\\Boundless\\geoserver\\tilecache` as referenced above.

     .. figure:: ../img/geoserver_maindir.png

        Creating the tilecache folder

#. Use :menuselection:`Start --> Apache Tomcat --> Tomcat Program Directory` to open the program directory, then open the :file:`webapps` directory.

#. Copy the :file:`geoserver.war` into the :file:`webapps` folder to deploy it.

   .. figure:: ../img/geoserver_deploy.png
       
      Deploy of GeoServer
   
   .. note:: The :file:`geoserver.war` file is included in the Boundless Suite WAR bundle (see :ref:`install.windows.tomcat.bundle`).

#. Change to the :guilabel:`General` tab and start the service using the :guilabel:`Start` button.
   
   .. figure:: ../img/tomcat_start.png
      
      Start Tomcat Service
      
#. Tomcat will deploy the :file:`geoserver.war` into a :guilabel:`geoserver` folder visible in the :file:`webapps` folder.

   .. note:: It will take a few moments for Tomcat to complete this process.

#. Navigate to http://localhost:8080/geoserver.

#. Log in at the top of the screen by filling in the admin credentials (which are by default :kbd:`admin` / :kbd:`geoserver`):

   .. figure:: ../img/geoserver_login.png
       
      Login to GeoServer application
      
#. Click :guilabel:`Server Status`.

   .. figure:: ../img/geoserver_status.png
       
      Page navigation

#. Confirm that the :guilabel:`Data directory` is correct.

   .. figure:: ../img/geoserver_status_page.png
      
      Server Status showing data directory
      
   .. note:: If the data directory is incorrectly located in :file:`webapps/geoserver/data` the ``GEOSERVER_DATA_DIR`` setting has not taken effect. Double check the :file:`geoserver.xml` file, and confirm that the service has restarted.

#. Navigate to http://localhost:8080/geoserver/gwc and ensure that the :guilabel:`Local Storage` value is set to the tilecache directory set above.

   .. image:: ../img/embedded_gwc.png

.. warning:: It is strongly recommended to change the GeoServer master password.  Please see :ref:`sysadmin.security.masterpwd` for more details on how to do this. Failure to change this password could cause a security issue with your GeoServer instance. 