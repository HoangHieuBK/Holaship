//package vn.vmg.ptdv.hola.infra.servicepack.sql;
//
//import org.springframework.jdbc.core.SqlParameter;
//import org.springframework.jdbc.object.MappingSqlQuery;
//import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
//import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;
//import vn.vmg.ptdv.hola.servicepack.factory.search.ServicePackSearch;
//import vn.vmg.ptdv.hola.shared.BaseFilter;
//
//import javax.sql.DataSource;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Types;
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ListServicePackSQL extends MappingSqlQuery implements MappingSQLCommand {
//
//    private Map<String, Object> params;
//    private BaseFilter baseFilter;
//
//    public ListServicePackSQL(BaseFilter baseFilter) {
//        this.baseFilter = baseFilter;
//    }
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
//        StringBuilder orderBy = new StringBuilder();
//        if (baseFilter.getFieldSort() != null && !baseFilter.getFieldSort().isEmpty()) {
//            for (String item : baseFilter.getFieldSort()) {
//                orderBy.append("sp." + item);
//            }
//        } else {
//            orderBy.append("sp.CREATED_AT");
//        }
//        orderBy.append(baseFilter.isAsc() ? " ASC" : " DESC");
//        StringBuilder builder = new StringBuilder();
//        builder.append("SELECT sp.ID,")
//                .append(" sp.NAME,")
//                .append(" sp.CODE,")
//                .append(" sp.CREATED_AT,")
//                .append(" sps.CREATED_BY, ")
//                .append(" sps.UPDATED_BY, ")
//                .append(" sps.UTIMESTAMP, ")
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
//                .append(" WHERE ( :" + ServicePackDB.NAME + " IS NULL OR LOWER(sp.NAME) LIKE :" + ServicePackDB.NAME +
//                        " )")
//                .append(" AND ( :" + ServicePackDB.CODE + " IS NULL OR LOWER(sp.CODE) =  :" + ServicePackDB.CODE + ")")
//                .append(" AND ( :" + ServicePackDB.STATUS + " IS NULL OR sp.STATUS = :" + ServicePackDB.STATUS + ")")
//                .append(" AND ( :" + ServicePackDB.EFFECTIVE_AT + " IS NULL OR TRUNC(sps.EFFECTIVE_AT) = TRUNC(:" +
//                        ServicePackDB.EFFECTIVE_AT + "))")
//                .append(" AND ( :" + ServicePackDB.CREATED_FROM + " IS NULL OR TRUNC(sp.CREATED_AT) >= TRUNC(:" +
//                        ServicePackDB.CREATED_FROM + "))")
//                .append(" AND ( :" + ServicePackDB.CREATED_TO + " IS NULL OR TRUNC(sp.CREATED_AT) <= TRUNC(:" +
//                        ServicePackDB.CREATED_TO + "))")
//                .append(" AND sps.ACTIVE = 1 ")
//                .append(" AND ( ( :" + ServicePackDB.COMMON_SEARCH + " IS NULL OR LOWER(sp.CODE) LIKE  :" +
//                        ServicePackDB.COMMON_SEARCH + ")")
//                .append(" OR ( :" + ServicePackDB.COMMON_SEARCH + " IS NULL OR LOWER(sp.NAME) LIKE  :" +
//                        ServicePackDB.COMMON_SEARCH + ")")
//                .append(" OR ( :" + ServicePackDB.COMMON_SEARCH + " IS NULL OR sps.MAX_TIME LIKE  :" +
//                        ServicePackDB.COMMON_SEARCH + ")")
//                .append(" OR ( :" + ServicePackDB.COMMON_SEARCH + " IS NULL OR sps.MAX_DISTANCE LIKE  :" +
//                        ServicePackDB.COMMON_SEARCH + ")")
//                .append(" OR ( :" + ServicePackDB.COMMON_SEARCH + " IS NULL OR sps.MAX_POINT LIKE  :" +
//                        ServicePackDB.COMMON_SEARCH + ")")
//                .append(" OR ( :" + ServicePackDB.COMMON_SEARCH + " IS NULL OR sps.MAX_ORDER LIKE  :" +
//                        ServicePackDB.COMMON_SEARCH + ") )")
//                .append(" ORDER BY ").append(orderBy)
//                .append(" OFFSET :" + ServicePackDB.OFFSET + " ROWS")
//                .append(" FETCH NEXT :" + ServicePackDB.FETCH_NEXT + " ROWS ONLY");
//        return builder.toString();
//    }
//
//    @Override
//    public void prepareParams(Object... parameters) {
//        ServicePackSearch servicePackSearch = (ServicePackSearch) parameters[0];
//        if (baseFilter.getPageIndex() == null)
//            baseFilter.setPageIndex(1);
//        if (baseFilter.getPageSize() == null)
//            baseFilter.setPageSize(15);
//        params = new HashMap<>();
//        params.put(ServicePackDB.NAME,
//                servicePackSearch.getName() != null ? "%" + servicePackSearch.getName().toLowerCase() + "%" : null);
////        params.put(ServicePackDB.CODE,
////                servicePackSearch.getCode() != null ? servicePackSearch.getCode().toLowerCase() : null);
////        params.put(ServicePackDB.STATUS, servicePackSearch.getStatus());
////        params.put(ServicePackDB.EFFECTIVE_AT, servicePackSearch.getEffectiveAt());
////        params.put(ServicePackDB.CREATED_FROM, servicePackSearch.getCreatedFrom());
////        params.put(ServicePackDB.CREATED_TO, servicePackSearch.getCreatedTo());
////        params.put(ServicePackDB.COMMON_SEARCH, servicePackSearch.getCommonSearch() != null ?
////                "%" + servicePackSearch.getCommonSearch().toLowerCase() + "%" : null);
////        params.put(ServicePackDB.OFFSET, Math.max((baseFilter.getPageIndex() - 1) * baseFilter.getPageSize(), 0));
////        params.put(ServicePackDB.FETCH_NEXT, baseFilter.getPageSize() <= 0 ? 15 : baseFilter.getPageSize());
//    }
//
//    @Override
//    public void declareParameters() {
//        declareParameter(new SqlParameter(ServicePackDB.NAME, Types.VARCHAR));
//        declareParameter(new SqlParameter(ServicePackDB.CODE, Types.VARCHAR));
//        declareParameter(new SqlParameter(ServicePackDB.STATUS, Types.NUMERIC));
//        declareParameter(new SqlParameter(ServicePackDB.EFFECTIVE_AT, Types.DATE));
//        declareParameter(new SqlParameter(ServicePackDB.OFFSET, Types.NUMERIC));
//        declareParameter(new SqlParameter(ServicePackDB.FETCH_NEXT, Types.NUMERIC));
//        declareParameter(new SqlParameter(ServicePackDB.COMMON_SEARCH, Types.VARCHAR));
//        declareParameter(new SqlParameter(ServicePackDB.CREATED_FROM, Types.DATE));
//        declareParameter(new SqlParameter(ServicePackDB.CREATED_TO, Types.DATE));
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