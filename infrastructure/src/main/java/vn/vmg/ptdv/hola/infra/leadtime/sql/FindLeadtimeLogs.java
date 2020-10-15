package vn.vmg.ptdv.hola.infra.leadtime.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeLogDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeLogSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindLeadtimeLogs extends MappingSqlQuery implements MappingSQLCommand {

    private Map<String, Object> params;

    public FindLeadtimeLogs() {

    }

    @Override
    protected LeadtimeLogDB mapRow(ResultSet rs, int i) throws SQLException {
        LeadtimeLogDB objDB = new LeadtimeLogDB();
        objDB.setId(rs.getLong(LeadtimeLogDB.ID));
        objDB.setCreatedAt(rs.getObject(LeadtimeLogDB.CREATED_AT, LocalDate.class));
        objDB.setCreatedBy(rs.getLong(LeadtimeLogDB.CREATED_BY));
        objDB.setDataUpdated(rs.getString(LeadtimeLogDB.DATA_UPDATED));
        objDB.setLeadtimeId(rs.getLong(LeadtimeLogDB.LEADTIME_PACK_ID));
        return objDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ")
                .append(" ll.ID, ")
                .append(" ll.CREATED_AT, ")
                .append(" ll.CREATED_BY, ")
                .append(" ll.DATA_UPDATED, ")
                .append(" ll.LEADTIME_PACK_ID ")
                .append("FROM LEADTIME_LOGS ll ")
                .append("WHERE (:" + LeadtimeLogDB.ID + " IS NULL OR ll.ID = :" + LeadtimeLogDB.ID + ") ")
                .append(" AND (:" + LeadtimeLogDB.CREATED_AT + " IS NULL OR ll.CREATED_AT = :" + LeadtimeLogDB.CREATED_AT + ") ")
                .append(" AND (:" + LeadtimeLogDB.CREATED_BY + " IS NULL OR ll.CREATED_BY = :" + LeadtimeDB.CREATED_BY + ") ")
                .append(" AND (:" + LeadtimeLogDB.DATA_UPDATED + " IS NULL OR ll.DATA_UPDATED LIKE :" + LeadtimeLogDB.DATA_UPDATED + ") ")
                .append(" AND (:" + LeadtimeLogDB.LEADTIME_PACK_ID + " IS NULL OR ll.LEADTIME_PACK_ID = :" + LeadtimeLogDB.LEADTIME_PACK_ID + ") ")
                .append(" ORDER BY ").append(":ORDER_BY ")
                .append(" OFFSET :OFFSET ROWS ")
                .append(" FETCH NEXT :FETCH_NEXT ROWS ONLY ");
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
        LeadtimeLogSearch search = (LeadtimeLogSearch) parameters[0];
        PagingSortFilter filter = (PagingSortFilter) parameters[1];
        int offset = (filter.getPageIndex() - 1) * filter.getPageSize();

        StringBuilder orderBy = new StringBuilder();
        if (filter.getFieldSort() == null && filter.getFieldSort().isEmpty()) {
            for (String fieldSort : filter.getFieldSort()
            ) {
                orderBy.append(" ll." + fieldSort);
            }
        }
        orderBy.append(filter.getAsc() ? " ASC " : " DESC ");

        params = new HashMap<>();
        params.put(LeadtimeLogDB.ID, search.getLeadtimeLogId().getId());
        params.put(LeadtimeLogDB.CREATED_AT, search.getAuditLog().getCreatedAt());
        params.put(LeadtimeLogDB.CREATED_BY, search.getAuditLog().getCreatedBy());
        params.put(LeadtimeLogDB.DATA_UPDATED, search.getDataUpdated());
        params.put(LeadtimeLogDB.LEADTIME_PACK_ID, search.getLeadtimeLogId().getId());
        params.put("ORDER_BY", orderBy);
        params.put("OFFSET", offset);
        params.put("FETCH_NEXT", filter.getPageSize());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(LeadtimeLogDB.ID, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeLogDB.CREATED_AT, Types.DATE));
        declareParameter(new SqlParameter(LeadtimeLogDB.CREATED_BY, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeLogDB.DATA_UPDATED, Types.NVARCHAR));
        declareParameter(new SqlParameter(LeadtimeLogDB.LEADTIME_PACK_ID, Types.NUMERIC));
        declareParameter(new SqlParameter("ORDER_BY", Types.VARCHAR));
        declareParameter(new SqlParameter("OFFSET", Types.NUMERIC));
        declareParameter(new SqlParameter("FETCH_NEXT", Types.NUMERIC));
    }
}
