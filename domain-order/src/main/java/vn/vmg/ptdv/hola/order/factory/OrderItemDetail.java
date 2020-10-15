package vn.vmg.ptdv.hola.order.factory;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderItemDetail {
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
