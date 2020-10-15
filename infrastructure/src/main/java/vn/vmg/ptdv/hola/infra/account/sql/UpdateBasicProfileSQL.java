package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.infra.account.factory.BasicProfileDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class UpdateBasicProfileSQL extends SqlUpdate implements UpdateSQLCommand<AccountId> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(" UPDATE APP_USERS au ")
                .append("SET")
                .append("    au.NAME = :NAME ")
                .append("   ,au.SEX = :SEX ")
                .append("   ,au.AVATAR = :AVATAR ")
                .append("   ,au.BIRTHDAY = :BIRTHDAY ")
                .append("   ,au.UTIMESTAMP = SYSTIMESTAMP ")
                .append(" WHERE ")
                .append("    au.PHONE = :PHONE ")
                .append("    AND au.UTIMESTAMP = :UTIMESTAMP ");

        return builder.toString();
    }

    @Override
    public int updateCommand(DataSource dataSource, AccountId accountId) {
        this.setDataSource(dataSource);
        this.setSql(this.prepareSQL());
        this.declareParameters();
        compile();
        return this.updateByNamedParam(params);
    }

    @Override
    public void prepareParams(Object... parameters) {
        BasicProfileDB basicProfileDB = (BasicProfileDB) parameters[0];
        params = new HashMap<>();
        params.put("NAME", basicProfileDB.getName());
        params.put("SEX", basicProfileDB.getSex());
        params.put("AVATAR", basicProfileDB.getAvatar());
        params.put("BIRTHDAY", basicProfileDB.getBirthday());
        params.put("PHONE", basicProfileDB.getPhone());
        params.put("UTIMESTAMP", basicProfileDB.getUTimestamp());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("NAME", Types.VARCHAR));
        declareParameter(new SqlParameter("SEX", Types.VARCHAR));
        declareParameter(new SqlParameter("AVATAR", Types.VARCHAR));
        declareParameter(new SqlParameter("BIRTHDAY", Types.DATE));
        declareParameter(new SqlParameter("PHONE", Types.VARCHAR));
        declareParameter(new SqlParameter("UTIMESTAMP", Types.TIMESTAMP_WITH_TIMEZONE));
    }
}
