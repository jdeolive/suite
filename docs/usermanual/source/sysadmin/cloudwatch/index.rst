.. _sysadmin.cloudwatch:

Monitoring GeoServer with CloudWatch
====================================

Amazon CloudWatch is a monitoring service provided for use on Amazon Web Services. GeoServer maintains performance and monitoring information that can be integrated with CloudWatch.

This section will describe how to send those GeoServer performance metrics to CloudWatch.

.. note:: This tutorial assumes familiarity with Amazon CloudWatch For more information, please see the `CloudWatch documentation <http://aws.amazon.com/cloudwatch/>`_.


Installation
------------

Amazon CloudWatch support isn't enabled by default, so it must be separately installed.

Installing Amazon CloudWatch support is the same as most :ref:`Boundless Suite Extensions <intro.extensions>`.

Installation instructions are dependent on your operating system and method of install:

* **Tomcat**: :ref:`Ubuntu<install.ubuntu.tomcat.extensions.cloudwatch>`, :ref:`Red Hat<install.redhat.tomcat.extensions.cloudwatch>`, :ref:`Windows<install.windows.tomcat.extensions.cloudwatch>`
* **Packages**: :ref:`Ubuntu<install.ubuntu.packages.list>`, :ref:`Red Hat<install.redhat.packages.list>` 

.. note:: The Boundless Suite virtual machine has most extensions pre-installed.


Configuration
-------------

Cloudwatch has a number of configuration parameters used to control the extension.

These can be configured in the standard three ways: by setting environment variables, by employing Java command line parameters, or by adding system parameters to the GeoServer :file:`web.xml` file. (See more on :ref:`sysadmin.startup`.)

The following are the parameters, along with their description:


.. list-table::
   :class: non-responsive
   :header-rows: 1
   :stub-columns: 1

   * - Variable
     - Description
     - Suggested Value
   * - ``GS_CW_INTERVAL``
     - Interval in milliseconds at which to send metrics
     - ``10000``
   * - ``AWS_ACCESS_KEY``
     - AWS Access Key
     - Credentials specific to the AWS account. To generate AWS credentials, please see the `AWS documentation <http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSGettingStartedGuide/AWSCredentials.html>`_.
   * - ``AWS_SECRET_KEY``
     - AWS Secret Key
     - Credentials specific to the AWS account. To generate AWS credentials, please see the `AWS documentation <http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSGettingStartedGuide/AWSCredentials.html>`_.
   * - ``GS_CW_ENABLE_PER_INSTANCE_METRICS``
     - Instance-specific metrics. If disabled, ``GS_CW_AUTOSCALING_GROUP_NAME`` should be populated.
     - ``true`` or ``false``
   * - ``GS_CW_INSTANCE_ID``
     - Overrides the instance identifier
     - Any name, such as ``testgroup``
   * - ``GS_CW_AUTOSCALING_GROUP_NAME``
     - Use if the server is part of an EC2 autoscaling group.
     - Name of the group
   * - ``GS_CW_JMX``
     - JMX metrics
     - ``true`` or ``false``
   * - ``GS_CW_WATCH_WMS``
     - Monitors the WMS
     - ``true`` or ``false``
   * - ``GS_CW_WATCH_WFS``
     - Monitors the WFS
     - ``true`` or ``false``
   * - ``GS_CW_WATCH_WPS``
     - Monitors the WPS
     - ``true`` or ``false``
   * - ``GS_CW_WATCH_WCS10``
     - Monitors the WCS version 1.0
     - ``true`` or ``false``
   * - ``GS_CW_WATCH_WCS111``
     - Monitors the WCS version 1.1.1
     - ``true`` or ``false``
   * - ``GS_CW_WATCH_WCS20``
     - Monitors the WCS version 2.0
     - ``true`` or ``false``
   * - ``GS_CW_WATCH_OWS``
     - Monitors the OWS
     - ``true`` or ``false``
   * - ``GS_CW_WATCH_WCS20``
     - Monitors the Catalog service
     - ``true`` or ``false``

Each of the enabled OWS watchers will produce three metrics.

- The number of requests per second.
- The number of errors per second.
- The median processing time per request (windowed over approximately five minutes).
