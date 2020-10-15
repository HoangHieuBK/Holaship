package vn.vmg.ptdv.hola.infra.leadtime.context;

import vn.vmg.ptdv.hola.infra.shared.InfraContext;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;

import javax.sql.DataSource;

public class LeadtimeRouteInfraContext extends InfraContext<LeadtimeRouteId> {
    public LeadtimeRouteInfraContext(DataSource ds) {
        super(ds);
    }
}
