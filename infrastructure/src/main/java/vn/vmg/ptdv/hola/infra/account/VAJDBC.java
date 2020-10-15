package vn.vmg.ptdv.hola.infra.account;

import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.infra.account.context.AccountInfraContext;
import vn.vmg.ptdv.hola.infra.account.factory.VADB;
import vn.vmg.ptdv.hola.infra.account.sql.CreateVAAccount;
import vn.vmg.ptdv.hola.infra.account.sql.FindVAWallet;

import javax.sql.DataSource;
import java.util.List;

public class VAJDBC {
    private final AccountInfraContext accountInfraContext;

    public VAJDBC(DataSource ds) {
        this.accountInfraContext = new AccountInfraContext(ds);
    }

    public VADB findVAWallet(AccountId accountId) {
        List<?> result = accountInfraContext
                .withSQLCommand(new FindVAWallet())
                .withParams(accountId.getPhoneNumber())
                .executeCommand();
        boolean isEmpty = result == null || result.size() == 0;
        VADB vadb = null;
        if (!isEmpty) {
            vadb = (VADB) result.get(0);
        }
        return vadb;
    }

    public boolean InsertVA(VADB vaAccountDB) {
        int result = accountInfraContext
                .withSQLCommand(new CreateVAAccount())
                .withParams(vaAccountDB)
                .executeUpdate(null);
        if (result == 1) {
            return true;
        }
        return false;
    }
}
