.. _sysadmin.mapmeter.activate:

Activating Mapmeter
===================

Enterprise clients get full access to Mapmeter. In addition, all users are eligible to create a free two-week trial account of Mapmeter. 

Trial account
-------------

If you are not an Enterprise client and would like to try out Mapmeter, you can activate your free trial. This is done from within the geoServer web admin interface.

#. Make sure the Mapmeter extension is properly :ref:`installed <sysadmin.mapmeter.install>`.

#. Click the :guilabel:`Mapmeter` link.

   .. figure:: img/install_mapmeterlink.png

      Mapmeter link

#. On the Mapmeter page, click :guilabel:`Activate Mapmeter trial`.

#. The page will refresh, and you will be given an API key. This key uniquely identifies this GeoServer instance to the Mapmeter service. Do not use it with any other GeoServer instance.

   .. figure:: img/activate_newkey.png

      Trial activated

#. On the GeoServer Welcome page, GeoServer will display a very basic chart displaying the number of requests on the administration home page. This chart will display for the duration of the trial period.

   .. figure:: img/activate_webadminchart.png

      Usage chart in GeoServer

#. There is much more usage information available on the Mapmeter site. To access Mapmeter, you'll need to convert your anonymous account by filling out the form, including a valid email address and password.

   .. figure:: img/activate_convertanonymous.png

      Converting anonymous account

#. When finished, click :guilabel:`Save`.

#. Now log on to http://app.mapmeter.com using the credentials supplied in the previous step.

   .. figure:: img/activate_signin.png

      Sign in page for Mapmeter

#. You will be asked to complete your profile. Enter your name, phone number and other profile information and click :guilabel:`Continue`.

   .. figure:: img/activate_profilecomplete.png

      Completing your profile

#. To finish activating your account, enter the Activation Code that you received via email.

   .. figure:: img/activate_code.png

      Activate account

Your account is now activated! You are now free to explore Mapmeter for the duration of the trial period. you'll be taken to the Settings pages, but click the Mapmeter logo at the top to be returned to the main Mapmeter screen.


Existing Mapmeter account
-------------------------

If you have an existing Mapmeter account, you can access that account and bypass the free trial.

#. Make sure the Mapmeter extension is properly :ref:`installed <sysadmin.mapmeter.install>`.

#. Click the :guilabel:`Mapmeter` link.

   .. figure:: img/install_mapmeterlink.png

      Mapmeter link

#. On the Mapmeter page, paste the API key of your GeoServer instance directly into the :guilabel:`API Key` field.

#. Click :guilabel:`Validate`.

   .. figure:: img/activate_validate.png

      Validating the API key

#. Once the key validates, click :guilabel:`Save` and your GeoServer instance will be connected to your account.

