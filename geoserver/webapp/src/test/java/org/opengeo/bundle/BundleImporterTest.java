package org.opengeo.bundle;

import org.apache.commons.io.FileUtils;
import org.geoserver.catalog.Catalog;
import org.geoserver.catalog.impl.CatalogImpl;
import org.geoserver.config.GeoServerPersister;
import org.geoserver.config.util.XStreamPersisterFactory;
import org.geoserver.platform.GeoServerResourceLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BundleImporterTest extends BundleTestSupport {

    BundleImporter importer;

    @Test
    public void testSimple() throws Exception {
        List<Map<String,Object>> features = new ArrayList<>();
        features.add(featureData("geom", geo("POINT(0 0)"), "name", "zero", "id", 0));
        features.add(featureData("geom", geo("POINT(1 1)"), "name", "one", "id", 1));
        features.add(featureData("geom", geo("POINT(2 2)"), "name", "two", "id", 2));

        new CatalogCreator(cat).workspace("foo")
            .property("bar")
            .featureType("stuff", "geom:Point:srid=4326,name:String,id:Integer", features);

        BundleExporter exporter = new BundleExporter(cat,new ExportOpts(cat.getWorkspaceByName("foo")));

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        exporter.export(bout);

        Catalog cat2 = createCatalog();
        importer = new BundleImporter(cat2, new ImportOpts());
        importer.run(new ByteArrayInputStream(bout.toByteArray()));

    }
}


