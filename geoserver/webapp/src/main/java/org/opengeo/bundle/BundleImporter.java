package org.opengeo.bundle;


import org.apache.commons.io.FileUtils;
import org.geoserver.catalog.Catalog;
import org.geoserver.catalog.LayerInfo;
import org.geoserver.catalog.NamespaceInfo;
import org.geoserver.catalog.ResourceInfo;
import org.geoserver.catalog.StoreInfo;
import org.geoserver.catalog.StyleInfo;
import org.geoserver.catalog.WorkspaceInfo;
import org.geoserver.config.GeoServerDataDirectory;
import org.geoserver.config.util.XStreamPersister;
import org.geoserver.config.util.XStreamPersisterFactory;
import org.geoserver.data.util.IOUtils;
import org.geoserver.platform.GeoServerResourceLoader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Imports GeoServer config/data bundle.
 */
public class BundleImporter {

    static FileFilter DIRECTORY = new FileFilter() {
        @Override
        public boolean accept(File file) {
            return file.isDirectory();
        }
    };

    static FilenameFilter CONFIG_FILE = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".xml") && !name.endsWith(".xml.xml");
        }
    };

    Catalog catalog;
    ImportOpts options;

    Path root;
    GeoServerDataDirectory importDataDir;
    GeoServerDataDirectory targetDataDir;

    XStreamPersister xsp;

    public BundleImporter(Catalog catalog, ImportOpts options) throws IOException {
        this.catalog = catalog;
        this.options = options;

        // temp directory to unpack
        root = Files.createTempDirectory(null);
        importDataDir = new GeoServerDataDirectory(new GeoServerResourceLoader(root.toFile()));
        targetDataDir = new GeoServerDataDirectory(catalog.getResourceLoader());

        // config deserializer
        xsp = new XStreamPersisterFactory().createXMLPersister();
        xsp.setExcludeIds();
        xsp.setReferenceByName(true);
        xsp.setCatalog(catalog);
    }

    public void run(InputStream input) throws IOException {
        // unpack
        IOUtils.decompress(input, root.toFile());

        // global styles
        File styleRoot = root.resolve("styles").toFile();
        if (styleRoot.exists()) {
            loadStyles(styleRoot);
        }

        // workspaces
        File wsRoot = root.resolve("workspaces").toFile();
        for (File dir : wsRoot.listFiles(DIRECTORY)) {
            loadWorkspace(dir);
        }
    }

    void loadStyles(File styleDir) throws IOException {
        for (File f : styleDir.listFiles(CONFIG_FILE)) {
            StyleInfo s = depersist(f, StyleInfo.class);

            //TODO: copy over sld file?
            catalog.add(s);
        }
    }

    void loadWorkspace(File wsDir) throws IOException {
        WorkspaceInfo ws = depersist(new File(wsDir, "workspace.xml"), WorkspaceInfo.class);
        catalog.add(ws);

        NamespaceInfo ns = depersist(new File(wsDir, "namespace.xml"), NamespaceInfo.class);
        catalog.add(ns);

        // data directory
        File dataDir = new File(wsDir, "data");
        if (dataDir.exists()) {
            FileUtils.copyDirectory(dataDir, targetDataDir.get(ws, "data").dir());
        }

        // styles
        File styleDir = new File(wsDir, "styles");
        if (styleDir.exists()) {
            loadStyles(styleDir);
        }

        //TODO: layer groups

        // stores
        for (File dir : wsDir.listFiles(DIRECTORY)) {
            loadStore(dir, wsDir, ws, ns);
        }
    }

    void loadStore(File storeDir, File wsDir, WorkspaceInfo ws, NamespaceInfo ns) throws IOException {
        //TODO: wms store
        File file = new File(storeDir, "datastore.xml");
        if (!file.exists()) {
            file = new File(storeDir, "coveragestore.xml");
        }
        if (!file.exists()) {
            file = new File(storeDir, "store.xml");
        }
        if (!file.exists()) {
            return;
        }

        StoreInfo s = depersist(file, StoreInfo.class);
        s.setWorkspace(ws);
        catalog.add(s);

        for (File dir : storeDir.listFiles(DIRECTORY)) {
            loadResource(dir, s, ns);
        }

    }

    void loadResource(File resourceDir, StoreInfo s, NamespaceInfo ns) throws IOException {
        // TODO: wms layer
        File file = new File(resourceDir, "featuretype.xml");
        if (!file.exists()) {
            file = new File(resourceDir, "coverage.xml");
        }
        if (!file.exists()) {
            file = new File(resourceDir, "resource.xml");
        }
        if (!file.exists()) {
            return;
        }

        ResourceInfo r = depersist(file, ResourceInfo.class);
        r.setStore(s);
        r.setNamespace(ns);
        catalog.add(r);

        LayerInfo l = depersist(new File(resourceDir, "layer.xml"), LayerInfo.class);
        l.setResource(r);
        catalog.add(l);
    }

    <T> T depersist(File file, Class<T> type) throws IOException {
        try (
            InputStream in = new BufferedInputStream(new FileInputStream(file));
        ) {
            return xsp.load(in, type);
        }
    }
}
