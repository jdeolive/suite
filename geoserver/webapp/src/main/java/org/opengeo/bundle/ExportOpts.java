package org.opengeo.bundle;

import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengis.filter.Filter;

public class ExportOpts {

    ReferencedEnvelope bounds;

    public ExportOpts bounds(ReferencedEnvelope bounds) {
        this.bounds = bounds;
        return this;
    }

    public ReferencedEnvelope bounds() {
        return bounds;
    }

}
