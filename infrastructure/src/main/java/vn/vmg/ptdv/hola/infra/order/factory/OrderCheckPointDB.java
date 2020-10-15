package vn.vmg.ptdv.hola.infra.order.factory;

import lombok.Data;

@Data
public class OrderCheckPointDB {
    public static final String ORDER_ID = "ORDER_ID";
    public static final String PROVINCE = "PROVINCE";
    public static final String DISTRICT = "DISTRICT";
    public static final String WARD = "WARD";
    public static final String ADDRESS = "ADDRESS";

    private Long orderId;
    private String province;
    private String district;
    private String ward;
    private String address;
}
