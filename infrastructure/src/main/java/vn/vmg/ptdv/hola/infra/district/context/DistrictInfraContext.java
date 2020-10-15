package vn.vmg.ptdv.hola.infra.district.context;

import vn.vmg.ptdv.hola.common.address.core.AddressId;
import vn.vmg.ptdv.hola.infra.shared.InfraContext;

import javax.sql.DataSource;

public class DistrictInfraContext extends InfraContext<AddressId> {
    public DistrictInfraContext(DataSource ds) {
        super(ds);
    }
}
