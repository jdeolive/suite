.. _install.windows.tomcat.docs:

Documentation install
---------------------

Boundless Suite comes with documentation that can be deployed just like an application.

#. From :menuselection:`Start --> Apache Tomcat --> Tomcat Program Directory` navigate to the :file:`webapps` folder.

#. Copy the :file:`suite-docs.war` file from the Boundless WAR bundle into the :file:`webapps` directory.

   .. note:: It will take a few moments for Tomcat to deploy the web application.

#. Navigate to http://localhost:8080/suite-docs to verify that the documentation deployed successfully.

   .. figure:: /install/include/war/img/suite-docs.png
      
      Boundless Suite User Manual