package vn.vmg.ptdv.hola.infra.order.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.order.factory.OrderItemDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.order.core.OrderId;
import vn.vmg.ptdv.hola.order.factory.search.OrderSearch;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOrderItem extends MappingSqlQuery implements MappingSQLCommand {
    private Map<String, Object> params;
    private OrderSearch search;
    private PagingSortFilter filter;
    private OrderId orderId;

    @Override
    protected OrderItemDB mapRow(ResultSet rs, int i) throws SQLException {
        OrderItemDB orderItemDB = new OrderItemDB();
        orderItemDB.setId(rs.getLong(OrderItemDB.ID));
        orderItemDB.setShortId(rs.getString(OrderItemDB.SHORT_ID));
        orderItemDB.setShopName(rs.getString(OrderItemDB.NAME));
        orderItemDB.setShopPhone(rs.getString(OrderItemDB.PHONE));
        orderItemDB.setTotalDistance(rs.getInt(OrderItemDB.TOTAL_DISTANCE));
        orderItemDB.setCreateAt(rs.getTimestamp(OrderItemDB.CREATE_AT));
        orderItemDB.setShopAddress(rs.getString(OrderItemDB.ADDRESS));
        orderItemDB.setTotalOrderDetail(rs.getInt(OrderItemDB.TOTAL_ORDER_DETAIL));
        orderItemDB.setTotalShipFee(rs.getLong(OrderItemDB.TOTAL_SHIP_FEE));
        orderItemDB.setTotalCod(rs.getLong(OrderItemDB.TOTAL_COD));
        orderItemDB.setTotalProductValue(rs.getLong(OrderItemDB.TOTAL_PRODUCT_VALUE));
        return orderItemDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder orderBy = new StringBuilder();
        if (filter.getFieldSort() == null && filter.getFieldSort().isEmpty()) {
            for (String fieldSort : filter.getFieldSort()
            ) {
                orderBy.append(" lp." + fieldSort);
            }
        } else {
            orderBy.append("lp.CREATED_AT");
        }
        orderBy.append(filter.getAsc() ? " ASC " : " DESC ");
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT o.ID, o.SHORT_ID, s.NAME, s.PHONE, o.TOTAL_DISTANCE, o.CREATE_AT, o.TOTAL_ORDER_DETAIL,")
                .append(" o.TOTAL_SHIP_FEE, o.TOTAL_COD, o.TOTAL_PRODUCT_VALUE, a.ADDRESS")
                .append(" FROM ORDERS o LEFT JOIN SHOP_ADDRESS s ON o.SHOP_ADDRESS_ID = s.ID")
                .append(" LEFT JOIN ADDRESS a ON s.ADDRESS_ID = a.ID")
                .append(" WHERE o.SHIP_ID = :" + OrderItemDB.SHIP_ID)
//                .append(" WHERE o.SHIP_ID = (SELECT ID FROM APP_USERS WHERE PHONE =:" + OrderItemDB.PHONE)
                .append(" AND ( :" + OrderItemDB.SHORT_ID + " IS NULL OR o.SHORT_ID = :" + OrderItemDB.SHORT_ID + ")")
                .append(" AND ( :" + OrderItemDB.PROVINCE_CODE + " IS NULL OR a.PROVINCE_ID = :" +
                        OrderItemDB.PROVINCE_CODE + ")")
                .append(" AND ( :" + OrderItemDB.DISTRICT_CODE + " IS NULL OR a.DISTRICT_ID = :" +
                        OrderItemDB.DISTRICT_CODE + ")")
                .append(" AND ( :" + OrderItemDB.WARD_CODE + " IS NULL OR a.WARD_ID =:" + OrderItemDB.WARD_CODE + ")")
                .append(" AND ( :" + OrderItemDB.ADDRESS + " IS NULL OR (LOWER(a.ADDRESS) LIKE  LOWER('%' || :" +
                        OrderItemDB.ADDRESS + " || '%')))")
                .append(" AND ( :" + OrderItemDB.PHONE_SENDER + " IS NULL OR s.PHONE =:" + OrderItemDB.PHONE_SENDER +
                        ")")
                .append(" ORDER BY ").append(orderBy)
                .append(" OFFSET :" + OrderItemDB.OFFSET + " ROWS")
                .append(" FETCH NEXT :" + OrderItemDB.FETCH_NEXT + " ROWS ONLY");
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
        this.orderId = (OrderId) parameters[0];
        this.search = (OrderSearch) parameters[1];
        this.filter = (PagingSortFilter) parameters[2];
        params = new HashMap<>();
        params.put(OrderItemDB.SHIP_ID, orderId.getShipId());
        params.put(OrderItemDB.SHORT_ID, search.getShortId());
        params.put(OrderItemDB.PROVINCE_CODE, search.getProvinceCode());
        params.put(OrderItemDB.DISTRICT_CODE, search.getDistrictCode());
        params.put(OrderItemDB.WARD_CODE, search.getWardCode());
        params.put(OrderItemDB.ADDRESS, search.getAddress());
        params.put(OrderItemDB.PHONE_SENDER, search.getPhoneSender());
        params.put(OrderItemDB.OFFSET, (filter.getPageIndex() - 1) * filter.getPageSize());
        params.put(OrderItemDB.FETCH_NEXT, filter.getPageSize() <= 0 ? 10 : filter.getPageSize());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(OrderItemDB.PHONE, Types.VARCHAR));
        declareParameter(new SqlParameter(OrderItemDB.SHORT_ID, Types.VARCHAR));
        declareParameter(new SqlParameter(OrderItemDB.PROVINCE_CODE, Types.NUMERIC));
        declareParameter(new SqlParameter(OrderItemDB.DISTRICT_CODE, Types.NUMERIC));
        declareParameter(new SqlParameter(OrderItemDB.WARD_CODE, Types.NUMERIC));
        declareParameter(new SqlParameter(OrderItemDB.ADDRESS, Types.VARCHAR));
        declareParameter(new SqlParameter(OrderItemDB.PHONE_SENDER, Types.VARCHAR));
        declareParameter(new SqlParameter(OrderItemDB.OFFSET, Types.NUMERIC));
        declareParameter(new SqlParameter(OrderItemDB.FETCH_NEXT, Types.NUMERIC));
    }
}
