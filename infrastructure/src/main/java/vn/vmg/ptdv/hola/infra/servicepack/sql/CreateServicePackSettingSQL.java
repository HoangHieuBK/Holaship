package vn.vmg.ptdv.hola.infra.servicepack.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class CreateServicePackSettingSQL extends SqlUpdate implements UpdateSQLCommand<AccountId> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO SERVICE_PACK_SETTINGS (")
                .append(" SERVICE_PACK_ID, CREATED_BY, CREATED_AT, UPDATED_BY, UTIMESTAMP, ACTIVE ")
                .append(" , NOTE, MAX_TIME, MAX_DISTANCE, MAX_POINT, BASE_DISTANCE, BASE_POINT")
                .append(" , SURCHARGE_DISTANCE, SURCHARGE_POINT, BASE_COST, BASE_ORDER_DETAIL, MAX_ORDER")
                .append(" , SURCHARGE_ORDER_DETAIL, PORTER_FEE, DOOR_DELIVERY_FEE, REFUND_FEE, PRICE_DECLARE_FEE")
                .append(" , TYPE, EFFECTIVE_AT, OTHER_COST, UPDATED_AT")
                .append(" ) VALUES ( ")
                .append(" :SERVICE_PACK_ID")
                .append(", :CREATED_BY")
                .append(", SYSDATE")
                .append(", :UPDATED_BY")
                .append(", SYSTIMESTAMP")
                .append(", 1")
                .append(", :NOTE")
                .append(", :MAX_TIME")
                .append(", :MAX_DISTANCE")
                .append(", :MAX_POINT")
                .append(", :BASE_DISTANCE")
                .append(", :BASE_POINT")
                .append(", :SURCHARGE_DISTANCE")
                .append(", :SURCHARGE_POINT")
                .append(", :BASE_COST")
                .append(", :BASE_ORDER_DETAIL")
                .append(", :MAX_ORDER")
                .append(", :SURCHARGE_ORDER_DETAIL")
                .append(", :PORTER_FEE")
                .append(", :DOOR_DELIVERY_FEE")
                .append(", :REFUND_FEE")
                .append(", :PRICE_DECLARE_FEE")
                .append(", 1")
                .append(", :EFFECTIVE_AT")
                .append(", :OTHER_COST")
                .append(", SYSDATE )");
        return builder.toString();
    }

    @Override
    public int updateCommand(DataSource dataSource, AccountId accountId) {
        this.setDataSource(dataSource);
        this.setSql(this.prepareSQL());
        this.declareParameters();
        compile();
        int created = this.updateByNamedParam(params);
        return created;
    }

    @Override
    public void prepareParams(Object... parameters) {
        ServicePackDB servicePackDB = (ServicePackDB) parameters[0];
        params = new HashMap<String, Object>();
//        params.put(ServicePackDB.SERVICE_PACK_ID, servicePackDB.getServicePackId());
        params.put(ServicePackDB.CREATED_BY, servicePackDB.getCreatedBy());
        params.put(ServicePackDB.NOTE, servicePackDB.getNote());
        params.put(ServicePackDB.MAX_TIME, servicePackDB.getMaxTime());
        params.put(ServicePackDB.MAX_DISTANCE, servicePackDB.getMaxDistance());
        params.put(ServicePackDB.MAX_POINT, servicePackDB.getMaxPoint());
        params.put(ServicePackDB.BASE_DISTANCE, servicePackDB.getBaseDistance());
        params.put(ServicePackDB.BASE_POINT, servicePackDB.getBasePoint());
        params.put(ServicePackDB.SURCHARGE_DISTANCE, servicePackDB.getSurchargeDistance());
        params.put(ServicePackDB.SURCHARGE_POINT, servicePackDB.getSurchargePoint());
        params.put(ServicePackDB.BASE_COST, servicePackDB.getBaseCost());
        params.put(ServicePackDB.BASE_ORDER_DETAIL, servicePackDB.getBaseOrderDetail());
        params.put(ServicePackDB.MAX_ORDER, servicePackDB.getMaxOrder());
        params.put(ServicePackDB.SURCHARGE_ORDER_DETAIL, servicePackDB.getSurchargeOrderDetail());
        params.put(ServicePackDB.PORTER_FEE, servicePackDB.getPorterFee());
        params.put(ServicePackDB.DOOR_DELIVERY_FEE, servicePackDB.getDoorDeliveryFee());
        params.put(ServicePackDB.REFUND_FEE, servicePackDB.getRefundFee());
        params.put(ServicePackDB.PRICE_DECLARE_FEE, servicePackDB.getPriceDeclareFee());
        params.put(ServicePackDB.EFFECTIVE_AT, servicePackDB.getEffectiveAt());
        params.put(ServicePackDB.OTHER_COST, servicePackDB.getOtherCost());
    }

    @Override
    public void declareParameters() {
//        declareParameter(new SqlParameter(ServicePackDB.SERVICE_PACK_ID, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.CREATED_BY, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.NOTE, Types.VARCHAR));
        declareParameter(new SqlParameter(ServicePackDB.MAX_TIME, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.MAX_DISTANCE, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.MAX_POINT, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.BASE_DISTANCE, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.BASE_POINT, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.SURCHARGE_DISTANCE, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.SURCHARGE_POINT, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.BASE_COST, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.BASE_ORDER_DETAIL, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.MAX_ORDER, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.SURCHARGE_ORDER_DETAIL, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.PORTER_FEE, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.DOOR_DELIVERY_FEE, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.REFUND_FEE, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.PRICE_DECLARE_FEE, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.EFFECTIVE_AT, Types.DATE));
        declareParameter(new SqlParameter(ServicePackDB.OTHER_COST, Types.NUMERIC));
    }
}
