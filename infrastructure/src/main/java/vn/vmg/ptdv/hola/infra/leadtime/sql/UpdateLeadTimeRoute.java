package vn.vmg.ptdv.hola.infra.leadtime.sql;


import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeRouteDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;


public class UpdateLeadTimeRoute extends SqlUpdate implements UpdateSQLCommand<LeadtimeRouteId> {

    private HashMap<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE leadtime_pack_routes ");
        builder.append("SET ");
        builder.append("  TYPE = :" + LeadtimeRouteDB.TYPE + ", ");
        builder.append("  STATUS = :" + LeadtimeRouteDB.STATUS + ", ");
        builder.append("  FROM_PROVINCE_ID = :" + LeadtimeRouteDB.FROM_PROVINCE_ID + ", ");
        builder.append("  FROM_PROVINCE_CODE = :" + LeadtimeRouteDB.FROM_PROVINCE_CODE + ", ");
        builder.append("  TO_PROVINCE_ID = :" + LeadtimeRouteDB.TO_PROVINCE_ID + ", ");
        builder.append("  TO_PROVINCE_CODE = :" + LeadtimeRouteDB.TO_PROVINCE_CODE + ", ");
        builder.append("  ESTIMATED_DELIVERY_DAY = :" + LeadtimeRouteDB.ESTIMATED_DELIVERY_DAY + ", ");
        builder.append("  TRANSPORT_TYPE = :" + LeadtimeRouteDB.TRANSPORT_TYPE + ", ");
        builder.append("  LIMIT_WEIGHT = :" + LeadtimeRouteDB.LIMIT_WEIGHT + ", ");
        builder.append("  LIMIT_TYPE = :" + LeadtimeRouteDB.LIMIT_TYPE + ", ");
        builder.append("  MAX_WEIGHT = :" + LeadtimeRouteDB.MAX_WEIGHT + ", ");
        builder.append("  FROM_DISTRICT_ID = :" + LeadtimeRouteDB.FROM_DISTRICT_ID + ", ");
        builder.append("  TO_DISTRICT_ID = :" + LeadtimeRouteDB.TO_DISTRICT_ID + ", ");
        builder.append("  FROM_DISTRICT_CODE = :" + LeadtimeRouteDB.FROM_DISTRICT_CODE + ", ");
        builder.append("  TO_DISTRICT_CODE = :" + LeadtimeRouteDB.TO_DISTRICT_CODE + ", ");
        builder.append("  UTIMESTAMP = SYSTIMESTAMP ");
        builder.append("WHERE ID = :" + LeadtimeRouteDB.ID + " AND UTIMESTAMP = :" + LeadtimeRouteDB.UTIMESTAMP);
        return builder.toString();
    }

    @Override
    public int updateCommand(DataSource dataSource, LeadtimeRouteId leadtimeRouteId) {
        this.setDataSource(dataSource);
        this.setSql(prepareSQL());
        this.declareParameters();
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        setGeneratedKeysColumnNames("ID");
//        setReturnGeneratedKeys(true);
        compile();
        return updateByNamedParam(params);
    }

    @Override
    public void prepareParams(Object... parameters) {
        LeadtimeRouteDB leadtimeRouteDB = (LeadtimeRouteDB) parameters[0];

        params = new HashMap<>();
        params.put(LeadtimeRouteDB.ID, leadtimeRouteDB.getId());
        params.put(LeadtimeRouteDB.TYPE, leadtimeRouteDB.getType());
        params.put(LeadtimeRouteDB.STATUS, leadtimeRouteDB.getStatus());
        params.put(LeadtimeRouteDB.FROM_PROVINCE_ID, leadtimeRouteDB.getFromProvinceId());
        params.put(LeadtimeRouteDB.FROM_PROVINCE_CODE, leadtimeRouteDB.getFromProvinceCode());
        params.put(LeadtimeRouteDB.TO_PROVINCE_ID, leadtimeRouteDB.getToProvinceId());
        params.put(LeadtimeRouteDB.TO_PROVINCE_CODE, leadtimeRouteDB.getToProvinceCode());
        params.put(LeadtimeRouteDB.ESTIMATED_DELIVERY_DAY, leadtimeRouteDB.getEstimatedDeliveryDay());
        params.put(LeadtimeRouteDB.TRANSPORT_TYPE, leadtimeRouteDB.getTransportType());
        params.put(LeadtimeRouteDB.LIMIT_WEIGHT, leadtimeRouteDB.getLimitWeight());
        params.put(LeadtimeRouteDB.LIMIT_TYPE, leadtimeRouteDB.getLimitType());
        params.put(LeadtimeRouteDB.MAX_WEIGHT, leadtimeRouteDB.getMaxWeight());
        params.put(LeadtimeRouteDB.FROM_DISTRICT_ID, leadtimeRouteDB.getFromDistrictId());
        params.put(LeadtimeRouteDB.TO_DISTRICT_ID, leadtimeRouteDB.getToDistrictId());
        params.put(LeadtimeRouteDB.FROM_DISTRICT_CODE, leadtimeRouteDB.getFromDistrictCode());
        params.put(LeadtimeRouteDB.TO_DISTRICT_CODE, leadtimeRouteDB.getToDistrictCode());
        params.put(LeadtimeRouteDB.UTIMESTAMP, leadtimeRouteDB.getUTimestamp());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(LeadtimeRouteDB.ID, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.TYPE, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.STATUS, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.FROM_PROVINCE_ID, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.FROM_PROVINCE_CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeRouteDB.TO_PROVINCE_ID, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.TO_PROVINCE_CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeRouteDB.ESTIMATED_DELIVERY_DAY, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.TRANSPORT_TYPE, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeRouteDB.LIMIT_WEIGHT, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.LIMIT_TYPE, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.MAX_WEIGHT, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.FROM_DISTRICT_ID, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.TO_DISTRICT_ID, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.FROM_DISTRICT_CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeRouteDB.TO_DISTRICT_CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeRouteDB.UTIMESTAMP, Types.TIMESTAMP_WITH_TIMEZONE));
    }

}
