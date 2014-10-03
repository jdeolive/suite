package org.opengeo.bundle;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.vfs.VFS;
import org.geoserver.catalog.Catalog;
import org.geoserver.catalog.DataStoreInfo;
import org.geoserver.catalog.impl.CatalogImpl;

import org.geoserver.config.GeoServerPersister;
import org.geoserver.config.util.XStreamPersisterFactory;
import org.geoserver.platform.GeoServerResourceLoader;
import org.geotools.geopkg.GeoPackage;
import org.geotools.util.logging.Logging;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opengeo.app.JSONObj;
import org.opengeo.app.JSONWrapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BundleExporterTest extends BundleTestSupport {

    BundleExporter exporter;

    @Test
    public void testSimple() throws Exception {
        List<Map<String,Object>> features = new ArrayList<>();
        features.add(featureData("geom", geo("POINT(0 0)"), "name", "zero", "id", 0));
        features.add(featureData("geom", geo("POINT(1 1)"), "name", "one", "id", 1));
        features.add(featureData("geom", geo("POINT(2 2)"), "name", "two", "id", 2));

        new CatalogCreator(cat).workspace("foo")
            .property("bar")
                .featureType("stuff", "geom:Point:srid=4326,name:String,id:Integer", features);

        exporter = new BundleExporter(cat, new ExportOpts(cat.getWorkspaceByName("foo")));
        exporter.export(null);

        Path root = exporter.root();
        System.out.println(root);

        assertPathExists(root, "workspaces");
        assertPathExists(root, "workspaces/foo");
        assertPathExists(root, "workspaces/foo/workspace.xml");
        assertPathExists(root, "workspaces/foo/namespace.xml");
        assertPathExists(root, "workspaces/foo/bar");
        assertPathExists(root, "workspaces/foo/bar/datastore.xml");
        assertPathExists(root, "workspaces/foo/bar/stuff");
        assertPathExists(root, "workspaces/foo/bar/stuff/featuretype.xml");
        assertPathExists(root, "workspaces/foo/bar/stuff/layer.xml");
        assertPathExists(root, "workspaces/foo/styles");
        assertPathExists(root, "workspaces/foo/styles/stuff.xml");
        assertPathExists(root, "workspaces/foo/styles/stuff.sld");
        assertPathExists(root, "workspaces/foo/data/bar/stuff.properties");

        // ensure the exported store config points to the properties
        DataStoreInfo store = new XStreamPersisterFactory().createXMLPersister()
                .load(new FileInputStream(root.resolve("workspaces/foo/bar/datastore.xml").toFile()), DataStoreInfo.class);

        assertEquals("file:workspaces/foo/data/bar", store.getConnectionParameters().get("directory"));
    }

    @Test
    public void testInjestIntoGeopkg() throws Exception {
        List<Map<String,Object>> stuff = new ArrayList<>();
        stuff.add(featureData("geom", geo("POINT(0 0)"), "name", "zero", "id", 0));
        stuff.add(featureData("geom", geo("POINT(1 1)"), "name", "one", "id", 1));
        stuff.add(featureData("geom", geo("POINT(2 2)"), "name", "two", "id", 2));

        List<Map<String,Object>> widgets = new ArrayList<>();
        widgets.add(featureData("geom", geo("POINT(0 0)"), "name", "bomb", "id", 0));
        widgets.add(featureData("geom", geo("POINT(1 1)"), "name", "anvil", "id", 1));
        widgets.add(featureData("geom", geo("POINT(2 2)"), "name", "dynamite", "id", 2));

        new CatalogCreator(cat).workspace("foo")
            .database("bar")
                .featureType("stuff", "geom:Point:srid=4326,name:String,id:Integer", stuff).store()
                .featureType("widgets", "geom:Point:srid=4326,name:String,id:Integer", widgets);

        exporter = new BundleExporter(cat, new ExportOpts(cat.getWorkspaceByName("foo")));
        exporter.export(null);
        Path root = exporter.root();

        assertPathExists(root, "workspaces");
        assertPathExists(root, "workspaces/foo");
        assertPathExists(root, "workspaces/foo/workspace.xml");
        assertPathExists(root, "workspaces/foo/namespace.xml");
        assertPathExists(root, "workspaces/foo/bar/datastore.xml");
        assertPathExists(root, "workspaces/foo/bar/stuff/featuretype.xml");
        assertPathExists(root, "workspaces/foo/bar/stuff/layer.xml");
        assertPathExists(root, "workspaces/foo/bar/widgets/featuretype.xml");
        assertPathExists(root, "workspaces/foo/bar/widgets/layer.xml");
        assertPathExists(root, "workspaces/foo/data/bar.gpkg");

        // ensure the geopackage has the right data in it
        GeoPackage gpkg = new GeoPackage(root.resolve("workspaces/foo/data/bar.gpkg").toFile());
        try {
            assertEquals(2, gpkg.features().size());
            assertNotNull(gpkg.feature("stuff"));
            assertNotNull(gpkg.feature("widgets"));
        }
        finally {
            gpkg.close();
        }

        // ensure the exported store config points to the geopackage
        DataStoreInfo store = new XStreamPersisterFactory().createXMLPersister()
            .load(new FileInputStream(root.resolve("workspaces/foo/bar/datastore.xml").toFile()), DataStoreInfo.class);

        assertEquals("geopkg", store.getConnectionParameters().get("dbtype"));
        assertEquals("file:workspaces/foo/data/bar.gpkg", store.getConnectionParameters().get("database"));
    }

    @Test
    public void testBundleInfo() throws Exception {
        new CatalogCreator(cat).workspace("foo");

        exporter = new BundleExporter(cat,
            new ExportOpts(cat.getWorkspaceByName("foo")).name("blah"));
        exporter.export(null);

        assertPathExists(exporter.root(), "bundle.json");

        try (
            FileInputStream in =  new FileInputStream(exporter.root().resolve("bundle.json").toFile());
        ) {
            JSONObj obj = JSONWrapper.read(in).toObject();
            assertEquals("blah", obj.str("name"));
        }
    }

    @Test
    public void testZipBundle() throws Exception {
        new CatalogCreator(cat).workspace("foo");
        exporter = new BundleExporter(cat,
            new ExportOpts(cat.getWorkspaceByName("foo")).name("blah"));

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        exporter.export(bout);

        ZipInputStream zin = new ZipInputStream(new ByteArrayInputStream(bout.toByteArray()));
        ZipEntry entry = null;

        boolean foundBundle = false;
        boolean foundWorkspace = false;

        while (((entry = zin.getNextEntry()) != null)) {
            if (entry.getName().equals("bundle.json")) {
                foundBundle = true;
            }
            if (entry.getName().endsWith("workspace.xml")) {
                foundWorkspace = true;
            }
        }

        assertTrue(foundBundle);
        assertTrue(foundWorkspace);
    }

    void assertPathExists(Path root, String path) {
        assertTrue(root.resolve(path).toFile().exists());
    }
}