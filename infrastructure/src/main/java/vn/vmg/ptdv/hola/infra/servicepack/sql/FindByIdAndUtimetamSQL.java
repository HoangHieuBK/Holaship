//package vn.vmg.ptdv.hola.infra.servicepack.sql;
//
//import org.springframework.jdbc.core.SqlParameter;
//import org.springframework.jdbc.object.MappingSqlQuery;
//import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
//import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;
//
//import javax.sql.DataSource;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Types;
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.List;
//
//public class FindByIdAndUtimetamSQL extends MappingSqlQuery implements MappingSQLCommand {
//
//    HashMap<String, Object> params;
//
//    @Override
//    protected ServicePackDB mapRow(ResultSet rs, int i) throws SQLException {
//        ServicePackDB servicePackDB = new ServicePackDB();
//        servicePackDB.setId(rs.getLong(ServicePackDB.ID));
//        servicePackDB.setName(rs.getString(ServicePackDB.NAME));
//        servicePackDB.setCode(rs.getString(ServicePackDB.CODE));
//        servicePackDB.setNote(rs.getString(ServicePackDB.NOTE));
//        servicePackDB.setActive(rs.getInt(ServicePackDB.ACTIVE));
//        servicePackDB.setStatus(rs.getInt(ServicePackDB.STATUS));
//        servicePackDB.setMaxTime(rs.getInt(ServicePackDB.MAX_TIME));
//        servicePackDB.setMaxDistance(rs.getInt(ServicePackDB.MAX_DISTANCE));
//        servicePackDB.setMaxPoint(rs.getInt(ServicePackDB.MAX_POINT));
//        servicePackDB.setMaxOrder(rs.getInt(ServicePackDB.MAX_ORDER));
//        servicePackDB.setBaseDistance(rs.getInt(ServicePackDB.BASE_DISTANCE));
//        servicePackDB.setBasePoint(rs.getInt(ServicePackDB.BASE_POINT));
//        servicePackDB.setBaseCost(rs.getInt(ServicePackDB.BASE_COST));
//        servicePackDB.setBaseOrderDetail(rs.getInt(ServicePackDB.BASE_ORDER_DETAIL));
//        servicePackDB.setSurchargeDistance(rs.getLong(ServicePackDB.SURCHARGE_DISTANCE));
//        servicePackDB.setSurchargePoint(rs.getLong(ServicePackDB.SURCHARGE_POINT));
//        servicePackDB.setSurchargeOrderDetail(rs.getLong(ServicePackDB.SURCHARGE_ORDER_DETAIL));
//        servicePackDB.setPorterFee(rs.getLong(ServicePackDB.PORTER_FEE));
//        servicePackDB.setDoorDeliveryFee(rs.getLong(ServicePackDB.DOOR_DELIVERY_FEE));
//        servicePackDB.setRefundFee(rs.getLong(ServicePackDB.REFUND_FEE));
//        servicePackDB.setPriceDeclareFee(rs.getLong(ServicePackDB.PRICE_DECLARE_FEE));
//        servicePackDB.setType(rs.getInt(ServicePackDB.TYPE));
//        servicePackDB.setCreatedAt(rs.getObject(ServicePackDB.CREATED_AT, LocalDate.class));
//        servicePackDB.setCreatedBy(rs.getLong(ServicePackDB.CREATED_BY));
//        servicePackDB.setUpdatedBy(rs.getLong(ServicePackDB.UPDATED_BY));
//        servicePackDB.setUTimestamp(rs.getTimestamp(ServicePackDB.UTIMESTAMP));
//        servicePackDB.setEffectiveAt(rs.getObject(ServicePackDB.EFFECTIVE_AT, LocalDate.class));
//        servicePackDB.setOtherCost(rs.getLong(ServicePackDB.OTHER_COST));
//        return servicePackDB;
//    }
//
//    @Override
//    public String prepareSQL() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("SELECT sp.ID,")
//                .append(" sp.NAME,")
//                .append(" sp.CODE,")
//                .append(" sp.CREATED_AT,")
//                .append(" sps.CREATED_BY, ")
//                .append(" sps.UPDATED_BY, ")
//                .append(" sp.UTIMESTAMP, ")
//                .append(" sps.ACTIVE, ")
//                .append(" sp.STATUS, ")
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
//                .append(" sps.OTHER_COST, ")
//                .append(" sps.SURCHARGE_ORDER_DETAIL, ")
//                .append(" sps.PORTER_FEE, ")
//                .append(" sps.DOOR_DELIVERY_FEE, ")
//                .append(" sps.REFUND_FEE, ")
//                .append(" sps.PRICE_DECLARE_FEE, ")
//                .append(" sps.TYPE, ")
//                .append(" sps.EFFECTIVE_AT ")
//                .append(" FROM SERVICE_PACKS sp INNER JOIN SERVICE_PACK_SETTINGS sps ON sp.ID= sps.SERVICE_PACK_ID ")
//                .append(" WHERE sp.CODE =  :" + ServicePackDB.CODE);
//        return builder.toString();
//    }
//
//    @Override
//    public void prepareParams(Object... parameters) {
//        String code = (String) parameters[0];
//        params = new HashMap<>();
//        params.put(ServicePackDB.CODE, code);
//    }
//
//    @Override
//    public void declareParameters() {
//        declareParameter(new SqlParameter(ServicePackDB.CODE, Types.VARCHAR));
//    }
//
//    @Override
//    public List<?> executeCommand(DataSource dataSource) {
//        this.setDataSource(dataSource);
//        this.setSql(prepareSQL());
//        this.declareParameters();
//        return this.executeByNamedParam(params);
//    }
//}
