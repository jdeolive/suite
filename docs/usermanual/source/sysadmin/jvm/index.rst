.. _sysadmin.jvm:

Setting the JVM
======================

This section will discuss JVM (Java) settings for use with Boundless Suite.

We recommend using the Oracle JVM with Boundless Suite, as testing has shown that the Oracle JVM is significantly faster than other JVM implementations.

Specifically, we recommend using **Oracle JRE 8**. The current GeoServer release requires a Java 8 JVM.

Determining the current Java version
------------------------------------

You may wish the verify the version of Java you are currently using.

.. warning:: It is possible to have multiple versions of the JRE installed, so just running ``java -version`` may not accurately reflect the version of the JRE being used by Boundless Suite.

To confirm the version of Java used:

#. Log in to the GeoServer admin interface.

#. Navigate to the :guilabel:`Server Status` page, found on the left side under :guilabel:`About & Status`.

   .. figure:: img/jvm_serverstatuslink.png

      Click to open the Server Status page

#. The JRE used by GeoServer is listed in the :guilabel:`JVM Version` entry.

   .. figure:: img/jvm_version.png
      
      JVM version

Changing to the Oracle JRE
--------------------------

Linux
^^^^^

#. Download the Oracle JRE. In your browser, navigate to `Oracle's download page for JRE 8 <http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html>`_.

#. Click the radio box to accept the license agreement and then click the download link that matches your system::

     jre-8u77-linux-x64.tar.gz

#. Extract the download file contents to your temporary directory or desktop.

#. In a terminal, change to the directory that contains the extracted folder.

#. Move this directory to :file:`/usr/lib/jvm` (or wherever you'd like to place your JRE):

   .. code-block:: console

      sudo mv jre1.8.0_77 /usr/lib/jvm

#. *(Optional)* Change your ``JAVA_HOME`` environment variable to point to this new directory:

   .. code-block:: console

      export $JAVA_HOME=/usr/lib/jvm/jre1.8.0_77
      
#. Ensure your application server is using this new Java. Many application servers will pick up the system ``JAVA_HOME`` environment variable or require that their service definition be modified.

#. If using Boundless Suite packages (or just using Tomcat) open :file:`/etc/tomcat8/tomcat8.conf` in a text editor.

#. Near the top of the file, the ``JAVA_HOME`` variable is set. Change it to:

   .. code-block:: bash
      :emphasize-lines: 3

      # This is the $JAVA_HOME of JDK, not JRE. not needed if you've setup
      # the file "/etc/profile.d/java.sh" with this variable.
      JAVA_HOME=/usr/lib/jvm/jre1.8.0_77

   .. note:: Make sure the line is uncommented (does not start with ``#``).

#. Save and close the file.

#. Restart Tomcat. 

#. Boundless Suite should now be using the new version of Java. Verify in GeoServer by navigating to the Server Status page.

   .. figure:: img/jvm_serverstatusoracle.png

      JVM Version showing Oracle JRE on Linux

#. On the line named :guilabel:`JVM Version`, you should see the Oracle JRE. (For historical reasons, it will be shown as "Java HotSpot.")

.. note:: Read more about :ref:`running Boundless Suite in Production <sysadmin.production>`.

Windows application servers
^^^^^^^^^^^^^^^^^^^^^^^^^^^

If running Boundless Suite in an application server on a Windows system:

#. Download the Oracle JRE. In your browser, navigate to `Oracle's download page for JRE 8 <http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html>`_.

#. Click the radio box to accept the license agreement and then click the download link that matches your system::

     jre-8u77-windows-x64.exe

#. Use the installer (or manual directions) to update Java.
   
   .. warning:: When installing a new Java Runtime Environment from Oracle, pay careful attention to the installation wizard. Oracle has a habit of including unwanted extras such as JavaFX and browser toolbars, so make sure you uncheck everything during the install process.

#. *(Optional)* Change your ``JAVA_HOME`` environment variable to point to this new directory. From the :guilabel:`System` Control Panel select :guilabel:`Advanced System Settings`. From the :guilabel:`System Properties` dialog navigate to the :guilabel:`Advanced Tab` and click :guilabel:`Environment Variables`. Define a System Variable by clicking :guilabel:`New` and entering:
   
   .. list-table::
      :widths: 30 70
      :header-rows: 1

      * - Variable name
        - Variable value
      * - JAVA_HOME
        - :file:`C:\\Program Files\\Java\\jre8`

#. Restart your application server.

#. Make sure that your application server is using this new Java. It may be reading the ``JAVA_HOME`` environment variable, or you may need to consult your application server documentation.

#. Boundless Suite should now be using the new version of Java. Verify in GeoServer by navigating to the Server Status page.

   .. figure:: img/jvm_serverstatuswindows.png
      
      GeoServer Server Status page showing Oracle JRE on Windows

#. On the line named :guilabel:`JVM Version`, you should see the Oracle JRE. (For historical reasons, it will be shown as "Java HotSpot.")

.. note:: Read more about :ref:`running Boundless Suite in Production <sysadmin.production>`.

.. note:: Read more about :ref:`installing Java on Windows <install.windows.tomcat.java>`.

.. _sysadmin.jvm.alternatives:

Setting the default JVM using *update-alternatives*
---------------------------------------------------

If you used linux packages to install different versions of java and are using the Boundless Suite packages you can use the *update-alternatives* command to set the *default* java for your system.  

.. note:: This method may change the java version other applications are using on your server.  This may cause issues.

#. Run the command;

    .. code-block:: bash
      
        update-alternatives --config java

#. You will be presented with a list of installed Java versions.  Choose the one you would like to use.

    .. code-block:: none 

        There are 2 programs which provide 'java'.
        
           Selection    Command
        -----------------------------------------------
         *+ 1           /usr/lib/jvm/jre-1.8.0-openjdk.x86_64/bin/java
            2           /usr/java/jre1.8.0_101/bin/java

        Enter to keep the current selection[+], or type selection number:

#. Verify by running;

   .. code-block:: bash

        java -version

#. Restart Tomcat

