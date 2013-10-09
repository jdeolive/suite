.. _webmaps.basic.style:

Style your layers
=================

After uploading your data, the Styling menu will open. While a simple style has been generated for your layer by default, you may wish to customize this style. GeoExplorer provides a tool to create and edit styles.

.. warning:: All style changes will apply and persist on the server immediately. We recommend you make a backup of a style before making any changes.

You can open the Styles dialog box at any time by clicking a layer in the Layer list and then clicking the Palette icon. Alternatively, right-click a layer and click :guilabel:`Layer Styles`.

.. figure:: img/style_contextmenu.png

   *Accessing the Layer Styles editor*

To edit a style for a given layer:

#. In the Styles menu you will see a default Style containing a single Rule. To create a duplicate of this style for modification, click the :guilabel:`Duplicate` button.

.. figure:: img/style_duplicatebutton.png

   *Duplicating the Style*

#. To avoid confusion with the original style, provide a new :guilabel:`Title` and :guilabel:`Abstract` description. Once you've entered this information, click :guilabel:`Save`.

   .. figure:: img/style_metadata.png

      *Style metadata*

   .. figure:: img/style_savebutton.png

      *Click Save when done*

#. Make sure the duplicated Style is selected in the :guilabel:`Choose style` box.

   .. figure:: img/style_styleselect.png

      *Selecting the new style*

#. Click the existing rule in the :guilabel:`Rules` box, and then click the :guilabel:`Edit` button underneath.

   .. figure:: img/style_editrulebutton.png

      *Click this button to edit the style rule*

#. Change the style as you see fit, selecting from a range of options including size, color, opacity, filters, and much more.

   .. note:: Please see the `GeoExplorer Documentation <../../geoexplorer/>`_  for more about what can be styled here.

   .. figure:: img/style_ruleedit.png

      *Editing the style*

   .. figure:: img/style_colorpicker.png

      *Using the color picker* 

#. Click :guilabel:`Save` to persist your changes. To go back to the main Layers List, click the :guilabel:`Layers` title in the Layers Panel at the top where it says :guilabel:`Layers >> <your layer>`.

   .. figure:: img/style_editcomplete.png

      *A new style for the layer*

#. Repeat this process for any layers that you wish to style. To edit styles for another layer, click the layer in the Layers Panel and then click the Palette icon, or right-click the layer and click :guilabel:`Layer Styles` as shown above.

.. note::  If you need a more customized or complex style than what is possible through GeoExplorer, you can code your own style. See the `Styling section <../../geoserver/styling/>`_ of the `GeoServer documentation <../../geoserver/>`_ for more information.

Now that your layers are styled, the next step is to :ref:`webmaps.basic.edit`.
