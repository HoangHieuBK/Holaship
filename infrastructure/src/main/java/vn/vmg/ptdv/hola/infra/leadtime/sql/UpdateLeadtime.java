package vn.vmg.ptdv.hola.infra.leadtime.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;
import vn.vmg.ptdv.hola.leadtime.factory.Leadtime;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class UpdateLeadtime extends SqlUpdate implements UpdateSQLCommand<Leadtime> {

    private Map<String, Object> params;

    public UpdateLeadtime() {
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(" UPDATE LEADTIME_PACKS SET ");
        builder.append("  NAME = :" + LeadtimeDB.NAME + ", ");
        builder.append("  STATUS = :" + LeadtimeDB.STATUS + ", ");
        builder.append("  NOTE = :NOTE, ");
        builder.append("  EFFECTIVE_AT = :" + LeadtimeDB.EFFECTIVE_AT + ", ");
        builder.append("  UTIMESTAMP = SYSTIMESTAMP ");
        builder.append(" WHERE ID = :" + LeadtimeDB.ID + " AND UTIMESTAMP = :" + LeadtimeDB.UTIMESTAMP);
        return builder.toString();
    }

    @Override
    public int updateCommand(DataSource dataSource, Leadtime leadtime) {
        this.setDataSource(dataSource);
        this.setSql(this.prepareSQL());
        this.declareParameters();
        compile();
        int executedRecord = this.updateByNamedParam(params);
        return executedRecord;
    }

    @Override
    public void prepareParams(Object... parameters) {
        LeadtimeDB objDb = (LeadtimeDB) parameters[0];
        params = new HashMap<>();
        params.put(LeadtimeDB.NAME, objDb.getName());
        params.put(LeadtimeDB.STATUS, objDb.getStatus());
        params.put(LeadtimeDB.NOTE, objDb.getNote());
        params.put(LeadtimeDB.EFFECTIVE_AT, objDb.getEffectiveAt() != null ? objDb.getEffectiveAt().toLocalDate() : null);
        params.put(LeadtimeDB.UTIMESTAMP, objDb.getUTimestamp());
        params.put(LeadtimeDB.ID, objDb.getId());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(LeadtimeDB.NAME, Types.NVARCHAR));
        declareParameter(new SqlParameter(LeadtimeDB.STATUS, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeDB.NOTE, Types.NVARCHAR));
        declareParameter(new SqlParameter(LeadtimeDB.EFFECTIVE_AT, Types.DATE));
        declareParameter(new SqlParameter(LeadtimeDB.UTIMESTAMP, Types.TIMESTAMP_WITH_TIMEZONE));
        declareParameter(new SqlParameter(LeadtimeDB.ID, Types.NUMERIC));
    }
}
