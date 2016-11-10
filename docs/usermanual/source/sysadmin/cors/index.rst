.. _sysadmin.cors:

How to enable Cross-Origin Resource Sharing (CORS)
==================================================

The Same Origin Policy enforced by browsers is designed to prevent a malicious script from one server being able to access sensitive data on a different server.

But if you want your GeoServer to be usable outside of your own domain, you will want to enable Cross-Origin Resource Sharing (CORS).

.. note:: These instructions will cover Tomcat only.

#. Edit the file :file:`$CATALINA_BASE/conf/web.xml` (typically :file:`/etc/tomcat8/web.xml`) .

#. Add the following filter:
   
   .. code-block:: xml
   
      <filter>
        <filter-name>CorsFilter</filter-name>
        <filter-class>org.apache.catalina.filters.CorsFilter</filter-class>
      </filter>
      <filter-mapping>
        <filter-name>CorsFilter</filter-name>
        <url-pattern>/*</url-pattern>
      </filter-mapping>

#. Save the file and restart the server.

For more information see:
   
* http://tomcat.apache.org/tomcat-8.0-doc/config/filter.html#CORS_Filter
* http://enable-cors.org/server_tomcat.html
