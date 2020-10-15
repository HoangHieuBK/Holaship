package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.account.factory.BankAccountDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBankAccount extends MappingSqlQuery implements MappingSQLCommand {
    private Map<String, Object> params;

    @Override
    protected BankAccountDB mapRow(ResultSet rs, int i) throws SQLException {
        BankAccountDB bankAccountDB = new BankAccountDB();
        bankAccountDB.setAppUserId(rs.getLong("BANK_ID"));
        bankAccountDB.setBankAccount(rs.getString("BANK_ACCOUNT"));
        bankAccountDB.setBankAccountName(rs.getString("BANK_ACCOUNT_NAME"));
        bankAccountDB.setPhone(rs.getString("PHONE"));
        return bankAccountDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(
                "SELECT BANK_ID,BANK_ACCOUNT,BANK_ACCOUNT_NAME,PHONE FROM BANK_ACCOUNTS WHERE APP_USER_ID =:APP_USER_ID AND BANK_ACCOUNT =:BANK_ACCOUNT");
        return builder.toString();
    }

    @Override
    public List<?> executeCommand(DataSource dataSource) {
        this.setDataSource(dataSource);
        this.setSql(prepareSQL());
        this.declareParameters();
        return this.executeByNamedParam(params);
    }

    @Override
    public void prepareParams(Object... parameters) {
        Long appUserId = (Long) parameters[0];
        String bankAccount = (String) parameters[1];

        params = new HashMap<String, Object>();
        params.put("APP_USER_ID", appUserId);
        params.put("BANK_ACCOUNT", bankAccount);


    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("APP_USER_ID", Types.NUMERIC));
        declareParameter(new SqlParameter("BANK_ACCOUNT", Types.VARCHAR));
    }
}
