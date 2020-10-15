package vn.vmg.ptdv.hola.infra.province.context;

import vn.vmg.ptdv.hola.common.address.core.AddressId;
import vn.vmg.ptdv.hola.infra.shared.InfraContext;

import javax.sql.DataSource;

public class ProvinceInfraContext extends InfraContext<AddressId> {
    public ProvinceInfraContext(DataSource ds) {
        super(ds);
    }
}
