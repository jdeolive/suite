package org.opengeo.bundle;

import org.geoserver.catalog.WorkspaceInfo;

/**
 * Justin Deoliveira, Boundless
 */
public class ImportOpts {

    WorkspaceInfo workspace;

    public ImportOpts(WorkspaceInfo workspace) {
        this.workspace = workspace;
    }

    public WorkspaceInfo workspace() {
        return workspace;
    }
}
