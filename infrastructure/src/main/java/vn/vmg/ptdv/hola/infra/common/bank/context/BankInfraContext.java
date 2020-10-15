package vn.vmg.ptdv.hola.infra.common.bank.context;

import vn.vmg.ptdv.hola.common.masterData.bank.core.BankId;
import vn.vmg.ptdv.hola.infra.shared.InfraContext;

import javax.sql.DataSource;

public class BankInfraContext extends InfraContext<BankId> {
    public BankInfraContext(DataSource ds) {
        super(ds);
    }
}
