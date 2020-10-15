package vn.vmg.ptdv.hola.infra.leadtime.context;

import vn.vmg.ptdv.hola.infra.shared.InfraContext;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;

import javax.sql.DataSource;

public class LeadtimeInfraContext extends InfraContext<LeadtimeId> {
    public LeadtimeInfraContext(DataSource ds) {
        super(ds);
    }
}
