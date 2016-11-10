.. _sysadmin.deploy:

Deployment
==========

In addition to being available through packages, Boundless Suite web applications are available individually for use in a custom deployment or on your own application server.

The main benefit of using individual application server bundle is its flexibility. This section will cover installation requirements needed for each application, and explore some common deployment scenarios.

The following checklists for each application can be used as a test during installation. For more detailed step-by-step installation instructions review the :ref:`Tomcat installation instructions <install>` for each supported platform.

Application server strategies
-----------------------------

The main benefit of the application server bundle is its flexibility. It is up to you determine the exact deployment that suits your needs. The following describes some common deployment scenarios.

Sandbox
^^^^^^^

If your application server is configured to sandbox web applications, you will need to grant additional permissions allowing GeoServer and GeoWebCache access to both environmental variables and the file system. These permissions are used to locate the GeoServer data directory.

#. For Tomcat, open the :file:`$CATALINA_BASE/conf/catalina.policy` file and add::

      # Boundless Suite permissions used to access Env Variables and GEOSERVER_DATA_DIRECTORY
      grant codeBase "file:${catalina.base}/webapps/geoserver/WEB-INF/libs/-" {
         permission java.security.AllPermission;
      };
      grant codeBase "file:${catalina.base}/geowebcache/geoserver/WEB-INF/libs/-" {
         permission java.security.AllPermission;
      };

   .. note:: Replace the directories, above, with your WAR install locations.
  
#. Start Tomcat with the ``-security`` option to use :file:`catalina.policy`.

Split GeoServer and GeoWebCache
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

GeoWebCache can act as a proxy between GeoServer and a client. It may be advantageous to place GeoWebCache in an public facing servlet container, as it only hosts images and contains no data. You can then host GeoServer in a non-public facing implementation such that only GeoWebCache can access it. This provides a level of isolation for your data, limiting direct data access. 

Multiple GeoServers
^^^^^^^^^^^^^^^^^^^

It is possible to deploy multiple copies of GeoServer in the same application server. This may be used to implement a "round robin" strategy for handling requests. You could go further and use multiple application servers to host GeoServer instances, making your system more fault tolerant.

For information on this approach see the section on :ref:`sysadmin.clustering`.

Separate PostGIS and GeoServer
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

A recommended installation strategy is to ensure PostGIS and GeoServer are not installed on the same server. This is primarily for security reasons, to prevent PostGIS from being accessed via the web. Give that PostGIS is a separate installation from the WAR bundle, this configuration is straightforward to implement.
