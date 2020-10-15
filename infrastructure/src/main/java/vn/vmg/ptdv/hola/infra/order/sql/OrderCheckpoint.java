package vn.vmg.ptdv.hola.infra.order.sql;

import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.order.factory.OrderCheckPointDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderCheckpoint extends MappingSqlQuery implements MappingSQLCommand {
    private Map<String, Object> params;

    @Override
    protected OrderCheckPointDB mapRow(ResultSet rs, int i) throws SQLException {
        OrderCheckPointDB orderCheckPointDB = new OrderCheckPointDB();
        orderCheckPointDB.setOrderId(rs.getLong(OrderCheckPointDB.ORDER_ID));
        orderCheckPointDB.setProvince(rs.getString(OrderCheckPointDB.PROVINCE));
        orderCheckPointDB.setDistrict(rs.getString(OrderCheckPointDB.DISTRICT));
        orderCheckPointDB.setWard(rs.getString(OrderCheckPointDB.WARD));
        orderCheckPointDB.setAddress(rs.getString(OrderCheckPointDB.ADDRESS));
        return orderCheckPointDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT o.ORDER_ID, p.NAME PROVINCE, d.NAME DISTRICT, w.NAME WARD, o.ADDRESS")
                .append(" FROM ORDER_CHECK_POINTS o LEFT JOIN PROVINCES p ON o.PROVINCE_ID = p.ID")
                .append(" LEFT JOIN DISTRICTS d ON o.DISTRICT_ID = d.ID")
                .append(" LEFT JOIN WARDS w ON o.WARD_ID = w.ID")
                .append(" WHERE o.ORDER_ID IN (:" + OrderCheckPointDB.ORDER_ID + ")")
                .append(" AND o.TYPE = 1");
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
//        List<Long> orderIds = (List<OrderId>) parameters[0];
    }

    @Override
    public void declareParameters() {

    }
}
