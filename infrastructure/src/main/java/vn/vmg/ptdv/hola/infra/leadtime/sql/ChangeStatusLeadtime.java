package vn.vmg.ptdv.hola.infra.leadtime.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;
import vn.vmg.ptdv.hola.leadtime.factory.Leadtime;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class ChangeStatusLeadtime extends SqlUpdate implements UpdateSQLCommand<Leadtime> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(" UPDATE LEADTIME_PACKS lp")
                .append(" SET")
                .append(" lp.UTIMESTAMP = SYSTIMESTAMP,")
                .append(" lp.STATUS = :" + LeadtimeDB.STATUS)
                .append(" WHERE")
                .append(" lp.ID = :" + LeadtimeDB.ID)
                .append(" AND lp.UTIMESTAMP = :" + LeadtimeDB.UTIMESTAMP);
        return builder.toString();
    }

    @Override
    public int updateCommand(DataSource dataSource, Leadtime leadtime) {
        this.setDataSource(dataSource);
        this.setSql(prepareSQL());
        this.declareParameters();
        compile();
        return this.updateByNamedParam(params);
    }

    @Override
    public void prepareParams(Object... parameters) {
        Long id = (Long) parameters[0];
        int status = (int) parameters[1];
        Timestamp uTimestamp = (Timestamp) parameters[2];
        params = new HashMap<>();
        params.put(LeadtimeDB.STATUS, status);
        params.put(LeadtimeDB.ID, id);
        params.put(LeadtimeDB.UTIMESTAMP, uTimestamp);
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(LeadtimeDB.STATUS, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeDB.ID, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeDB.UTIMESTAMP, Types.TIMESTAMP_WITH_TIMEZONE));
    }
}
