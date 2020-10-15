package vn.vmg.ptdv.hola.infra.leadtime.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.infra.leadtime.factory.TotalRecordDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeSearch;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;

public class CountLeadtimes extends MappingSqlQuery implements MappingSQLCommand {

    private HashMap<String, Object> params;
    private LeadtimeSearch search;

    @Override
    protected TotalRecordDB mapRow(ResultSet rs, int i) throws SQLException {
        TotalRecordDB totalRecordDB = new TotalRecordDB();
        totalRecordDB.setTotalRecords(rs.getInt(TotalRecordDB.TOTAL));
        return totalRecordDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT COUNT(*) TOTAL");
        builder.append(" FROM LEADTIME_PACKS lp");
        builder.append(" WHERE ( :" + LeadtimeDB.GLOBAL_SEARCH + " IS NULL OR (LOWER(lp.CODE) = LOWER(:" + LeadtimeDB.GLOBAL_SEARCH + ") OR LOWER(lp.NAME) LIKE LOWER('%' || :" + LeadtimeDB.GLOBAL_SEARCH + " || '%')))");
        builder.append(" AND ( :" + LeadtimeDB.CODE + " IS NULL OR LOWER(lp.CODE) = LOWER(:" + LeadtimeDB.CODE + "))");
        builder.append(" AND ( :" + LeadtimeDB.NAME + " IS NULL OR LOWER(lp.NAME) LIKE LOWER('%' || :" + LeadtimeDB.NAME + " || '%'))");
        builder.append(" AND ( :" + LeadtimeDB.STATUS + " IS NULL OR lp.STATUS = :" + LeadtimeDB.STATUS + ")");
        if (search.getEffectiveAt() != null) {
            builder.append(" AND (TRUNC(lp.EFFECTIVE_AT) = :" + LeadtimeDB.EFFECTIVE_AT + ")");
        }
        if (!isCreateAtFromNull() && !isCreateAtToNull()) {
            builder.append(" AND (TRUNC(lp.CREATED_AT) BETWEEN :" + LeadtimeDB.CREATED_AT_FROM + " AND :" + LeadtimeDB.CREATED_AT_TO + ") ");
        } else {
            if (!isCreateAtFromNull()) {
                builder.append(" AND (TRUNC(lp.CREATED_AT) >= :" + LeadtimeDB.CREATED_AT_FROM + ")");
            }
            if (!isCreateAtToNull()) {
                builder.append(" AND (TRUNC(lp.CREATED_AT) <= :" + LeadtimeDB.CREATED_AT_TO + ")");
            }
        }
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
        this.search = (LeadtimeSearch) parameters[0];
        params = new HashMap<>();
        params.put(LeadtimeDB.GLOBAL_SEARCH, search.getGlobalSearch());
        params.put(LeadtimeDB.CODE, search.getLeadtimeId().getCode());
        params.put(LeadtimeDB.NAME, search.getName());
        params.put(LeadtimeDB.STATUS, search.getStatus());
        if (search.getEffectiveAt() != null) {
            params.put(LeadtimeDB.EFFECTIVE_AT, search.getEffectiveAt().toLocalDate());
        }
        if (!isCreateAtFromNull()) {
            params.put(LeadtimeDB.CREATED_AT_FROM, search.getCreatedAtFrom().getCreatedAt().toLocalDate());
        }
        if (!isCreateAtToNull()) {
            params.put(LeadtimeDB.CREATED_AT_TO, search.getCreatedAtTo().getCreatedAt().toLocalDate());
        }
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(LeadtimeDB.GLOBAL_SEARCH, Types.NVARCHAR));
        declareParameter(new SqlParameter(LeadtimeDB.CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeDB.NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeDB.STATUS, Types.NUMERIC));
        if (search.getEffectiveAt() != null) {
            declareParameter(new SqlParameter(LeadtimeDB.EFFECTIVE_AT, Types.DATE));
        }
        if (!isCreateAtFromNull()) {
            declareParameter(new SqlParameter(LeadtimeDB.CREATED_AT_FROM, Types.DATE));
        }
        if (!isCreateAtToNull()) {
            declareParameter(new SqlParameter(LeadtimeDB.CREATED_AT_TO, Types.DATE));
        }
    }

    private boolean isCreateAtFromNull() {
        if (search.getCreatedAtFrom() == null) {
            return true;
        } else {
            if (search.getCreatedAtFrom().getCreatedAt() == null) {
                return true;
            }
        }
        return false;
    }

    private boolean isCreateAtToNull() {
        if (search.getCreatedAtTo() == null) {
            return true;
        } else {
            if (search.getCreatedAtTo().getCreatedAt() == null) {
                return true;
            }
        }
        return false;
    }
}
