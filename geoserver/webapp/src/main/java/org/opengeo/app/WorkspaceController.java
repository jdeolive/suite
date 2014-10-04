package org.opengeo.app;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.geoserver.catalog.CascadeDeleteVisitor;
import org.geoserver.catalog.Catalog;
import org.geoserver.catalog.NamespaceInfo;
import org.geoserver.catalog.Predicates;
import org.geoserver.catalog.WorkspaceInfo;
import org.geoserver.catalog.util.CloseableIterator;
import org.geoserver.config.GeoServer;
import org.opengeo.bundle.BundleExporter;
import org.opengeo.bundle.BundleImporter;
import org.opengeo.bundle.ExportOpts;
import org.opengeo.bundle.ImportOpts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/api/workspaces")
public class WorkspaceController extends AppController {

    public static final String APPLICATION_ZIP_VALUE = "application/zip";

    @Autowired
    public WorkspaceController(GeoServer geoServer) {
        super(geoServer);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody JSONArr list() {
        JSONArr arr = new JSONArr();

        Catalog cat = geoServer.getCatalog();

        WorkspaceInfo def = cat.getDefaultWorkspace();
        if (def != null) {
           IO.workspace(arr.addObject(), def, namespaceFor(def), true);
        }

        CloseableIterator<WorkspaceInfo> list = cat.list(WorkspaceInfo.class, Predicates.acceptAll());

        try {
            while(list.hasNext()) {
                WorkspaceInfo ws = list.next();
                if (def != null && ws.getName().equals(def.getName())) {
                    continue;
                }

                NamespaceInfo ns = namespaceFor(ws);
                arr.addObject()
                   .put("name", ws.getName())
                   .put("default", false)
                   .put("uri", ns.getURI());
            }
        }
        finally {
            list.close();
        }

        return arr;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody JSONObj create(@RequestBody JSONObj obj) {
        Catalog cat = geoServer.getCatalog();

        String wsName = obj.str("name");
        if (wsName == null) {
            throw new BadRequestException("Workspace must have a name");
        }

        String nsUri = obj.str("uri");
        if (nsUri == null) {
            nsUri = "http://" + wsName;
        }

        boolean isDefault = obj.has("default") ? obj.bool("default") : false;

        WorkspaceInfo ws = cat.getFactory().createWorkspace();
        ws.setName(wsName);

        NamespaceInfo ns = cat.getFactory().createNamespace();
        ns.setPrefix(wsName);
        ns.setURI(nsUri);

        cat.add(ws);
        cat.add(ns);

        if (isDefault) {
            cat.setDefaultWorkspace(ws);
            cat.setDefaultNamespace(ns);
        }

        return IO.workspace(new JSONObj(), ws, ns, isDefault);
    }

    @RequestMapping(value = "/{wsName}", method = RequestMethod.GET)
    public @ResponseBody JSONObj get(@PathVariable String wsName) {
        Catalog cat = geoServer.getCatalog();

        WorkspaceInfo ws = findWorkspace(wsName, cat);
        WorkspaceInfo def = cat.getDefaultWorkspace();

        return IO.workspace(new JSONObj(), ws, namespaceFor(ws), def != null && def.equals(ws));
    }

    @RequestMapping(value = "/{wsName}", method = RequestMethod.PATCH)
    public @ResponseBody JSONObj patch(@PathVariable String wsName, @RequestBody JSONObj obj) {
        return put(wsName, obj);
    }

    @RequestMapping(value = "/{wsName}", method = RequestMethod.PUT)
    public @ResponseBody JSONObj put(@PathVariable String wsName, @RequestBody JSONObj obj) {
        Catalog cat = geoServer.getCatalog();

        WorkspaceInfo ws = findWorkspace(wsName, cat);
        NamespaceInfo ns = namespaceFor(ws);

        String name = obj.str("name");
        if (name != null) {
            ws.setName(name);
            ns.setPrefix(name);
        }

        String uri = obj.str("uri");
        if (uri != null) {
            ns.setURI(uri);
        }

        cat.save(ws);
        cat.save(ns);

        Boolean isDefault = obj.bool("default");
        if (Boolean.TRUE.equals(isDefault)) {
            cat.setDefaultWorkspace(ws);
            cat.setDefaultNamespace(ns);
        }
        else if (Boolean.FALSE.equals(isDefault)) {
            //TODO: check if currently the default, and unset it
        }

        return IO.workspace(new JSONObj(), ws, ns, isDefault == Boolean.TRUE);
    }

    @RequestMapping(value = "/{wsName}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String wsName) {
        Catalog cat = geoServer.getCatalog();

        WorkspaceInfo ws = findWorkspace(wsName, cat);
        new CascadeDeleteVisitor(cat).visit(ws);
    }

    @RequestMapping(value = "/{wsName}/export", method = RequestMethod.POST, produces = APPLICATION_ZIP_VALUE)
    public void export(@PathVariable String wsName, HttpServletResponse response) throws Exception {
        Catalog cat = geoServer.getCatalog();

        WorkspaceInfo ws = findWorkspace(wsName, cat);
        BundleExporter exporter = new BundleExporter(cat, new ExportOpts(ws));
        exporter.run();

        Path zip = exporter.zip();

        response.setContentType(APPLICATION_ZIP_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=\""+zip.getFileName()+"\"");
        FileUtils.copyFile(zip.toFile(), response.getOutputStream());
    }


    @RequestMapping(value = "/{wsName}/import", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void inport(@PathVariable String wsName, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        Catalog cat = geoServer.getCatalog();

        WorkspaceInfo ws = findWorkspace(wsName, cat);

        // grab the uploaded file
        Iterator<FileItem> files = doFileUpload(request);
        if (!files.hasNext()) {
            throw new BadRequestException("Request must contain a single file");
        }

        FileItem file = files.next();

        Path zip = Files.createTempFile(null, null);
        file.write(zip.toFile());

        BundleImporter importer = new BundleImporter(cat, new ImportOpts(ws));
        importer.unzip(zip);
        importer.run();

        response.setStatus(HttpStatus.OK.value());
    }

}
