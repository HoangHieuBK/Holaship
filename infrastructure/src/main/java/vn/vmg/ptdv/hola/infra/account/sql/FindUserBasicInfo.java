package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.account.factory.HolaProfileDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindUserBasicInfo extends MappingSqlQuery implements MappingSQLCommand {
    private Map<String, Object> params;

    @Override
    protected HolaProfileDB mapRow(ResultSet rs, int rowNum) throws SQLException {
        HolaProfileDB profileDB = new HolaProfileDB();
        profileDB.setId(rs.getLong("ID"));
        profileDB.setPhone(rs.getString("PHONE"));
        profileDB.setDeviceToken(rs.getString("DEVICE_TOKEN"));
        profileDB.setShop(rs.getInt("SHOP"));
        profileDB.setShip(rs.getInt("SHIP"));
        profileDB.setAvatar(rs.getString("AVATAR"));
        profileDB.setEmail(rs.getString("EMAIL"));
        profileDB.setSex(rs.getInt("SEX"));
        profileDB.setName(rs.getString("NAME"));
        profileDB.setBirthday(rs.getObject("BIRTHDAY", LocalDate.class));
        profileDB.setUTimestamp(rs.getTimestamp("UTIMESTAMP"));
        return profileDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ")
                .append("  au.ID  ")
                .append(", au.PHONE ")
                .append(", au.SHOP ")
                .append(", au.SHIP ")
                .append(", au.NAME ")
                .append(", au.EMAIL ")
                .append(", au.SEX ")
                .append(", au.AVATAR ")
                .append(", au.BIRTHDAY ")
                .append(", au.UTIMESTAMP ")
                .append(", aud.DEVICE_TOKEN ")
                .append(" FROM APP_USERS au ")
                .append(" LEFT JOIN APP_USER_DEVICES aud  ")
                .append("    ON aud.APP_USER_ID = au.ID ")
                .append(" WHERE au.PHONE = :PHONE ");

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
        params = new HashMap<>();
        params.put("PHONE", phone);
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("PHONE", Types.VARCHAR));
    }
}
