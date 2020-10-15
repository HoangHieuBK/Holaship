package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.account.factory.VADB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindVAWallet extends MappingSqlQuery implements MappingSQLCommand {
    private Map<String, Object> params;

    @Override
    protected Object mapRow(ResultSet rs, int i) throws SQLException {
        VADB vaAccountDB = new VADB();
        vaAccountDB.setShipCode(rs.getString("SHIP_CODE"));
        vaAccountDB.setShopCode(rs.getString("SHOP_CODE"));
        vaAccountDB.setUserId(rs.getLong("USER_ID"));
        vaAccountDB.setUserName(rs.getString("USER_NAME"));
        return vaAccountDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(
                "SELECT USER_ID,USER_NAME,SHIP_CODE,SHOP_CODE FROM VA_WALLETS  va LEFT JOIN APP_USERS ap ON va.USER_ID = ap.ID WHERE PHONE =:" + VADB.PHONE);
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
        params = new HashMap<String, Object>();
        params.put(VADB.PHONE, phoneNumber);
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("PHONE", Types.VARCHAR));
    }
}
