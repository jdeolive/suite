package org.opengeo.bundle;

import org.apache.commons.io.FileUtils;
import org.geoserver.catalog.Catalog;
import org.geoserver.catalog.FeatureTypeInfo;
import org.geoserver.catalog.LayerInfo;
import org.geoserver.catalog.WorkspaceInfo;
import org.geoserver.catalog.impl.CatalogImpl;
import org.geoserver.config.GeoServerPersister;
import org.geoserver.config.util.XStreamPersisterFactory;
import org.geoserver.platform.GeoServerResourceLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opengis.feature.type.FeatureType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BundleImporterTest extends BundleTestSupport {

    BundleImporter importer;

    @Test
    public void testSimple() throws Exception {
        new CatalogCreator(cat).workspace("foo")
            .property("bar")
            .featureType("stuff", "geom:Point:srid=4326,name:String,id:Integer", stuff()).layer();

        Catalog cat2 = createCatalog();
        new CatalogCreator(cat2).workspace("bam");

        doImport(doExport("foo"), "bam", cat2);

        LayerInfo stuff = cat2.getLayerByName("bam:stuff");
        assertNotNull(stuff);

        assertNotNull(stuff.getDefaultStyle());
        assertNotNull(stuff.getResource());

        assertNotNull(((FeatureTypeInfo)stuff.getResource()).getFeatureSource(null, null));
    }

    @Test
    public void testLayerWithOtherStyles() throws Exception {
        new CatalogCreator(cat).workspace("foo")
                .property("bar")
                .featureType("stuff", "geom:Point:srid=4326,name:String,id:Integer", stuff())
                    .layer()
                    .style(false);

        Catalog cat2 = createCatalog();
        new CatalogCreator(cat2).workspace("bam");

        doImport(doExport("foo"), "bam", cat2);

        LayerInfo stuff = cat2.getLayerByName("bam:stuff");
        assertNotNull(stuff);

        assertNotNull(stuff.getDefaultStyle());
        assertEquals(1, stuff.getStyles().size());

        assertNotNull(stuff.getStyles().iterator().next());
    }

    Path doExport(String wsName) throws Exception {
        BundleExporter exporter = new BundleExporter(cat,new ExportOpts(cat.getWorkspaceByName(wsName)));
        exporter.run();
        return exporter.zip();
    }

    void doImport(Path zip, String wsName, Catalog cat) throws Exception {
        importer = new BundleImporter(cat, new ImportOpts(cat.getWorkspaceByName(wsName)));
        importer.unzip(zip);
        importer.run();
    }
}


