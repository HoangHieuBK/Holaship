package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.AuditLog;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class ActiveAccountShip extends SqlUpdate implements UpdateSQLCommand<AccountId> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(" UPDATE APP_USERS SET ")
                .append("  SHIP = 1 ")
                .append("  ,UTIMESTAMP = SYSTIMESTAMP ")
                .append(" WHERE PHONE = :PHONE ")
                .append(" AND SHIP = 0 ")
                .append(" AND UTIMESTAMP = :UTIMESTAMP ");
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
        String phone = (String) parameters[0];
        AuditLog auditLog = (AuditLog) parameters[1];
        params = new HashMap<>();
        params.put("PHONE", phone);
        params.put("UTIMESTAMP", auditLog.getUTimestamp());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("PHONE", Types.VARCHAR));
        declareParameter(new SqlParameter("UTIMESTAMP", Types.TIMESTAMP_WITH_TIMEZONE));

    }
}
