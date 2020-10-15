package vn.vmg.ptdv.hola.infra.leadtime.sql;


import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeRouteDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;


public class CreateLeadTimeRoute extends SqlUpdate implements UpdateSQLCommand<LeadtimeRouteId> {

    private HashMap<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO LEADTIME_PACK_ROUTES (")
                .append("LEADTIME_PACK_ID,TYPE,STATUS,FROM_PROVINCE_ID,FROM_PROVINCE_CODE,TO_PROVINCE_ID,")
                .append("TO_PROVINCE_CODE,ESTIMATED_DELIVERY_DAY,TRANSPORT_TYPE,LIMIT_WEIGHT,LIMIT_TYPE,")
                .append("MAX_WEIGHT,FROM_DISTRICT_ID,TO_DISTRICT_ID,FROM_DISTRICT_CODE,TO_DISTRICT_CODE,UTIMESTAMP")
                .append(" ) VALUES ( ")
                .append(" :" + LeadtimeRouteDB.LEADTIME_PACK_ID)
                .append(", 1")
                .append(", :" + LeadtimeRouteDB.STATUS)
                .append(", :" + LeadtimeRouteDB.FROM_PROVINCE_ID)
                .append(", :" + LeadtimeRouteDB.FROM_PROVINCE_CODE)
                .append(", :" + LeadtimeRouteDB.TO_PROVINCE_ID)
                .append(", :" + LeadtimeRouteDB.TO_PROVINCE_CODE)
                .append(", :" + LeadtimeRouteDB.ESTIMATED_DELIVERY_DAY)
                .append(", :" + LeadtimeRouteDB.TRANSPORT_TYPE)
                .append(", :" + LeadtimeRouteDB.LIMIT_WEIGHT)
                .append(", :" + LeadtimeRouteDB.LIMIT_TYPE)
                .append(", :" + LeadtimeRouteDB.MAX_WEIGHT)
                .append(", :" + LeadtimeRouteDB.FROM_DISTRICT_ID)
                .append(", :" + LeadtimeRouteDB.TO_DISTRICT_ID)
                .append(", :" + LeadtimeRouteDB.FROM_DISTRICT_CODE)
                .append(", :" + LeadtimeRouteDB.TO_DISTRICT_CODE)
                .append(", SYSTIMESTAMP")
                .append(" )");
        return builder.toString();
    }

    @Override
    public int updateCommand(DataSource dataSource, LeadtimeRouteId leadtimeRouteId) {
        this.setDataSource(dataSource);
        this.setSql(prepareSQL());
        this.declareParameters();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        setGeneratedKeysColumnNames("ID");
        setReturnGeneratedKeys(true);
        compile();
        int created = this.updateByNamedParam(params, keyHolder);
        if (created > 0) {
            leadtimeRouteId.setId(keyHolder.getKey().longValue());
        }
        return created;
    }

    @Override
    public void prepareParams(Object... parameters) {
        LeadtimeRouteDB leadTimePackRoutesDB = (LeadtimeRouteDB) parameters[0];

        params = new HashMap<>();
        params.put(LeadtimeRouteDB.STATUS, leadTimePackRoutesDB.getStatus());
        params.put(LeadtimeRouteDB.FROM_PROVINCE_ID, leadTimePackRoutesDB.getFromProvinceId());
        params.put(LeadtimeRouteDB.FROM_PROVINCE_CODE, leadTimePackRoutesDB.getFromProvinceCode());
        params.put(LeadtimeRouteDB.TO_PROVINCE_ID, leadTimePackRoutesDB.getToProvinceId());
        params.put(LeadtimeRouteDB.TO_PROVINCE_CODE, leadTimePackRoutesDB.getToProvinceCode());
        params.put(LeadtimeRouteDB.ESTIMATED_DELIVERY_DAY, leadTimePackRoutesDB.getEstimatedDeliveryDay());
        params.put(LeadtimeRouteDB.TRANSPORT_TYPE, leadTimePackRoutesDB.getTransportType());
        params.put(LeadtimeRouteDB.LIMIT_WEIGHT, leadTimePackRoutesDB.getLimitWeight());
        params.put(LeadtimeRouteDB.MAX_WEIGHT, leadTimePackRoutesDB.getMaxWeight());
        params.put(LeadtimeRouteDB.FROM_DISTRICT_ID, leadTimePackRoutesDB.getFromDistrictId());
        params.put(LeadtimeRouteDB.TO_DISTRICT_ID, leadTimePackRoutesDB.getToDistrictId());
        params.put(LeadtimeRouteDB.FROM_DISTRICT_CODE, leadTimePackRoutesDB.getFromDistrictCode());
        params.put(LeadtimeRouteDB.TO_DISTRICT_CODE, leadTimePackRoutesDB.getToDistrictCode());
        params.put(LeadtimeRouteDB.LIMIT_TYPE, leadTimePackRoutesDB.getLimitType());
        params.put(LeadtimeRouteDB.LEADTIME_PACK_ID, leadTimePackRoutesDB.getLeadTimePackId());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(LeadtimeRouteDB.STATUS, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.LEADTIME_PACK_ID, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.FROM_PROVINCE_ID, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.FROM_PROVINCE_CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeRouteDB.TO_PROVINCE_ID, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.TO_PROVINCE_CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeRouteDB.ESTIMATED_DELIVERY_DAY, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.TRANSPORT_TYPE, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeRouteDB.LIMIT_WEIGHT, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.MAX_WEIGHT, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.FROM_DISTRICT_ID, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.TO_DISTRICT_ID, Types.NUMERIC));
        declareParameter(new SqlParameter(LeadtimeRouteDB.FROM_DISTRICT_CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeRouteDB.TO_DISTRICT_CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(LeadtimeRouteDB.LIMIT_TYPE, Types.NUMERIC));
    }
}
