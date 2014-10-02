package org.opengeo.bundle;

import org.apache.commons.io.FileUtils;
import org.geoserver.catalog.Catalog;
import org.geoserver.catalog.CatalogInfo;
import org.geoserver.catalog.CoverageInfo;
import org.geoserver.catalog.CoverageStoreInfo;
import org.geoserver.catalog.DataStoreInfo;
import org.geoserver.catalog.FeatureTypeInfo;
import org.geoserver.catalog.LayerInfo;
import org.geoserver.catalog.ResourceInfo;
import org.geoserver.catalog.StoreInfo;
import org.geoserver.catalog.StyleInfo;
import org.geoserver.catalog.WorkspaceInfo;
import org.geoserver.catalog.util.CloseableIterator;
import org.geoserver.config.GeoServerDataDirectory;
import org.geoserver.config.util.XStreamPersister;
import org.geoserver.config.util.XStreamPersisterFactory;
import org.geoserver.platform.GeoServerResourceLoader;
import org.geotools.util.logging.Logging;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

import static org.geoserver.catalog.Predicates.*;

/**
 * Exports GeoServer config/data bundle.  
 */
public class BundleExporter {

    static Logger LOG = Logging.getLogger(BundleExporter.class);

    Catalog catalog;

    Path root;

    GeoServerDataDirectory sourceDataDir;
    GeoServerDataDirectory exportDataDir;

    XStreamPersister xsp;

    public BundleExporter(Catalog catalog) throws IOException {
        this.catalog = catalog;

        // create a temp directory for the export
        root = Files.createTempDirectory(null);
        sourceDataDir = new GeoServerDataDirectory(catalog.getResourceLoader());
        exportDataDir = new GeoServerDataDirectory(new GeoServerResourceLoader(root.toFile()));
        exportDataDir.setConfigFileExtension("json");

        // config serializer
        xsp = new XStreamPersisterFactory().createJSONPersister();
    }

    public Path root() {
        return root;
    }

    public void export(WorkspaceInfo workspace) throws Exception {
        persist(workspace);

        try (
            CloseableIterator<StoreInfo> sit = catalog.list(StoreInfo.class, equal("workspace", workspace))
        ) {
            while (sit.hasNext()) {
                StoreInfo store = sit.next();

                persist(store);

                try (
                    CloseableIterator<ResourceInfo> rit =
                        catalog.list(ResourceInfo.class, equal("store.id", store.getId()))
                ) {
                    while(rit.hasNext()) {
                        ResourceInfo resource = rit.next();
                        persist(resource);
                    }
                }
            }
        }
    }

    void persist(WorkspaceInfo ws) throws IOException {
        File dir = exportDataDir.get(ws).dir();
        dir.mkdirs();
        persist(ws, exportDataDir.config(ws).file());
    }

    void persist(StoreInfo s) throws IOException {
        File dir = exportDataDir.get(s).dir();
        dir.mkdirs();

        File file = null;
        if (s instanceof CoverageStoreInfo) {
            file = exportDataDir.config((CoverageStoreInfo)s).file();
        }
        else if (s instanceof DataStoreInfo) {
            file = exportDataDir.config((DataStoreInfo)s).file();
        }
        else {
            file = new File(dir, "store.xml");
        }

        persist(s, file);
    }

    void persist(ResourceInfo r) throws IOException {
        File dir = exportDataDir.get(r).dir();
        dir.mkdirs();

        File file = null;
        if (r instanceof CoverageInfo) {
            file = exportDataDir.config((CoverageInfo)r).file();
        }
        else if (r instanceof FeatureTypeInfo) {
            file = exportDataDir.config((FeatureTypeInfo)r).file();
        }
        else {
            file = new File(dir, "resource.xml");
        }

        persist(r, file);

        List<LayerInfo> layers = catalog.getLayers(r);
        if (!layers.isEmpty()) {
            persist(layers.get(0));
        }
        else {
            LOG.warning("Resource: " + r.getName() + " has no layer");
        }

    }

    void persist(LayerInfo l) throws IOException {
        persist(l, exportDataDir.config(l).file());
        persist(l.getDefaultStyle());
        for (StyleInfo s : l.getStyles()) {
            persist(s);
        }
    }

    void persist(StyleInfo s) throws IOException {
        File dir = exportDataDir.get(s).dir();
        dir.mkdirs();

        persist(s, exportDataDir.config(s).file());
        FileUtils.copyFileToDirectory(sourceDataDir.style(s).file(), dir);

        //TODO: grab all of the icons
    }

    void persist(CatalogInfo info, File file) throws IOException {
        try (
            OutputStream out = new BufferedOutputStream(new FileOutputStream(file))
        ) {
            xsp.save(info, out);
        }
    }
}
