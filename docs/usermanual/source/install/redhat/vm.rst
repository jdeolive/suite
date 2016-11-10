.. _install.redhat.vm:

Virtual machine install on Red Hat
==================================

This section will install the Boundless Suite virtual machine on a Red Hat-based Linux host.

Installation
------------

.. include:: ../include/vbox/setup_intro.txt

#. To install **VirtualBox** open ``Terminal``. Navigate to :menuselection:`Applications --> System Tools --> Terminal`.

    .. figure:: img/terminal_centos.png

        Opening Terminal

#. Execute the following sequence of commands into the console:

    .. code-block:: console

        $ cd /etc/yum.repos.d/
        $ wget http://download.virtualbox.org/virtualbox/rpm/rhel/virtualbox.repo
        $ rpm -Uvh http://download.fedoraproject.org/pub/epel/6/x86_64/epel-release-6-8.noarch.rpm
        $ yum install gcc make patch  dkms qt libgomp
        $ yum install kernel-headers kernel-devel fontforge binutils glibc-headers glibc-devel
        $ export KERN_DIR=/usr/src/kernels/2.6.32-504.3.3.el6.x86_64
        $ yum install VirtualBox-5.1
        $ service vboxdrv setup
        $ virtualbox &

    .. note:: 
        * You may need administrative / super-user privileges for some of these commands

#. After installation, run VirtualBox. Navigate to :menuselection:`File --> Import Appliance`.

   .. figure:: img/import_appliance_centos.png

      Import Appliance link

#. Select the Boundless Suite virtual machine file.

#. Details about the virtual machine will be displayed. Click :guilabel:`Import`.

   .. figure:: img/appliance_settings_centos.png

      Import Appliance menu

#. The Boundless Suite license agreement will display. Click :guilabel:`Agree` to accept.
   
   .. figure:: img/vb_license_centos.png

      Boundless Suite license

#. You will now see the :guilabel:`Boundless Suite` entry in the list of virtual machines in VirtualBox.

   .. figure:: img/vb_centos.png

      VirtualBox Manager showing Boundless Suite virtual machine

#. Click to select the virtual machine and then click :guilabel:`Shared Folders`.

   .. figure:: /install/include/vbox/img/vbox_sharedfolderlink.png

      Accessing the shared folder menu

#. In order to facilitate copying files from your host system to the virtual machine, we recommend creating a shared folder such that any files copied to that folder  will be accessible inside the virtual machine. Right-click the blank area of the dialog and select :guilabel:`Add shared folder` (or press :kbd:`Insert`).

   .. figure:: img/shared_menu_centos.png

      Link to add a new shared folder

#. Fill out the form:

   * For :guilabel:`Folder Path`, select a directory on the host machine that will serve as the shared folder. One good option for this directory would be the Desktop.
   * For :guilabel:`Folder Name`, enter :kbd:`share`. 
   * Check :guilabel:`Auto-mount`.

   .. figure:: img/shared_settings_centos.png

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
