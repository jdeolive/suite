.. _install.windows.vm:

Virtual machine install on Windows
==================================

.. warning:: The virtual machine install of Suite is **not** recommended for production.

This section will install the Boundless Suite virtual machine on a Windows host.

Installation
------------

.. include:: ../include/vbox/setup_intro.txt

#. Install `VirtualBox <https://www.virtualbox.org/wiki/Downloads>`__. You may keep all defaults during the install.

   .. figure:: img/welcome.png

      Installing VirtualBox

   .. note:: You may see a warning about network interfaces being temporarily disabled. This is okay.

      .. figure:: img/warningnetwork.png

         Warning about network interfaces

#. After installation, run VirtualBox. Navigate to :menuselection:`File --> Import Appliance`.

   .. figure:: ../include/vbox/img/vbox_importlink.png

      Import Appliance link

Import the VM
^^^^^^^^^^^^^

.. include:: ../include/vbox/setup.txt

#. Fill out the form:

   * For :guilabel:`Folder Path`, select a directory on the host machine that will serve as the shared folder. One good option for this directory would be the Desktop.
   * For :guilabel:`Folder Name`, enter :kbd:`share`. 
   * Check :guilabel:`Auto-mount`.

   .. figure:: img/sharedfolder.png

      Setting a shared folder

#. When finished, click :guilabel:`OK`, then click :guilabel:`OK` again to close the :guilabel:`Settings` page.

Post-installation
-----------------

A few more steps are required before you are ready to proceed.

Start the virtual machine
^^^^^^^^^^^^^^^^^^^^^^^^^

It is important to test that the virtual machine installed in the previous exercise is running correctly.

#. Start the virtual machine by clicking the :guilabel:`Start` toolbar button.

   .. note:: "Normal Start" will spawn a console window, while "Headless Start" will not. We recommend using "Headless Start" and interacting with the server via a local SSH connection.

   .. figure:: ../include/vbox/img/vbox_headless.png

      Headless Start

   .. note:: Occasionally, the virtual machine can not be started. In many cases, this can be solved by going into your machine's BIOS and enabling **hardware virtualization**. Please check with your hardware manufacturer for information on how to enable this.

#. If you see any Windows Firewall warnings, you may accept them.

#. It may take a few minutes for the virtual machine to load. You will know that the virtual machine is ready when you see the preview pause and ask for a login:

   .. figure:: ../include/vbox/img/vbox_preview.png

      Login screen on the preview pane in VirtualBox

   .. note:: If you chose :guilabel:`Normal Start`, a console window will be opened. This window captures keyboard and mouse input, which can be a hindrance to working with the virtual machine.
      
      * If you just see a blank screen, click in the window and press :kbd:`Enter`.

      * If you ever lose your mouse or are unable to type, press the :kbd:`Right Ctrl` key to reclaim focus back from the virtual machine.
    
#. Once you see the above screen, open a browser and navigate to http://localhost:8080/dashboard. You should see the Boundless Suite Dashboard.

   .. figure:: /intro/img/dashboard.png

      Boundless Suite Dashboard

Terminal setup
^^^^^^^^^^^^^^

Most interaction with Boundless Suite will be done through a browser, but occasionally we will want to run commands directly on the virtual machine. To do this, we will use a terminal client.

.. note:: Use :guilabel:`Headless Start` to prevent the virtual machine console from even being shown.

We will use the following connection parameters to connect to the virtual machine via the terminal:

* **Host name**: ``localhost``
* **Port**: ``2020``
* **User**: ``root``
* **Password**: ``boundless123``

For **Windows** systems, we recommend using the `PuTTY <http://the.earth.li/~sgtatham/putty/latest/x86/putty.exe>`_ terminal utility.

#. Double-click ``putty.exe``.

#. In the :guilabel:`Host Name (or IP address)` box, enter :kbd:`localhost`.

#. In the :guilabel:`Port` box, enter :kbd:`2020`.

   .. figure:: ../include/vbox/img/vbox_puttyconfig.png

      Configuring PuTTY for console access

#. Click :guilabel:`Open`.

#. When asked for a user name, enter :kbd:`root` and press :kbd:`Enter`.

#. When asked for a password, enter :kbd:`boundless123` and press :kbd:`Enter`.

#. Check the shared directory to confirm that files can be shared between the host and the virtual machine:

   .. code-block:: console

      cd /media/sf_share
      ls -l

   You should see the contents of your shared directory.

   .. note:: If you named your shared directory something other than "share", the shared folder will be named :file:`/media/sf_<name>`.

Using the shared folder
^^^^^^^^^^^^^^^^^^^^^^^

.. include:: ../include/vbox/copy_shared_file.txt

Create a snapshot
^^^^^^^^^^^^^^^^^

.. include:: ../include/vbox/check_snapshot.txt

.. _install.windows.vm.extensions:

Extensions
^^^^^^^^^^

.. include:: ../include/vbox/extensions.txt



Virtual Machine reference
^^^^^^^^^^^^^^^^^^^^^^^^^

.. include:: ../include/vbox/check_reference.txt
