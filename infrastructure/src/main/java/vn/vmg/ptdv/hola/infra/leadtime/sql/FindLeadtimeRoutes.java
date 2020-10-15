package vn.vmg.ptdv.hola.infra.leadtime.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeRouteDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeRouteSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindLeadtimeRoutes extends MappingSqlQuery implements MappingSQLCommand {

    private Map<String, Object> params;
    private PagingSortFilter filter;

    public FindLeadtimeRoutes(PagingSortFilter filter) {
        this.filter = filter;
    }

    @Override
    protected LeadtimeRouteDB mapRow(ResultSet rs, int i) throws SQLException {
        LeadtimeRouteDB objDB = new LeadtimeRouteDB();
        objDB.setId(rs.getLong(LeadtimeRouteDB.ID));
        objDB.setLeadTimePackId(rs.getLong(LeadtimeRouteDB.LEADTIME_PACK_ID));
        objDB.setCode(rs.getString(LeadtimeRouteDB.CODE));
        objDB.setType(rs.getInt(LeadtimeRouteDB.TYPE));
        objDB.setStatus(rs.getInt(LeadtimeRouteDB.STATUS));
        objDB.setFromProvinceId(rs.getLong(LeadtimeRouteDB.FROM_PROVINCE_ID));
        objDB.setFromProvinceCode(rs.getString(LeadtimeRouteDB.FROM_PROVINCE_CODE));
        objDB.setToProvinceId(rs.getLong(LeadtimeRouteDB.TO_PROVINCE_ID));
        objDB.setToProvinceCode(rs.getString(LeadtimeRouteDB.TO_PROVINCE_CODE));
        objDB.setEstimatedDeliveryDay(rs.getInt(LeadtimeRouteDB.ESTIMATED_DELIVERY_DAY));
        objDB.setTransportType(rs.getInt(LeadtimeRouteDB.TRANSPORT_TYPE));
        objDB.setLimitWeight(rs.getDouble(LeadtimeRouteDB.LIMIT_WEIGHT));
        objDB.setLimitType(rs.getInt(LeadtimeRouteDB.LIMIT_TYPE));
        objDB.setMaxWeight(rs.getDouble(LeadtimeRouteDB.MAX_WEIGHT));
        objDB.setFromDistrictId(rs.getLong(LeadtimeRouteDB.FROM_DISTRICT_ID));
        objDB.setToDistrictId(rs.getLong(LeadtimeRouteDB.TO_DISTRICT_ID));
        objDB.setFromDistrictCode(rs.getString(LeadtimeRouteDB.FROM_DISTRICT_CODE));
        objDB.setToDistrictCode(rs.getString(LeadtimeRouteDB.TO_DISTRICT_CODE));
        objDB.setFromProvinceName(rs.getString(LeadtimeRouteDB.FROM_PROVINCE_NAME));
        objDB.setToProvinceName(rs.getString(LeadtimeRouteDB.TO_PROVINCE_NAME));
        objDB.setFromDistrictName(rs.getString(LeadtimeRouteDB.FROM_DISTRICT_NAME));
        objDB.setToDistrictName(rs.getString(LeadtimeRouteDB.TO_DISTRICT_NAME));
        objDB.setUTimestamp(rs.getTimestamp(LeadtimeRouteDB.UTIMESTAMP));
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
                .append(" WHERE lpr.LEADTIME_PACK_ID = :LEADTIME_PACK_ID ");

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
        LeadtimeRouteSearch leadtimeRouteSearch = (LeadtimeRouteSearch) parameters[0];
        params = new HashMap<>();
        params.put(LeadtimeRouteDB.LEADTIME_PACK_ID, leadtimeRouteSearch.getLeadtimeId().getId());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(LeadtimeRouteDB.LEADTIME_PACK_ID, Types.NUMERIC));
    }

}
