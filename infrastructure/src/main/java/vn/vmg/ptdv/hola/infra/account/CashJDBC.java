package vn.vmg.ptdv.hola.infra.account;

import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.BankInfo;
import vn.vmg.ptdv.hola.infra.account.context.AccountInfraContext;
import vn.vmg.ptdv.hola.infra.account.factory.BankAccountDB;
import vn.vmg.ptdv.hola.infra.account.factory.BankDB;
import vn.vmg.ptdv.hola.infra.account.sql.CreateBankAccount;
import vn.vmg.ptdv.hola.infra.account.sql.FindBankAccount;
import vn.vmg.ptdv.hola.infra.account.sql.FindBankByBankCode;

import javax.sql.DataSource;
import java.util.List;

public class CashJDBC {
    private final AccountInfraContext accountInfraContext;

    public CashJDBC(DataSource ds) {
        this.accountInfraContext = new AccountInfraContext(ds);
    }

    public BankAccountDB findBankAccount(AccountId accountId, BankInfo bankInfo) {
        List<?> result = accountInfraContext
                .withSQLCommand(new FindBankAccount())
                .withParams(accountId.getId(), bankInfo.getBankAccount())
                .executeCommand();
        boolean isEmpty = result == null || result.size() == 0;
        BankAccountDB db = null;
        if (!isEmpty) {
            db = (BankAccountDB) result.get(0);
        }
        return db;
    }

    public BankDB findBankNameByBankCode(String bankCode){
        List<?> result = accountInfraContext
                .withSQLCommand(new FindBankByBankCode())
                .withParams(bankCode)
                .executeCommand();
        boolean isEmpty = result == null || result.size() == 0;
        BankDB db = null;
        if (!isEmpty) {
            db = (BankDB) result.get(0);
        }
        return db;
    }

    public boolean InsertBank(BankAccountDB bankAccountDB) {
        int result = accountInfraContext
                .withSQLCommand(new CreateBankAccount())
                .withParams(bankAccountDB)
                .executeUpdate(null);
        if (result == 1) {
            return true;
        }
        return false;
    }

}
