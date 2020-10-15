package vn.vmg.ptdv.hola.infra.leadtime.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeRouteDB;
import vn.vmg.ptdv.hola.infra.leadtime.mapper.LeadtimeRouteDBMapper;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindLeadtimeRouteById extends MappingSqlQuery implements MappingSQLCommand {
    private Map<String, Object> params;

    @Override
    protected LeadtimeRouteDB mapRow(ResultSet rs, int i) throws SQLException {
        LeadtimeRouteDB objDB = LeadtimeRouteDBMapper.getInstance().fromResultSet(rs);
        return objDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ")
                .append("  lpr.ID,  ")
                .append("  lpr.LEADTIME_PACK_ID, ")
                .append("  lpr.CODE, ")
                .append("  lpr.TYPE, ")
                .append("  lpr.STATUS, ")
                .append("  lpr.FROM_PROVINCE_ID, ")
                .append("  lpr.FROM_PROVINCE_CODE, ")
                .append("  lpr.TO_PROVINCE_ID, ")
                .append("  lpr.TO_PROVINCE_CODE, ")
                .append("  lpr.ESTIMATED_DELIVERY_DAY, ")
                .append("  lpr.TRANSPORT_TYPE, ")
                .append("  lpr.LIMIT_WEIGHT, ")
                .append("  lpr.LIMIT_TYPE, ")
                .append("  lpr.MAX_WEIGHT, ")
                .append("  lpr.FROM_DISTRICT_ID, ")
                .append("  lpr.TO_DISTRICT_ID, ")
                .append("  lpr.FROM_DISTRICT_CODE, ")
                .append("  lpr.TO_DISTRICT_CODE, ")
                .append("  lpr.UTIMESTAMP, ")
                .append("  p1.name FROM_PROVINCE_NAME, ")
                .append("  p2.name TO_PROVINCE_NAME, ")
                .append("  d1.name FROM_DISTRICT_NAME, ")
                .append("  d2.name TO_DISTRICT_NAME ")
                .append(" FROM leadtime_pack_routes lpr ")
                .append(" LEFT JOIN provinces p1 ON lpr.FROM_PROVINCE_ID = p1.id ")
                .append(" LEFT JOIN provinces p2 ON lpr.TO_PROVINCE_ID = p2.id ")
                .append(" LEFT JOIN districts d1 ON lpr.FROM_DISTRICT_ID = d1.id ")
                .append(" LEFT JOIN districts d2 ON lpr.TO_DISTRICT_ID = d2.id ")
                .append(" WHERE lpr.id = :ID ");
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
        params.put(LeadtimeRouteDB.ID, id);
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(LeadtimeRouteDB.ID, Types.NUMERIC));
    }
}
