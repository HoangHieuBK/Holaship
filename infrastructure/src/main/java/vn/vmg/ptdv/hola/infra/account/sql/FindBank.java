package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.account.factory.BankDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class FindBank  extends MappingSqlQuery implements MappingSQLCommand {
    private Map<String,Object> params;
    @Override
    protected Object mapRow(ResultSet rs, int i) throws SQLException {
        BankDB bankDB = new BankDB();
        bankDB.setName(rs.getString("NAME"));
        bankDB.setCode(rs.getString("CODE"));
        bankDB.setBankCode(rs.getString("BANK_CODE"));
        bankDB.setImageBank(rs.getString("IMAGE_BANK"));
        return bankDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT NAME,CODE,BANK_CODE,IMAGE_BANK FROM BANKS WHERE STATUS = 1");
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

    }

    @Override
    public void declareParameters() {

    }
}
