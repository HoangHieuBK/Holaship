package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.infra.account.factory.BankAccountDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class CreateBankAccount extends SqlUpdate implements UpdateSQLCommand<AccountId> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO BANK_ACCOUNTS (")
                .append("BANK_ID, BANK_ACCOUNT, BANK_ACCOUNT_NAME, STATUS, PHONE, TYPE, APP_USER_ID,ROLE_TYPE ,UTIMESTAMP")
                .append(" ) VALUES ( ")
                .append("(SELECT ID FROM BANKS WHERE BANK_CODE =:" + BankAccountDB.BANK_CODE)
                .append("), :" + BankAccountDB.BANK_ACCOUNT)
                .append((", :" + BankAccountDB.BANK_ACCOUNT_NAME))
                .append(", 1")
                .append(", :" + BankAccountDB.PHONE)
                .append(", :" + BankAccountDB.TYPE)
                .append(", :" + BankAccountDB.APP_USER_ID)
                .append(", 1")
                .append(", SYSTIMESTAMP )");
        return builder.toString();
    }

    @Override
    public int updateCommand(DataSource dataSource, AccountId accountId) {
        this.setDataSource(dataSource);
        this.setSql(this.prepareSQL());
        this.declareParameters();
        compile();
        int created = this.updateByNamedParam(params);

        return created;
    }

    @Override
    public void prepareParams(Object... parameters) {
        BankAccountDB bankAccountDB = (BankAccountDB) parameters[0];
        params = new HashMap<String, Object>();
        params.put(BankAccountDB.BANK_CODE, bankAccountDB.getBankCode());
        params.put(BankAccountDB.BANK_ACCOUNT, bankAccountDB.getBankAccount());
        params.put(BankAccountDB.BANK_ACCOUNT_NAME, bankAccountDB.getBankAccountName());
        params.put(BankAccountDB.PHONE, bankAccountDB.getPhone());
        params.put(BankAccountDB.TYPE, bankAccountDB.getType());
        params.put(BankAccountDB.APP_USER_ID, bankAccountDB.getAppUserId());


    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(BankAccountDB.BANK_CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(BankAccountDB.BANK_ACCOUNT, Types.VARCHAR));
        declareParameter(new SqlParameter(BankAccountDB.BANK_ACCOUNT_NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(BankAccountDB.PHONE, Types.NVARCHAR));
        declareParameter(new SqlParameter(BankAccountDB.TYPE, Types.NUMERIC));
        declareParameter(new SqlParameter(BankAccountDB.APP_USER_ID, Types.NUMERIC));
    }
}
