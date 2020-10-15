package vn.vmg.ptdv.hola.infra.account.context;

import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.infra.shared.InfraContext;

import javax.sql.DataSource;

public class AccountInfraContext extends InfraContext<AccountId> {
    public AccountInfraContext(DataSource ds) {
        super(ds);
    }
}
