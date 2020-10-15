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
import java.util.HashMap;
import java.util.List;

public class FindListSuggestData extends MappingSqlQuery implements MappingSQLCommand {
    private PagingSortFilter filter;
    HashMap<String, Object> params;

    public FindListSuggestData(PagingSortFilter filter) {
        this.filter = filter;
    }

    @Override
    protected Object mapRow(ResultSet rs, int i) throws SQLException {
        LeadtimeDB leadtimeDB = new LeadtimeDB();
        if (filter.getFieldSort().get(0).equalsIgnoreCase(LeadtimeDB.NAME)) {
            leadtimeDB.setName(rs.getString(LeadtimeDB.NAME));
        } else if (filter.getFieldSort().get(0).equalsIgnoreCase(LeadtimeDB.CODE)) {
            leadtimeDB.setCode(rs.getString(LeadtimeDB.CODE));
        }
        return leadtimeDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder orderBy = new StringBuilder();
        orderBy.append(" " + filter.getFieldSort().get(0) + " ");
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT " + orderBy)
                .append(" FROM LEADTIME_PACKS ")
                .append(" WHERE ( :" + LeadtimeDB.SEARCH_SUGGEST + " IS NULL OR " + orderBy + " LIKE :" +
                        LeadtimeDB.SEARCH_SUGGEST + " )")
                .append(" ORDER BY " + orderBy + " ASC ");

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
        LeadtimeSearch search = (LeadtimeSearch) parameters[0];
        params = new HashMap<>();
        params.put(LeadtimeDB.SEARCH_SUGGEST, "%"+search.getSearchSuggest()+"%");
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(LeadtimeDB.SEARCH_SUGGEST, Types.VARCHAR));
    }
}
