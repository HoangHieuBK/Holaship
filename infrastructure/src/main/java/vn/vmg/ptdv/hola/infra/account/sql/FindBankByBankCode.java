package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.account.factory.BankDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBankByBankCode extends MappingSqlQuery implements MappingSQLCommand {
    private Map<String, Object> params;

    @Override
    protected Object mapRow(ResultSet rs, int i) throws SQLException {
        BankDB bankDB = new BankDB();
        bankDB.setBankCode(rs.getString("BANK_CODE"));
        bankDB.setName(rs.getString("NAME"));
        bankDB.setCode(rs.getString("CODE"));
        return bankDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT NAME , CODE , BANK_CODE FROM BANKS WHERE BANK_CODE =:" + BankDB.BANK_CODE);
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
        String bankCode = (String) parameters[0];
        params = new HashMap<String, Object>();
        params.put(BankDB.BANK_CODE, bankCode);
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("BANK_CODE", Types.VARCHAR));
    }
}
