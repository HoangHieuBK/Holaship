package vn.vmg.ptdv.hola.infra.leadtime.context;

import vn.vmg.ptdv.hola.infra.shared.InfraContext;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeLogId;

import javax.sql.DataSource;

public class LeadtimeLogInfraContext extends InfraContext<LeadtimeLogId> {
    public LeadtimeLogInfraContext(DataSource ds) {
        super(ds);
    }
}
