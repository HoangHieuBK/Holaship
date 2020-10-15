package vn.vmg.ptdv.hola.infra.servicepack.factory;

import lombok.Data;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Data
public class ServicePackDB {

    public static final String ID = "ID";
    public static final String CODE = "CODE";
    public static final String NAME = "NAME";
    public static final String STATUS = "STATUS";
    public static final String NOTE = "NOTE";
    public static final String ACTIVE = "ACTIVE";
    public static final String MAX_TIME = "MAX_TIME";
    public static final String MAX_DISTANCE = "MAX_DISTANCE";
    public static final String MAX_POINT = "MAX_POINT";
    public static final String MAX_ORDER = "MAX_ORDER";
    public static final String BASE_DISTANCE = "BASE_DISTANCE";
    public static final String BASE_POINT = "BASE_POINT";
    public static final String BASE_COST = "BASE_COST";
    public static final String BASE_ORDER_DETAIL = "BASE_ORDER_DETAIL";
    public static final String SURCHARGE_DISTANCE = "SURCHARGE_DISTANCE";
    public static final String SURCHARGE_POINT = "SURCHARGE_POINT";
    public static final String SURCHARGE_ORDER_DETAIL = "SURCHARGE_ORDER_DETAIL";
    public static final String PORTER_FEE = "PORTER_FEE";
    public static final String DOOR_DELIVERY_FEE = "DOOR_DELIVERY_FEE";
    public static final String REFUND_FEE = "REFUND_FEE";
    public static final String PRICE_DECLARE_FEE = "PRICE_DECLARE_FEE";
    public static final String TYPE = "TYPE";
    public static final String OTHER_COST = "OTHER_COST";
    public static final String EFFECTIVE_AT = "EFFECTIVE_AT";
    public static final String CREATED_AT = "CREATED_AT";
    public static final String CREATED_AT_FROM = "CREATED_AT_FROM";
    public static final String CREATED_AT_TO = "CREATED_AT_TO";
    public static final String CREATED_BY = "CREATED_BY";
    public static final String UPDATED_AT = "UPDATED_AT";
    public static final String UPDATED_BY = "UPDATED_BY";
    public static final String UTIMESTAMP = "UTIMESTAMP";
    public static final String OFFSET = "OFFSET";
    public static final String FETCH_NEXT = "FETCH_NEXT";
    public static final String GLOBAL_SEARCH = "GLOBAL_SEARCH";
    public static final String SEARCH_SUGGEST = "SEARCH_SUGGEST";

    private Long id;
    private String code;
    private String name;
    private Integer status;
    private String note;
    private Integer active;
    private Integer maxTime;
    private Integer maxDistance;
    private Integer maxPoint;
    private Integer maxOrder;
    private Integer baseDistance;
    private Integer basePoint;
    private Integer baseCost;
    private Integer baseOrderDetail;
    private Long surchargeDistance;
    private Long surchargePoint;
    private Long surchargeOrderDetail;
    private Long porterFee;
    private Long doorDeliveryFee;
    private Long refundFee;
    private Long priceDeclareFee;
    private Integer type;
    private Long otherCost;
    private OffsetDateTime effectiveAt;
    private OffsetDateTime createdAt;
    private Long createdBy;
    private OffsetDateTime updatedAt;
    private Long updatedBy;
    private Timestamp uTimestamp;
    private String searchSuggest;

}
