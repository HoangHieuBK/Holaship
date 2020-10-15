package vn.vmg.ptdv.hola.infra.servicepack.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.servicepack.factory.search.ServicePackSearch;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindServicePacks extends MappingSqlQuery implements MappingSQLCommand {

    private Map<String, Object> params;
    private ServicePackSearch search;
    private PagingSortFilter filter;

    public FindServicePacks() {
    }

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
        StringBuilder orderBy = new StringBuilder();
        if (filter.getFieldSort() == null && filter.getFieldSort().isEmpty()) {
            for (String fieldSort : filter.getFieldSort()
            ) {
                orderBy.append(" sp." + fieldSort);
            }
        } else {
            orderBy.append("sp.CREATED_AT");
        }
        orderBy.append(filter.getAsc() ? " ASC " : " DESC ");

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
                .append(" FROM SERVICE_PACKS sp INNER JOIN SERVICE_PACK_SETTINGS sps ON sp.ID= sps.SERVICE_PACK_ID ");
        builder.append(" WHERE ( :" + ServicePackDB.GLOBAL_SEARCH + " IS NULL OR ");
        builder.append("   (LOWER(sp.CODE) = LOWER(:" + ServicePackDB.GLOBAL_SEARCH + ") OR ");
        builder.append("   LOWER(sp.NAME) LIKE LOWER('%' || :" + ServicePackDB.GLOBAL_SEARCH + " || '%') OR ");
        builder.append("   sps.MAX_TIME LIKE '%' || :" + ServicePackDB.GLOBAL_SEARCH + " || '%' OR ");
        builder.append("   sps.MAX_DISTANCE LIKE '%' || :" + ServicePackDB.GLOBAL_SEARCH + " || '%' OR ");
        builder.append("   sps.MAX_POINT LIKE '%' || :" + ServicePackDB.GLOBAL_SEARCH + " || '%' OR ");
        builder.append("   sps.MAX_ORDER LIKE '%' || :" + ServicePackDB.GLOBAL_SEARCH + " || '%')) ");
        builder.append(" AND ( :" + ServicePackDB.CODE + " IS NULL OR LOWER(sp.CODE) = LOWER(:" + ServicePackDB.CODE + "))");
        builder.append(" AND ( :" + ServicePackDB.NAME + " IS NULL OR LOWER(sp.NAME) LIKE LOWER('%' || :" + ServicePackDB.NAME + " || '%')) ");
        builder.append(" AND ( :" + ServicePackDB.STATUS + " IS NULL OR sp.STATUS = :" + ServicePackDB.STATUS + ") ");
        if (search.getEffectiveAt() != null) {
            builder.append(" AND ( :" + ServicePackDB.EFFECTIVE_AT + " IS NULL OR sps.EFFECTIVE_AT = :" + ServicePackDB.EFFECTIVE_AT + ") ");
        }
        if (!isCreateAtFromNull() && !isCreateAtToNull()) {
            builder.append(" AND ((sp.CREATED_AT >= :" + ServicePackDB.CREATED_AT_FROM + ") AND (sp.CREATED_AT <= :" + ServicePackDB.CREATED_AT_TO + ")) ");
        } else {
            if (!isCreateAtFromNull()) {
                builder.append(" AND (sp.CREATED_AT >= :" + ServicePackDB.CREATED_AT_FROM + ") ");
            }
            if (!isCreateAtToNull()) {
                builder.append(" AND (sp.CREATED_AT <= :" + ServicePackDB.CREATED_AT_TO + ") ");
            }
        }
        builder.append(" ORDER BY ").append(orderBy);
        builder.append(" OFFSET :" + ServicePackDB.OFFSET + " ROWS");
        builder.append(" FETCH NEXT :" + ServicePackDB.FETCH_NEXT + " ROWS ONLY");
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
        this.search = (ServicePackSearch) parameters[0];
        this.filter = (PagingSortFilter) parameters[1];

        params = new HashMap<>();
        params.put(ServicePackDB.GLOBAL_SEARCH, search.getGlobalSearch());
        params.put(ServicePackDB.CODE, search.getServicePackID().getCode());
        params.put(ServicePackDB.NAME, search.getName());
        params.put(ServicePackDB.STATUS, search.getStatus());
        if (search.getEffectiveAt() != null) {
            params.put(LeadtimeDB.EFFECTIVE_AT, search.getEffectiveAt());
        }
        if (!isCreateAtFromNull()) {
            params.put(ServicePackDB.CREATED_AT_FROM, search.getCreatedAtFrom().getCreatedAt().toLocalDateTime());
        }
        if (!isCreateAtToNull()) {
            params.put(ServicePackDB.CREATED_AT_TO, search.getCreatedAtTo().getCreatedAt().toLocalDateTime());
        }
        params.put(ServicePackDB.OFFSET, (filter.getPageIndex() - 1) * filter.getPageSize());
        params.put(ServicePackDB.FETCH_NEXT,
                filter.getPageSize() <= 0 ? 15 : filter.getPageSize());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(ServicePackDB.GLOBAL_SEARCH, Types.NVARCHAR));
        declareParameter(new SqlParameter(ServicePackDB.CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(ServicePackDB.NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(ServicePackDB.STATUS, Types.NUMERIC));
        if (search.getEffectiveAt() != null) {
            declareParameter(new SqlParameter(LeadtimeDB.EFFECTIVE_AT, Types.DATE));
        }
        if (!isCreateAtFromNull()) {
            declareParameter(new SqlParameter(ServicePackDB.CREATED_AT_FROM, Types.DATE));
        }
        if (!isCreateAtToNull()) {
            declareParameter(new SqlParameter(ServicePackDB.CREATED_AT_TO, Types.DATE));
        }
        declareParameter(new SqlParameter(ServicePackDB.OFFSET, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.FETCH_NEXT, Types.NUMERIC));
    }

    private boolean isCreateAtFromNull() {
        if (search.getCreatedAtFrom() == null) {
            return true;
        } else {
            if (search.getCreatedAtFrom().getCreatedAt() == null) {
                return true;
            }
        }
        return false;
    }

    private boolean isCreateAtToNull() {
        if (search.getCreatedAtTo() == null) {
            return true;
        } else {
            if (search.getCreatedAtTo().getCreatedAt() == null) {
                return true;
            }
        }
        return false;
    }

}
