.. _install.windows.tomcat.geoserver.performance:

GeoServer performance extensions
================================

The following extensions make changes to the GeoServer execution environment unlocking new capabilities and improving performance. These steps are strongly recommended.

Java Cryptography
-----------------

We recommend installing the Java Cryptography Extension Unlimited Strength Jurisdiction Policy File files.

#. Download the :guilabel:`Java Cryptography Extension Unlimited Strength Jurisdiction Policy Files` listed under :guilabel:`Additional Resources` on the Oracle `download page <http://www.oracle.com/technetwork/java/javase/downloads/index.html>`__.
   
   .. figure:: ../img/java_cryptography.png
      
      Java Cryptography Extension (JCE) Policy Files
   
#. Extract the two files (:file:`local_policy.jar` and :file:`US_export_policy.jar`) from the archive into your ``JRE_HOME`` :file:`lib\\security` directory. For example, this directory might be :file:`C:\\Program Files (x86)\\Java\\jre1.8.0_101\\lib\\security`.
   
   .. note:: These files will overwrite the existing policy files.

   .. figure:: ../img/java_cryptography_install.png
      
      Installation of local_policy.jar and US_export_policy.jar

#. Using the Tomcat Manager, start and stop the Tomcat service.

#. Navigate to the GeoServer web application at http://localhost:8080/geoserver and you should see a :guilabel:`Strong cryptography available` message.

Marlin Rasterizer
-----------------

The Marlin renderer is an open source Java rendering engine optimized for performance, based on OpenJDK’s Pisces implementation. With this, vector rendering in GeoServer is much improved over the standard engine.

#. From the Boundless extension bundle, open the :file:`marlin` folder.

#. Copy the :file:`marlin-0.7.3-Unsafe.jar` to your Tomcat :file:`bin` folder. This file will be located in :file:`C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 8\\bin\\marlin-0.7.3-Unsafe.jar`.
   
   .. figure:: ../img/marlin_install.png
      
      Marlin install
      
#. Return to :guilabel:`Apache Tomcat Properties`, switch to the :guilabel:`Java` tab, and add the following additional :guilabel:`Java Options`:
   
   .. warning:: Please adjust the example below to match the location of your version of tomcat (i.e. Tomcat 8.5)
   
   .. literalinclude:: ../include/java_opts.txt
      :language: none
      :start-after: # marlin
      :end-before: # marlin end
  
#. Click :guilabel:`Apply`.

#. Stop and start Tomcat.

#. Navigate to the GeoServer web application and click :guilabel:`Server Status`. To confirm the use of the Marlin Rasterizer, the :guilabel:`Java Rendering Engine` will be listed as ``org.marlin.pisces.PiscesRenderingEngine``.

   .. figure:: ../img/geoserver_marlin.png
      
      Server Status Marlin rendering Engine

LibJPEG Turbo
-------------

The libjpeg-turbo extension provides a significant performance enhancement for JPEG encoding in GeoServer WMS output (up to 40% faster than with no native libraries, equal or greater performance than with Native ImageIO).

#. From the Boundless extension bundle, open the :file:`windows` folder.

#. Double click the :file:`libjpeg-turbo-1.4.2-vc.exe` installer.

#. Install LibJPEG Turbo in the default location (:file:`c:\\libjpeg-turbo`).

#. Add this directory (:file:`c:\\libjpeg-turbo`) to the system PATH:

   .. include:: /install/windows/include/updatePATH.txt

#. Use :menuselection:`Start --> Apache Tomcat --> Configure Tomcat` to open :guilabel:`Apache Tomcat Properties`. Switch to the :guilabel:`Java` tab.

#. Under :guilabel:`Java Options`, add the following line::

     -Djava.library.path=c:\libjpeg-turbo\bin
    

#. Click :guilabel:`Apply`.
     
   .. note::

      If you already have an existing ``-Djava.library.path=`` entry in your :guilabel:`Java Options`, append the new path to the end of the line, separated by a semicolon. For example::

        -Djava.library.path=C:\GDAL;C:\libjpeg-turbo\bin
        
#. Stop and start the Tomcat service.

#. Navigate to the GeoServer web application and login using the admin credentials.

#. Navigate to the GeoServer Detailed Status Page at http://localhost:8080/geoserver/rest/about/status.

#. Search for "libjpeg" on the page and verify it is enabled and available.

   .. figure:: ../../../include/ext/img/libjpeg.png
