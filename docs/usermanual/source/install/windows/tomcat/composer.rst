.. _install.windows.tomcat.composer:

Composer install
================

:ref:`Composer<webmaps.composer>` is a tool for creating and styling web maps.

.. note:: You must install GeoServer prior to installing Composer.

#. From :menuselection:`Start --> Apache Tomcat --> Tomcat Program Directory` navigate to the :file:`webapps` folder.

#. Copy the :file:`composer.war` file from the Boundless WAR bundle into the :file:`webapps` directory.

   .. note:: It will take a few moments for Tomcat to deploy the web application.

#. Navigate to http://localhost:8080/composer to verify that the application deployed successfully.

#. Log in using the GeoServer admin credentials (default is ``admin`` / ``geoserver``).
   
   .. figure:: /install/include/war/img/composer_login.png
      
      Login to Composer application