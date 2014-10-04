package org.opengeo.app;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.geoserver.catalog.Catalog;
import org.geoserver.importer.Importer;
import org.geoserver.platform.GeoServerExtensions;
import org.geoserver.platform.GeoServerResourceLoader;
import org.geoserver.platform.resource.Resource;
import org.geoserver.test.GeoServerSystemTestSupport;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AppIntegrationTest extends GeoServerSystemTestSupport {

    @Test
    public void testPageLayers() throws Exception {
        JSONArray arr = (JSONArray) getAsJSON(("/app/api/layers/sf"));
        assertEquals(3, arr.size());

        arr = (JSONArray) getAsJSON(("/app/api/layers/sf?page=1&pagesize=1"));
        assertEquals(1, arr.size());
    }

    @Test
    public void testImportFile() throws Exception {
        Catalog catalog = getCatalog();
        assertNull(catalog.getLayerByName("gs:point"));

        Importer importer =
            GeoServerExtensions.bean(Importer.class, applicationContext);
        ImportController ctrl = new ImportController(getGeoServer(), importer);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContextPath("/geoserver");
        request.setRequestURI("/geoserver/hello");
        request.setMethod("post");

        createMultiPartFormContent(request, "form-data; name=\"upload\"; filename=\"point.zip\"", "application/zip",
                IOUtils.toByteArray(getClass().getResourceAsStream("point.shp.zip")));

        JSONObj result = ctrl.create("gs", request);

        assertEquals(1, result.array("imported").size());
        JSONObj obj = result.array("imported").object(0);

        assertEquals("gs", obj.object("layer").str("workspace"));
        assertEquals("point", obj.object("layer").str("name"));

        assertNotNull(catalog.getLayerByName("gs:point"));
    }

    @Test
    public void testIconsUpload() throws Exception {
        Catalog catalog = getCatalog();
        IconController ctrl = new IconController(getGeoServer());
        
        // test upload
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContextPath("/geoserver");
        request.setRequestURI("/geoserver/api/icons");
        request.setMethod("post");

        createMultiPartFormContent(request, "form-data; name=\"icon\"; filename=\"STYLE.PROPERTIES\"",
            "text/x-java-properties", "square=LINESTRING((0 0,0 1,1 1,1 0,0 0))".getBytes() );

        JSONArr arr = ctrl.icon("cite", request );
        System.out.println( arr );
        assertEquals( 1, arr.size() );
        
        Resource r = catalog.getResourceLoader().get("workspaces/cite/styles/STYLE.PROPERTIES");
        assertEquals("created", Resource.Type.RESOURCE, r.getType() );
    }

    @Test
    public void testWorkspaceExport() throws Exception {
        MockHttpServletResponse response = doWorkspaceExport("sf");

        assertEquals("application/zip", response.getContentType());
        assertEquals("attachment; filename=\"sf.zip\"", response.getHeader("Content-Disposition"));

        Path tmp = Files.createTempDirectory(Paths.get("target"), "export");
        org.geoserver.data.util.IOUtils.decompress(
            new ByteArrayInputStream(response.getContentAsByteArray()), tmp.toFile());

        assertTrue(tmp.resolve("bundle.json").toFile().exists());
    }

    @Test
    public void testWorkspaceImport() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        createMultiPartFormContent(request, "form-data; name=\"file\"; filename=\"sf.zip\"", "application/zip",
            doWorkspaceExport("sf").getContentAsByteArray());

        Catalog cat = getCatalog();
        assertNull(cat.getLayerByName("gs:PrimitiveGeoFeature"));

        MockHttpServletResponse response = new MockHttpServletResponse();
        WorkspaceController ctrl = new WorkspaceController(getGeoServer());
        ctrl.inport("gs", request, response);

        assertNotNull(cat.getLayerByName("gs:PrimitiveGeoFeature"));
    }

    MockHttpServletResponse doWorkspaceExport(String wsName) throws Exception {
        WorkspaceController ctrl = new WorkspaceController(getGeoServer());

        MockHttpServletResponse response = new MockHttpServletResponse();
        ctrl.export(wsName, response);

        return response;
    }

    void createMultiPartFormContent(MockHttpServletRequest request, String contentDisposition, String contentType,
        byte[] content) throws Exception {
        MimeMultipart body = new MimeMultipart();
        request.setContentType(body.getContentType());
        InternetHeaders headers = new InternetHeaders();
        headers.setHeader("Content-Disposition", contentDisposition);
        headers.setHeader("Content-Type", contentType);
        body.addBodyPart(new MimeBodyPart(headers, content ));

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        body.writeTo(bout);
        request.setContent(bout.toByteArray());
    }
}
