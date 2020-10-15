package vn.vmg.ptdv.hola.infra.leadtime.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindLeadtimes extends MappingSqlQuery implements MappingSQLCommand {

    private Map<String, Object> params;
    private LeadtimeSearch search;
    private PagingSortFilter filter;

    public FindLeadtimes() {
    }

    @Override
    protected LeadtimeDB mapRow(ResultSet rs, int i) throws SQLException {
        LeadtimeDB leadtimeDB = new LeadtimeDB();
        leadtimeDB.setId(rs.getLong(LeadtimeDB.ID));
        leadtimeDB.setCode(rs.getString(LeadtimeDB.CODE));
        leadtimeDB.setName(rs.getString(LeadtimeDB.NAME));
        leadtimeDB.setStatus(rs.getInt(LeadtimeDB.STATUS));
        leadtimeDB.setNote(rs.getString(LeadtimeDB.NOTE));
        leadtimeDB.setEffectiveAt(rs.getObject(LeadtimeDB.EFFECTIVE_AT, OffsetDateTime.class));
        leadtimeDB.setCreatedAt(rs.getObject(LeadtimeDB.CREATED_AT, OffsetDateTime.class));
        leadtimeDB.setCreatedBy(rs.getLong(LeadtimeDB.CREATED_BY));
        leadtimeDB.setUpdatedAt(rs.getObject(LeadtimeDB.UPDATED_AT, OffsetDateTime.class));
        leadtimeDB.setUpdatedBy(rs.getLong(LeadtimeDB.UPDATED_BY));
        leadtimeDB.setUTimestamp(rs.getTimestamp(LeadtimeDB.UTIMESTAMP));
        return leadtimeDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder orderBy = new StringBuilder();
        if (filter.getFieldSort() == null && filter.getFieldSort().isEmpty()) {
            for (String fieldSort : filter.getFieldSort()
            ) {
                orderBy.append(" lp." + fieldSort);
            }
        } else {
            orderBy.append("lp.CREATED_AT");
        }
        orderBy.append(filter.getAsc() ? " ASC " : " DESC ");

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT lp.ID,");
        builder.append(" lp.CODE,");
        builder.append(" lp.NAME,");
        builder.append(" lp.STATUS,");
        builder.append(" lp.NOTE,");
        builder.append(" lp.EFFECTIVE_AT,");
        builder.append(" lp.CREATED_AT,");
        builder.append(" lp.CREATED_BY,");
        builder.append(" lp.UPDATED_AT,");
        builder.append(" lp.UPDATED_BY,");
        builder.append(" lp.UTIMESTAMP ");
        builder.append(" FROM LEADTIME_PACKS lp ");
        builder.append(" WHERE ( :" + LeadtimeDB.GLOBAL_SEARCH + " IS NULL OR (LOWER(lp.CODE) = LOWER(:" + LeadtimeDB.GLOBAL_SEARCH + ") OR LOWER(lp.NAME) LIKE LOWER('%' || :" + LeadtimeDB.GLOBAL_SEARCH + " || '%'))) ");
        builder.append(" AND ( :" + LeadtimeDB.CODE + " IS NULL OR LOWER(lp.CODE) = LOWER(:" + LeadtimeDB.CODE + "))");
        builder.append(" AND ( :" + LeadtimeDB.NAME + " IS NULL OR LOWER(lp.NAME) LIKE LOWER('%' || :" + LeadtimeDB.NAME + " || '%')) ");
        builder.append(" AND ( :" + LeadtimeDB.STATUS + " IS NULL OR lp.STATUS = :" + LeadtimeDB.STATUS + ") ");
        if (search.getEffectiveAt() != null) {
            builder.append(" AND (lp.EFFECTIVE_AT = :" + LeadtimeDB.EFFECTIVE_AT + ") ");
        }
        if (!isCreateAtFromNull() && !isCreateAtToNull()) {
            builder.append(" AND ((lp.CREATED_AT >= :" + LeadtimeDB.CREATED_AT_FROM + ") AND (lp.CREATED_AT <= :" + LeadtimeDB.CREATED_AT_TO + ")) ");
        } else {
            if (!isCreateAtFromNull()) {
                builder.append(" AND (lp.CREATED_AT >= :" + LeadtimeDB.CREATED_AT_FROM + ") ");
            }
            if (!isCreateAtToNull()) {
                builder.append(" AND (lp.CREATED_AT <= :" + LeadtimeDB.CREATED_AT_TO + ") ");
            }
        }
        builder.append(" ORDER BY ").append(orderBy);
        builder.append(" OFFSET :" + LeadtimeDB.OFFSET + " ROWS");
        builder.append(" FETCH NEXT :" + LeadtimeDB.FETCH_NEXT + " ROWS ONLY");
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
        this.filter = (PagingSortFilter) parameters[1];

        params = new HashMap<>();
        params.put(LeadtimeDB.GLOBAL_SEARCH, search.getGlobalSearch());
        params.put(LeadtimeDB.CODE, search.getLeadtimeId().getCode());
        params.put(LeadtimeDB.NAME, search.getName());
        params.put(LeadtimeDB.STATUS, search.getStatus());
//        params.put(LeadtimeDB.NOTE, search.getNote());
        if (search.getEffectiveAt() != null) {
            params.put(LeadtimeDB.EFFECTIVE_AT, search.getEffectiveAt());
        }
        if (!isCreateAtFromNull()) {
            params.put(LeadtimeDB.CREATED_AT_FROM, search.getCreatedAtFrom().getCreatedAt().toLocalDateTime());
        }
        if (!isCreateAtToNull()) {
            params.put(LeadtimeDB.CREATED_AT_TO, search.getCreatedAtTo().getCreatedAt().toLocalDateTime());
        }
        params.put(LeadtimeDB.OFFSET, (filter.getPageIndex() - 1) * filter.getPageSize());
        params.put(LeadtimeDB.FETCH_NEXT,
                filter.getPageSize() <= 0 ? 15 : filter.getPageSize());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(LeadtimeDB.GLOBAL_SEARCH, Types.NVARCHAR));
        declareParameter(new SqlParameter(LeadtimeDB.CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeDB.NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeDB.STATUS, Types.NUMERIC));
//        declareParameter(new SqlParameter(LeadtimeDB.NOTE, Types.NVARCHAR));
        if (search.getEffectiveAt() != null) {
            declareParameter(new SqlParameter(LeadtimeDB.EFFECTIVE_AT, Types.DATE));
        }
        if (!isCreateAtFromNull()) {
            declareParameter(new SqlParameter(LeadtimeDB.CREATED_AT_FROM, Types.DATE));
        }
        if (!isCreateAtToNull()) {
            declareParameter(new SqlParameter(LeadtimeDB.CREATED_AT_TO, Types.DATE));
        }
        declareParameter(new SqlParameter(LeadtimeDB.OFFSET, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeDB.FETCH_NEXT, Types.NUMERIC));
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
