package vn.vmg.ptdv.hola.infra.servicepack.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindServicePackByCode extends MappingSqlQuery implements MappingSQLCommand {
    private Map<String, Object> params;

    @Override
    protected ServicePackDB mapRow(ResultSet rs, int i) throws SQLException {
        ServicePackDB objDB = new ServicePackDB();
        objDB.setId(rs.getLong(ServicePackDB.ID));
        objDB.setCode(rs.getString(ServicePackDB.CODE));
        objDB.setName(rs.getString(ServicePackDB.NAME));
        objDB.setStatus(rs.getInt(ServicePackDB.STATUS));
        objDB.setNote(rs.getString(ServicePackDB.NOTE));
        objDB.setActive(rs.getInt(ServicePackDB.ACTIVE));
        objDB.setEffectiveAt(rs.getObject(objDB.EFFECTIVE_AT, OffsetDateTime.class));
        objDB.setCreatedAt(rs.getObject(ServicePackDB.CREATED_AT, OffsetDateTime.class));
        objDB.setCreatedBy(rs.getLong(ServicePackDB.CREATED_BY));
        objDB.setUpdatedAt(rs.getObject(ServicePackDB.UPDATED_AT, OffsetDateTime.class));
        objDB.setUpdatedBy(rs.getLong(ServicePackDB.UPDATED_BY));
        objDB.setUTimestamp(rs.getTimestamp(ServicePackDB.UTIMESTAMP));
        objDB.setMaxTime(rs.getInt(ServicePackDB.MAX_TIME));
        objDB.setMaxDistance(rs.getInt(ServicePackDB.MAX_DISTANCE));
        objDB.setMaxPoint(rs.getInt(ServicePackDB.MAX_POINT));
        objDB.setMaxOrder(rs.getInt(ServicePackDB.MAX_ORDER));
        objDB.setBaseDistance(rs.getInt(ServicePackDB.BASE_DISTANCE));
        objDB.setBasePoint(rs.getInt(ServicePackDB.BASE_POINT));
        objDB.setBaseCost(rs.getInt(ServicePackDB.BASE_COST));
        objDB.setBaseOrderDetail(rs.getInt(ServicePackDB.BASE_ORDER_DETAIL));
        objDB.setSurchargeDistance(rs.getLong(ServicePackDB.SURCHARGE_DISTANCE));
        objDB.setSurchargePoint(rs.getLong(ServicePackDB.SURCHARGE_POINT));
        objDB.setSurchargeOrderDetail(rs.getLong(ServicePackDB.SURCHARGE_ORDER_DETAIL));
        objDB.setPorterFee(rs.getLong(ServicePackDB.PORTER_FEE));
        objDB.setDoorDeliveryFee(rs.getLong(ServicePackDB.DOOR_DELIVERY_FEE));
        objDB.setRefundFee(rs.getLong(ServicePackDB.REFUND_FEE));
        objDB.setPriceDeclareFee(rs.getLong(ServicePackDB.PRICE_DECLARE_FEE));
        objDB.setType(rs.getInt(ServicePackDB.TYPE));
        objDB.setOtherCost(rs.getLong(ServicePackDB.OTHER_COST));

        return objDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT sp.ID,")
                .append(" sp.NAME,")
                .append(" sp.CODE,")
                .append(" sp.CREATED_AT,")
                .append(" sps.CREATED_BY, ")
                .append(" sps.UPDATED_AT, ")
                .append(" sps.UPDATED_BY, ")
                .append(" sps.UTIMESTAMP, ")
                .append(" sps.ACTIVE, ")
                .append(" sp.STATUS, ")
                .append(" sps.NOTE, ")
                .append(" sps.MAX_TIME, ")
                .append(" sps.MAX_DISTANCE, ")
                .append(" sps.MAX_POINT, ")
                .append(" sps.BASE_DISTANCE, ")
                .append(" sps.BASE_POINT, ")
                .append(" sps.SURCHARGE_DISTANCE, ")
                .append(" sps.SURCHARGE_POINT, ")
                .append(" sps.BASE_COST, ")
                .append(" sps.BASE_ORDER_DETAIL, ")
                .append(" sps.MAX_ORDER, ")
                .append(" sps.OTHER_COST, ")
                .append(" sps.SURCHARGE_ORDER_DETAIL, ")
                .append(" sps.PORTER_FEE, ")
                .append(" sps.DOOR_DELIVERY_FEE, ")
                .append(" sps.REFUND_FEE, ")
                .append(" sps.PRICE_DECLARE_FEE, ")
                .append(" sps.TYPE, ")
                .append(" sps.EFFECTIVE_AT ")
                .append(" FROM SERVICE_PACKS sp INNER JOIN SERVICE_PACK_SETTINGS sps ON sp.ID= sps.SERVICE_PACK_ID ")
                .append(" WHERE sp.CODE =  :" + ServicePackDB.CODE);

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
        String code = (String) parameters[0];
        params = new HashMap<>();
        params.put(ServicePackDB.CODE, code);
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(ServicePackDB.CODE, Types.VARCHAR));
    }
}
