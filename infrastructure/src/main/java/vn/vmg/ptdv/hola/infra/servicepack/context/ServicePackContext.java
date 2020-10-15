package vn.vmg.ptdv.hola.infra.servicepack.context;


import vn.vmg.ptdv.hola.infra.shared.InfraContext;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;

import javax.sql.DataSource;

public class ServicePackContext extends InfraContext<ServicePackID> {
    public ServicePackContext(DataSource ds) {
        super(ds);
    }
}
