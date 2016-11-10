.. _sysadmin.clustering.setup:

Setting up the clustering extension
===================================

After following the steps to install and configure JDBCConfig and Clustering, your GeoServer configuration will be stored in a JDBC database. The following process will detail how to set up an initial cluster from this point. The specific network details will change depending on your system.

.. warning:: Make sure you have set the data directory to be external to the GeoServer installation, in a shared location that all GeoServer instances will be able to access.

.. warning:: Setting up a database for the data directory is a **one-way process**. It is not currently possible to export the configuration back to a file-based data directory.

.. note:: Configuration of the clustering extension, as it involves changes to the data directory, must be done through configuration files. It cannot be done through the UI. It is also not possible to use the REST API to configure the clustering extension.

.. note:: This setup assumes that a load balancer (such as `HAProxy <http://haproxy.1wt.eu/>`_ will be employed in front of GeoServer. The details of the configuration of the load balancer are beyond the scope of this documentation.

Broadcasting setup
------------------

#. Open the file :file:`<data_dir>/cluster/cluster.properties` in a text editor.

#. Replace the lines::

     sync_mode=reload
     sync_delay=5

   with the lines::

     sync_mode=event
     sync_delay=0

#. Save and close this file.

Verification
------------

#. Start GeoServer and log in as an administrator to the web interface.

#. If the clustering extension is working, you will see two messages on the Welcome page: one about JDBCConfig and the other about clustering.

   .. figure:: /install/include/ext/img/jdbcconfig_status.png

      JDBCConfig status

   .. figure:: /install/include/ext/img/cluster_status.png

      Clustering status

#. Confirm that the JDBC message lists the correct JDBC URL. Also confirm that the layers, stores, and other catalog objects have been imported correctly by viewing the list and them in the Layer Preview.

Other nodes
-----------

To configure the other GeoServer nodes, perform the following steps on each:

#. Install GeoServer with the clustering extension. Make sure that it is responding on the same subnet as the initial GeoServer.

   .. note:: Alternately, you can convert the edited GeoServer instance to a WAR and then deploy it.

#. Point the new GeoServer instance to the shared data directory.

#. Restart the new GeoServer. Verify that the extension is working properly and that the node is reading the shared data directory.

Session sharing
---------------

*(Optional but recommended)* HTTP session sharing is not enabled by default. To enable session sharing:

#. Open the file :file:`$CATALINA_HOME/webapps/geoserver/WEB-INF/web.xml` in a text editor.

#. Add this block of text as the first ``filter`` in the file.

   .. warning:: The order is very important here. This must come first. 

   .. code-block:: xml

      <filter>
        <filter-name>hazelcast</filter-name>
        <filter-class>org.geoserver.cluster.hazelcast.web.HzSessionShareFilter</filter-class>
      </filter>

#. Add the following block of text as the very first ``filter-mapping`` in the file.

   .. warning:: Again, the order is very important.

   .. code-block:: xml

      <filter-mapping>
        <filter-name>hazelcast</filter-name>
        <url-pattern>/*</url-pattern>
      </filter-mapping>

#. Add the following block of text in the ``listener`` section. The order is not important here.

   .. code-block:: xml

    <!-- hazelcast session listener -->
    <listener>
      <listener-class>org.geoserver.cluster.hazelcast.web.HzSessionShareListener</listener-class>
    </listener>

#. Restart GeoServer.

Repeat this for each GeoServer in the cluster.

Log separation
--------------

By default, a cluster will end up collecting all the log output from all the nodes into a single file without indicating which message came from which node.

If this is not desired, you can split the logs into files distinct to each node. This property can be set via the standard methods of a JVM system variable, environment variable, or servlet context parameter.

The variable to set is called ``GEOSERVER_LOG_LOCATION``::

  GEOSERVER_LOG_LOCATION=[log_location]

Or, as set in :file:`web.xml`:

.. code-block:: xml

   <context-param>
     <param-name>GEOSERVER_LOG_LOCATION</param-name>
     <param-value>[log_location]</param-value>
   </context-param> 

For example, on GeoServer node #1, you could set the following variable::

  GEOSERVER_LOG_LOCATION=logs/geoserver_node1.log

For node #2::

  GEOSERVER_LOG_LOCATION=logs/geoserver_node2.log

This way, each node will have its own log in the shared data directory, making administration and troubleshooting easier.

Final verification
------------------

To verify that the cluster is set up correctly, perform the following steps:

#. If you enabled session sharing, log in to GeoServer through the load balancer, shut down the node that handled the login request, then make subsequent requests and verify that you are still logged in.

#. On the first instance, view a layer (through the Layer Preview).

#. On the second instance, make a change to the layer, such as one that will affect its visualization or metadata (Title or Abstract). Save this change.

#. Verify that the change has propagated back to the first instance.

   .. note:: Perform this step quickly so as to ensure that it is the clustering extension that is working as expected, and not just as a result of cache expiration.

Repeat these steps for other instances until all nodes in the cluster have been tested against one other node.

