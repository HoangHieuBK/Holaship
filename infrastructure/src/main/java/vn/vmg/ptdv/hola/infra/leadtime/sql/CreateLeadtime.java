package vn.vmg.ptdv.hola.infra.leadtime.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class CreateLeadtime extends SqlUpdate implements UpdateSQLCommand<LeadtimeId> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO LEADTIME_PACKS (")
                .append("CODE,NAME,STATUS,CREATED_AT,CREATED_BY,EFFECTIVE_AT,UTIMESTAMP")
                .append(" ) VALUES ( ")
                .append(" :" + LeadtimeDB.NAME)
                .append(", :" + LeadtimeDB.CODE)
                .append(", :" + LeadtimeDB.STATUS)
                .append(",SYSDATE")
                .append(", :" + LeadtimeDB.CREATED_BY)
                .append(", :" + LeadtimeDB.EFFECTIVE_AT)
                .append(",SYSTIMESTAMP")
                .append(" )");
        return builder.toString();
    }

    @Override
    public int updateCommand(DataSource dataSource, LeadtimeId leadtimeId) {
        this.setDataSource(dataSource);
        this.setSql(prepareSQL());
        this.declareParameters();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        setGeneratedKeysColumnNames("ID");
        setReturnGeneratedKeys(true);
        compile();
        int executedRecord = this.updateByNamedParam(params, keyHolder);
        if (executedRecord > 0) {
            leadtimeId.setId(keyHolder.getKey().longValue());
        }

        return executedRecord;
    }

    @Override
    public void prepareParams(Object... parameters) {
        LeadtimeDB leadtimeDB = (LeadtimeDB) parameters[0];
        params = new HashMap<>();
        params.put(LeadtimeDB.CODE, leadtimeDB.getCode());
        params.put(LeadtimeDB.NAME, leadtimeDB.getName());
        params.put(LeadtimeDB.STATUS, leadtimeDB.getStatus());
        params.put(LeadtimeDB.CREATED_BY, leadtimeDB.getCreatedBy());
        params.put(LeadtimeDB.EFFECTIVE_AT, leadtimeDB.getEffectiveAt());

    }

    @Override
    public void declareParameters() {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
        declareParameter(new SqlParameter(LeadtimeDB.CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeDB.NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeDB.STATUS, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeDB.CREATED_BY, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeDB.EFFECTIVE_AT, Types.DATE));

    }

}
