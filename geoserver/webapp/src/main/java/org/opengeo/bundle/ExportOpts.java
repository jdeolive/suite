package org.opengeo.bundle;

import org.geoserver.catalog.WorkspaceInfo;
import org.geotools.geometry.jts.ReferencedEnvelope;

public class ExportOpts {

    WorkspaceInfo workspace;

    String name;
    ReferencedEnvelope bounds;

    public ExportOpts(WorkspaceInfo workspace) {
        this.workspace = workspace;
    }

    public WorkspaceInfo workspace() {
        return workspace;
    }

    public ExportOpts bounds(ReferencedEnvelope bounds) {
        this.bounds = bounds;
        return this;
    }

    public ReferencedEnvelope bounds() {
        return bounds;
    }

    public ExportOpts name(String name) {
        this.name = name;
        return this;
    }

    public String name() {
        return name != null ? name : workspace.getName();
    }

}
