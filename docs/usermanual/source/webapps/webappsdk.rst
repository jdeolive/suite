.. _webapps.sdk:

Creating and deploying apps with the Webapp SDK
===============================================

The OpenGeo Webapp SDK provides tools for building web mapping applications backed by OpenGeo Suite.  The application development life-cycle are as follows:

#. **Creation** - Generating a new template application
#. **Customization** - Adding features and functionality to the template application
#. **Testing** - Deploying the application in a test mode to verify functionality and debug
#. **Deployment** - Deploying the application in a production environment

This tutorial will discuss creating, testing, and deploying of an application.  See the tutorial :ref:`webapps.sdk.dev` for information on customizing your application.

Prerequisites:

* The Webapp SDK installed. See the next section for installation details.

* A `Java Development Kit (JDK) <http://www.oracle.com/technetwork/java/javase/downloads/index.html>`_.  A standard Java Runtime Environment (JRE) is not sufficient.

* `Apache Ant <http://ant.apache.org>`_ installed and on the ``PATH``.  To verify this, type ``ant -version`` at a terminal prompt.

.. _webapps.sdk.install:

SDK Installation
----------------

The SDK can be installed in one of two ways.  The first is as part of a regular OpenGeo Suite install. See the :ref:`installation` section for details.

The second method involves installing the SDK standalone:

#. Downloaded the SDK from http://boundlessgeo.com/solutions/solutions-software/software/. 

#. Extract the archive to a suitable location on the file system.

#. Ensure the SDK :file:`bin` is on the ``PATH``. 

To verify the SDK is installed properly execute the command ``suite-sdk`` from 
a command prompt.


.. _webapps.sdk.create:

Creating a new application
--------------------------

The Webapp SDK comes with an application template that can be useful for getting started developing with the Suite.  To create a new application based on this template, run the ``suite-sdk create`` command::

  suite-sdk create path/to/myapp

In the above command, the app will be called :file:`myapp` and will be placed in the :file:`path/to/myapp` directory.  This directory will contain all required client-side resources for your application.

.. warning:: Be sure to not name your application :file:`geoserver`, :file:`geoexplorer`, :file:`manager`, or any other name that might cause a conflict when :ref:`webapps.sdk.deploy`.

.. _webapps.sdk.debug:

Testing the application
-----------------------

The Client SDK comes with a server that can be used to debug your application during development.  The server loads all of your JavaScript as individual, unminified scripts - very useful for debugging in a browser, but not suitable for production.

Run the following command to launch a server that loads the application in "debug mode"::

  suite-sdk debug path/to/myapp

This server will publish the app at ``http://localhost:9080/`` .  Open this URL in a browser to see your application.

In debug mode, all JavaScript resources are loaded uncompressed to make for easy debugging in the browser.

Typing :command:`Ctrl-C` in the terminal window will shut down the server.

Changing the port
~~~~~~~~~~~~~~~~~

By default, the application will be published on port 9080.  To run the server on another port, provide the ``-l <port>`` option to the debug command::

  suite-sdk debug -l 8000 path/to/myapp

This will make your application available for debugging at ``http://localhost:8000/``.

Accessing GeoServer while testing
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

When deploying the application in OpenGeo Suite, the application will have access to GeoServer at the relative URL of :file:`/geoserver`.  If a GeoServer is not available on that path, or to set up this relationship with a remote GeoServer while testing your application, you can proxy a remote GeoServer to make it look as if it were available locally.  To proxy a GeoServer located at ``http://example.com:8080/geoserver``, run the following command::

  suite-sdk debug -g http://example.com:8080/geoserver path/to/myapp 

This will make your remote GeoServer available locally to your application at the relative URL of :file:`/geoserver`.

Note that the ``-l`` and ``-g`` options can be used together.  For example, you could debug your application on port 8000 while accessing a local GeoServer running on port 8080::

  suite-sdk debug -l 8000 -g http://localhost:8080/geoserver path/to/myapp 

Again, this would make your application available at ``http://localhost:8000/`` while making GeoServer available on the same port at ``http://localhost:8000/geoserver``.

The debug server and proxy are suitable for debugging purposes only.  Use the ``suite-sdk deploy`` command to prepare your application for production.

.. _webapps.sdk.deploy:

Deploying the application
-------------------------

Deploying your application is the process of publishing an application on an OpenGeo Suite instance.  This process will concatenate and minify all JavaScript resources, and then copy them to a remote OpenGeo Suite.

To deploy your application to your (remote) OpenGeo Suite instance, run the following command::

  suite-sdk deploy -d example.com -r 8080 -u <username> -p <password> -c <container> path/to/myapp

.. note::  Type ``suite-sdk deploy --help`` without any arguments to see a full list of possible arguments.

The above command assumes your Suite instance is available at ``http://example.com:8080/``.  Using the ``-u`` and ``p`` options supplies the :ref:`remote manager credentials <webapps.sdk.remotedeploy>` for the remote OpenGeo Suite.

The container type also needs to be supplied by the ``-c`` flag (default is ``tomcat6x``).  See the following table for the default containers for the various installation types.

.. list-table::
   :header-rows: 1

   * - Installation type
     - Container used
     - Syntax
   * - Ubuntu
     - Tomcat 6
     - ``-c tomcat6x``
   * - Red Hat / CentOS 5
     - Tomcat 5
     - ``-c tomcat5x``
   * - Red Hat / CentOS 6
     - Tomcat 6
     - ``-c tomcat6x``
   * - Windows installer
     - Jetty 6
     - ``-c jetty7x``
   * - Mac OS X installer
     - Jetty 6
     - ``-c jetty7x``

For a full list of supported containers and their deployment syntax, please see http://cargo.codehaus.org .

