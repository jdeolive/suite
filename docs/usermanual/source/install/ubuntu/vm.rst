.. _install.ubuntu.vm:

Virtual machine install on Ubuntu
=================================

This section will install the Boundless Suite virtual machine on an Ubuntu host.

Installation
------------

.. include:: ../include/vbox/setup_intro.txt

#. To install **VirtualBox** open ``Terminal`` by clicking on the Ubuntu icon in the top left hand corner of the screen, and search for Terminal.

    .. figure:: img/terminal_search_ubuntu.png

        Opening Terminal

#. Execute the following command inside the console:

    .. code-block:: console

        $ sudo apt-get install virtualbox

#. Information about the packages which are required or that will be modified will be displayed. Enter 'Y' in order to accept and proceed with the installation.

    .. figure:: img/apt-get_cmd_ubuntu.png

        Terminal output during install

#. After installation, run VirtualBox. Navigate to :menuselection:`File --> Import Appliance`.

   .. figure:: img/import_appliance_ubuntu.png

      Import Appliance link

#. Select the Boundless Suite virtual machine file.

#. Details about the virtual machine will be displayed. Click :guilabel:`Import`.

   .. figure:: img/vm_settings_ubuntu.png

      Import Appliance menu

#. The Boundless Suite license agreement will display. Click :guilabel:`Agree` to accept.
   
   .. figure:: img/vm_license_ubuntu.png

      Boundless Suite license

#. You will now see the :guilabel:`Boundless Suite` entry in the list of virtual machines in VirtualBox.

   .. figure:: /install/include/vbox/img/vbox_manager.png

      VirtualBox Manager showing Boundless Suite virtual machine

#. Click to select the virtual machine and then click :guilabel:`Shared Folders`.

   .. figure:: /install/include/vbox/img/vbox_sharedfolderlink.png

      Accessing the shared folder menu

#. In order to facilitate copying files from your host system to the virtual machine, we recommend creating a shared folder such that any files copied to that folder  will be accessible inside the virtual machine. Right-click the blank area of the dialog and select :guilabel:`Add shared folder` (or press :kbd:`Insert`).

   .. figure:: img/sharedfolder_ubuntu.png

      Link to add a new shared folder

#. Fill out the form:

   * For :guilabel:`Folder Path`, select a directory on the host machine that will serve as the shared folder. One good option for this directory would be the Desktop.
   * For :guilabel:`Folder Name`, enter :kbd:`share`. 
   * Check :guilabel:`Auto-mount`.

   .. figure:: img/sharedfolder_settings_ubuntu.png

      Setting a shared folder

#. When finished, click :guilabel:`OK`, then click :guilabel:`OK` again to close the :guilabel:`Settings` page.

Post-installation
-----------------

A few more steps are required before you are ready to proceed.

Start the virtual machine
^^^^^^^^^^^^^^^^^^^^^^^^^

.. include:: ../include/vbox/check_start.txt

Terminal setup
^^^^^^^^^^^^^^

.. include:: ../include/vbox/check_terminal.txt

Using the shared folder
^^^^^^^^^^^^^^^^^^^^^^^

.. include:: ../include/vbox/copy_shared_file.txt

Create a snapshot
^^^^^^^^^^^^^^^^^

.. include:: ../include/vbox/check_snapshot.txt

Extensions
^^^^^^^^^^

.. include:: ../include/vbox/extensions.txt

Virtual Machine reference
^^^^^^^^^^^^^^^^^^^^^^^^^

.. include:: ../include/vbox/check_reference.txt
