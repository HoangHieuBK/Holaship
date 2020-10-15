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

public class FindBankAccountList extends MappingSqlQuery implements MappingSQLCommand {
    private Map<String, Object> params;

    @Override
    protected Object mapRow(ResultSet rs, int i) throws SQLException {
        BankAccountDB bankAccountDB = new BankAccountDB();
        bankAccountDB.setImageBank(rs.getString("IMAGE_BANK"));
        bankAccountDB.setBankCode(rs.getString("BANK_CODE"));
        bankAccountDB.setBankAccount(rs.getString("BANK_ACCOUNT"));
        bankAccountDB.setType(rs.getInt("TYPE"));
        bankAccountDB.setBankAccountName(rs.getString("BANK_ACCOUNT_NAME"));
        bankAccountDB.setBankName(rs.getString("NAME"));
        bankAccountDB.setCode(rs.getString("CODE"));
        bankAccountDB.setId(rs.getLong("ID"));
        return bankAccountDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(
                "SELECT ba.ID, ba.BANK_ACCOUNT,ba.BANK_ACCOUNT_NAME,ba.TYPE, b.BANK_CODE,b.IMAGE_BANK,b.CODE,b.NAME FROM BANK_ACCOUNTS ba")
                .append(" LEFT JOIN BANKS b ON b.ID = ba.BANK_ID WHERE ba.APP_USER_ID = (SELECT ID FROM APP_USERS WHERE PHONE =:" +
                        BankAccountDB.PHONE)
                .append(") AND ba.ROLE_TYPE =:" + BankAccountDB.ROLE_TYPE)
                .append(" ORDER BY ba.ID ASC");
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
        String phoneNumber = (String) parameters[0];
        int roleType = (int) parameters[1];
        params = new HashMap<String, Object>();
        params.put("PHONE",phoneNumber);
        params.put("ROLE_TYPE",roleType);
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("PHONE", Types.VARCHAR));
        declareParameter(new SqlParameter("ROLE_TYPE", Types.NUMERIC));
    }
}
