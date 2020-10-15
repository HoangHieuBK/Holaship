package vn.vmg.ptdv.hola.infra.leadtime.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindLeadtimeById extends MappingSqlQuery implements MappingSQLCommand {
    private Map<String, Object> params;

    @Override
    protected LeadtimeDB mapRow(ResultSet rs, int i) throws SQLException {
        LeadtimeDB objDB = new LeadtimeDB();
        objDB.setId(rs.getLong(LeadtimeDB.ID));
        objDB.setCode(rs.getString(LeadtimeDB.CODE));
        objDB.setName(rs.getString(LeadtimeDB.NAME));
        objDB.setStatus(rs.getInt(LeadtimeDB.STATUS));
        objDB.setNote(rs.getString(LeadtimeDB.NOTE));
        objDB.setEffectiveAt(rs.getObject(LeadtimeDB.EFFECTIVE_AT, OffsetDateTime.class));
        objDB.setCreatedAt(rs.getObject(LeadtimeDB.CREATED_AT, OffsetDateTime.class));
        objDB.setCreatedBy(rs.getLong(LeadtimeDB.CREATED_BY));
        objDB.setUpdatedAt(rs.getObject(LeadtimeDB.UPDATED_AT, OffsetDateTime.class));
        objDB.setUpdatedBy(rs.getLong(LeadtimeDB.UPDATED_BY));
        objDB.setUTimestamp(rs.getTimestamp(LeadtimeDB.UTIMESTAMP));

        return objDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ")
                .append("  lp.ID,  ")
                .append("  lp.CODE, ")
                .append("  lp.NAME, ")
                .append("  lp.STATUS, ")
                .append("  lp.NOTE, ")
                .append("  lp.EFFECTIVE_AT, ")
                .append("  lp.CREATED_AT, ")
                .append("  lp.CREATED_BY, ")
                .append("  lp.UPDATED_AT, ")
                .append("  lp.UPDATED_BY, ")
                .append("  lp.UTIMESTAMP ")
                .append(" FROM leadtime_packs lp ")
                .append(" WHERE lp.ID = :ID ");

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
        Long id = (Long) parameters[0];
        params = new HashMap<>();
        params.put(LeadtimeDB.ID, id);
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(LeadtimeDB.ID, Types.NUMERIC));
    }
}
