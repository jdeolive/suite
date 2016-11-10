.. _install.windows.tomcat.tomcat:

Installing Tomcat
=================

.. note:: During installation we will be editing text files that require Administrator access to modify. We recommend using `Notepad++ <https://notepad-plus-plus.org/>`__, though any text editor will do.

The Apache Tomcat application server is used to host the Boundless Suite web applications. Tomcat itself is a Java application, and some care will be required to configure it appropriately for use.

#. Download the :guilabel:`32-bit/64-bit Windows Service Installer` from the `Tomcat website <http://tomcat.apache.org/download-80.cgi>`_:
   
   .. figure:: img/tomcat_download.png
      
      Tomcat Service Installer download
   
   .. warning: Boundless Suite requires Tomcat version 7 or above. It must support "Servlet Spec 3.0".
   
#. Run the Tomcat installer:

   .. figure:: img/tomcat_install.png
      
      Apache Tomcat installer welcome page

#. Accept the license:

   .. figure:: img/tomcat_license.png
      
      Tomcat license page
      
#. Select :guilabel:`Tomcat` from the list of components, and make sure that :guilabel:`Service Startup` and :guilabel:`Native` components are checked.

   .. figure:: img/tomcat_components.png
   
      Service Startup and Native components

#. Supply a :guilabel:`User Name` and :guilabel:`Password` used for Tomcat administrator login. This will be used for the Tomcat Manager application.

   .. figure:: img/tomcat_config.png
   
      User Name and Password configuration
   
   .. note:: The account will be given the Tomcat administrator role ``manager-gui``, which is required to interact with the :guilabel:`Tomcat Manager` application, used to monitor Boundless Suite web applications and the Tomcat server.

#. If requested, browse to the location of the Java 8 JRE installed previously.

   .. figure:: img/tomcat_jre.png
   
      Java Runtime Environment
      
#. Accept the default Tomcat install location and click :guilabel:`Install` to proceed.

   .. figure:: img/tomcat_location.png
   
      Install location

#. When installation is completed click :guilabel:`Finish` to run the application.

   .. figure:: img/tomcat_done.png
   
      Completing Tomcat setup

#. To confirm the application is working, navigate in your browser to `http://localhost:8080/ <http://localhost:8080>`__. You should see the Welcome page.

   .. figure:: img/tomcat_welcome.png
      
      Tomcat welcome
      
   .. note:: You can also open the welcome page using :menuselection:`Start --> Apache Tomcat --> Welcome`.

#. After the service has started you can monitor application status using the :guilabel:`Monitor Tomcat` applicationl, available at :menuselection:`Start --> Apache Tomcat --> Monitor Tomcat`.

   .. note:: The application is also availabe in the System Tray:

      .. figure:: img/tomcat_taskbar.png
      
         Monitor Tomcat

#. Configure the Tomcat application using the :guilabel:`Configure Tomcat` application, available at :menuselection:`Start --> Apache Tomcat --> Configure Tomcat`.

    .. figure:: img/tomcat_properties.png
       
       Configure Tomcat
    
#. Change to the the :guilabel:`Java` tab to configure available memory:
    
    * Set :guilabel:`Initial memory pool` to 256 MB
    * Set :guilabel:`Maximum memory pool` to 756 MB
    
    .. figure:: img/tomcat_memory.png

       Available memory

    .. note:: You may wish to increase the above memory settings, especially when working with raster data, or if you encounter memory issues.

#. Append the following additional :guilabel:`Java Options` to optimize memory management for the larger requests needed when working with geospatial data.
    
   .. literalinclude:: include/java_opts.txt
      :language: none
      :start-after: # memory
      :end-before: # memory end
    
   .. figure:: img/tomcat_optimize.png
      
      Java Options
       
#. Click :guilabel:`Apply` to save the configuration.

#. Switch to the :guilabel:`General` tab. :guilabel:`Stop` then :guilabel:`Start` the service with these new settings.

#. Navigate to http://localhost:8080/manager/html to launch the :guilabel:`Tomcat Manager`. Use the user name and password chosen during the installation process.
    
   .. figure:: img/tomcat_login.png

   .. note:: This application is also available at :menuselection:`Start --> Apache Tomcat --> Tomcat Manager`.

   .. note::

      If you didn't create an administrator user name and password during the installation process, you can do this manually:
       
      #. Navigate to :menuselection:`Start --> Apache Tomcat --> Tomcat Program Directory`.

      #. Open the :file:`config` directory and edit the :file:`tomcat-users.xml` with an additional user::
           
           <user username="admin" password="******" roles="manager-gui" />

         making sure to replace the ``******`` with your password.

      #. Save the file.

      If you encounter issues logging in, please ensure that your edits to the file are not contained inside a comment block.

       
#. View the Tomcat Manager.

    .. figure:: img/tomcat_manager.png
       
       Tomcat Web Application Manager
