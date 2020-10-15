package vn.vmg.ptdv.hola.infra.order.factory;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderItemDB {
    // map  data  BD
    public static final String ID = "ID";
    public static final String SHORT_ID = "SHORT_ID";
    public static final String NAME = "NAME";
    public static final String PHONE = "PHONE";
    public static final String TOTAL_DISTANCE = "TOTAL_DISTANCE";
    public static final String CREATE_AT = "CREATE_AT";
    public static final String TOTAL_ORDER_DETAIL = "TOTAL_ORDER_DETAIL";
    public static final String TOTAL_SHIP_FEE = "TOTAL_SHIP_FEE";
    public static final String TOTAL_COD = "TOTAL_COD";
    public static final String TOTAL_PRODUCT_VALUE = "TOTAL_PRODUCT_VALUE";

    // map data search
    public static final String SHIP_ID = "SHIP_ID";
    public static final String PROVINCE_CODE = "PROVINCE_CODE";
    public static final String DISTRICT_CODE = "DISTRICT_CODE";
    public static final String WARD_CODE = "WARD_CODE";
    public static final String ADDRESS = "ADDRESS";
    public static final String PHONE_SENDER = "PHONE_SENDER";
    public static final String ORDER_BY = "ORDER_BY";
    public static final String ORDER_BY_TYPE = "ORDER_BY_TYPE";
    public static final String OFFSET = "OFFSET";
    public static final String FETCH_NEXT = "FETCH_NEXT";

    private Long id;
    private String shortId;
    private String shopName;
    private String shopPhone;
    private Integer totalDistance;
    private Timestamp createAt;
    private String shopAddress;
    private Integer totalOrderDetail;
    private Long totalShipFee;
    private Long totalCod;
    private Long totalProductValue;
}
