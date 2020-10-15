package vn.vmg.ptdv.hola.infra.servicepack.sql;

import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class FindServicePackByIdSQL extends MappingSqlQuery implements MappingSQLCommand {
    private HashMap<String, Object> params;

    @Override
    protected ServicePackDB mapRow(ResultSet resultSet, int i) throws SQLException {
        ServicePackDB servicePackDB = new ServicePackDB();
        servicePackDB.setId(resultSet.getLong(servicePackDB.ID));
        servicePackDB.setName(resultSet.getString(servicePackDB.NAME));
        servicePackDB.setCode(resultSet.getString(servicePackDB.CODE));
//        servicePackDB.setServicePackSettingId(resultSet.getLong(servicePackDB.SERVICE_PACK_SETTING_ID));
        servicePackDB.setCreatedBy(resultSet.getLong(servicePackDB.CREATED_BY));
//        servicePackDB.setCreatedAt(resultSet.getObject(ServicePackDB.CREATED_AT, LocalDate.class));
        servicePackDB.setUpdatedBy(resultSet.getLong(servicePackDB.UPDATED_BY));
//        servicePackDB.setNameUpdatedBy(resultSet.getString(servicePackDB.NAME_UPDATED_BY));
        servicePackDB.setActive(resultSet.getInt(servicePackDB.ACTIVE));
        servicePackDB.setNote(resultSet.getString(servicePackDB.NOTE));
        servicePackDB.setMaxTime(resultSet.getInt(servicePackDB.MAX_TIME));
        servicePackDB.setMaxDistance(resultSet.getInt(servicePackDB.MAX_DISTANCE));
        servicePackDB.setMaxPoint(resultSet.getInt(servicePackDB.MAX_POINT));
        servicePackDB.setBaseDistance(resultSet.getInt(servicePackDB.BASE_DISTANCE));
        servicePackDB.setBasePoint(resultSet.getInt(servicePackDB.BASE_POINT));
        servicePackDB.setSurchargeDistance(resultSet.getLong(servicePackDB.SURCHARGE_DISTANCE));
        servicePackDB.setSurchargePoint(resultSet.getLong(servicePackDB.SURCHARGE_POINT));
        servicePackDB.setBaseCost(resultSet.getInt(servicePackDB.BASE_COST));
        servicePackDB.setBaseOrderDetail(resultSet.getInt(servicePackDB.BASE_ORDER_DETAIL));
        servicePackDB.setMaxOrder(resultSet.getInt(servicePackDB.MAX_ORDER));
        servicePackDB.setSurchargeOrderDetail(resultSet.getLong(servicePackDB.SURCHARGE_ORDER_DETAIL));
        servicePackDB.setPorterFee(resultSet.getLong(servicePackDB.PORTER_FEE));
        servicePackDB.setDoorDeliveryFee(resultSet.getLong(servicePackDB.DOOR_DELIVERY_FEE));
        servicePackDB.setRefundFee(resultSet.getLong(servicePackDB.REFUND_FEE));
        servicePackDB.setPriceDeclareFee(resultSet.getLong(servicePackDB.PRICE_DECLARE_FEE));
        servicePackDB.setType(resultSet.getInt(servicePackDB.TYPE));
//        servicePackDB.setEffectiveAt(resultSet.getObject(ServicePackDB.EFFECTIVE_AT, LocalDate.class));
        servicePackDB.setOtherCost(resultSet.getLong(servicePackDB.OTHER_COST));
        servicePackDB.setUTimestamp(resultSet.getTimestamp(servicePackDB.UTIMESTAMP));
//        servicePackDB.setUpdatedAt(resultSet.getObject(ServicePackDB.UPDATED_AT, LocalDate.class));
        return servicePackDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
//        builder.append("SELECT sp.ID ,sp.NAME, sp.CODE,")
//                .append(" sps.ID SERVICE_PACK_SETTING_ID, ")
//                .append(" sps.CREATED_BY, ")
//                .append(" sps.CREATED_AT, ")
//                .append(" sps.UPDATED_BY, ")
//                .append(" aa.NAME NAME_UPDATED_BY, ")
//                .append(" sps.UTIMESTAMP, ")
//                .append(" sps.ACTIVE, ")
//                .append(" sps.NOTE, ")
//                .append(" sps.MAX_TIME, ")
//                .append(" sps.MAX_DISTANCE, ")
//                .append(" sps.MAX_POINT, ")
//                .append(" sps.BASE_DISTANCE, ")
//                .append(" sps.BASE_POINT, ")
//                .append(" sps.SURCHARGE_DISTANCE, ")
//                .append(" sps.SURCHARGE_POINT, ")
//                .append(" sps.BASE_COST, ")
//                .append(" sps.BASE_ORDER_DETAIL, ")
//                .append(" sps.MAX_ORDER, ")
//                .append(" sps.SURCHARGE_ORDER_DETAIL, ")
//                .append(" sps.PORTER_FEE, ")
//                .append(" sps.DOOR_DELIVERY_FEE, ")
//                .append(" sps.REFUND_FEE, ")
//                .append(" sps.PRICE_DECLARE_FEE, ")
//                .append(" sps.TYPE, ")
//                .append(" sps.EFFECTIVE_AT, ")
//                .append(" sps.OTHER_COST, ")
//                .append(" sps.UPDATED_AT ")
//                .append(" FROM SERVICE_PACK_SETTINGS sps ")
//                .append(" LEFT JOIN APP_ADMINS aa ON aa.ID = sps.UPDATED_BY")
//                .append(" LEFT JOIN SERVICE_PACKS sp ON sps.SERVICE_PACK_ID = sp.ID  ")
//                .append(" WHERE sps.ID  = :" + ServicePackDB.SERVICE_PACK_SETTING_ID);

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
        Integer id = (Integer) parameters[0];
        params = new HashMap<>();
//        params.put(ServicePackDB.SERVICE_PACK_SETTING_ID, id);
    }

    @Override
    public void declareParameters() {
//        declareParameter(new SqlParameter(ServicePackDB.SERVICE_PACK_SETTING_ID, Types.NUMERIC));
    }
}
