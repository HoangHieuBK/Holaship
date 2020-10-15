package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.account.factory.HolaAccountDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAppUserByPhone extends MappingSqlQuery implements MappingSQLCommand {
    private Map<String, Object> params;

    @Override
    protected HolaAccountDB mapRow(ResultSet rs, int i) throws SQLException {
        HolaAccountDB holaAccountDB = new HolaAccountDB();
        holaAccountDB.setId(rs.getLong("ID"));
        holaAccountDB.setPhone(rs.getString("PHONE"));
        holaAccountDB.setDeviceToken(rs.getString("DEVICE_TOKEN"));
        holaAccountDB.setShop(rs.getInt("SHOP"));
        holaAccountDB.setShip(rs.getInt("SHIP"));
        return holaAccountDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ")
                .append("  au.ID  ")
                .append(", au.PHONE ")
                .append(", au.SHOP ")
                .append(", au.SHIP ")
                .append(", aud.DEVICE_TOKEN ")
                .append(", au.UTIMESTAMP ")
                .append(" FROM APP_USERS au ")
                .append(" LEFT JOIN APP_USER_DEVICES aud  ")
                .append("    ON aud.APP_USER_ID = au.ID ")
                .append(" WHERE au.PHONE = :PHONE ")
                .append(" AND aud.DEVICE_TOKEN  = :DEVICE_TOKEN ");

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
        String phone = (String) parameters[0];
        String deviceToken = (String) parameters[1];
        params = new HashMap<>();
        params.put("PHONE", phone);
        params.put("DEVICE_TOKEN", deviceToken);
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("PHONE", Types.VARCHAR));
        declareParameter(new SqlParameter("DEVICE_TOKEN", Types.VARCHAR));
    }
}
