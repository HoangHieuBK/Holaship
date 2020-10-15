package vn.vmg.ptdv.hola.infra.order.factory;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderDetailDB {

    // map  data  BD
    public static final String SHORT_ID = "SHORT_ID";
    public static final String CONSIGNEE = "CONSIGNEE";
    public static final String PHONE = "PHONE";
    public static final String CREATE_AT = "CREATE_AT";
    public static final String PROVINCE = "PROVINCE";
    public static final String DISTRICT = "DISTRICT";
    public static final String WARD = "WARD";
    public static final String ADDRESS = "ADDRESS";
    public static final String COD = "COD";
    public static final String PRODUCT_VALUE = "PRODUCT_VALUE";
    public static final String STATUS = "STATUS";

    // map data search
    public static final String ORDER_ID = "ORDER_ID";

    private String shortId;
    private String shopName;
    private String shopPhone;
    private String receiverName;
    private String receiverPhone;
    private Timestamp createAt;
    private String receiverProvince;
    private String receiverDistrict;
    private String receiverWard;
    private String receiverAddress;
    private Long cod;
    private Long productValue;
    private Integer status;
}
