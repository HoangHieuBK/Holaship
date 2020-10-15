package vn.vmg.ptdv.hola.order.factory;

import lombok.Data;

@Data
public class OrderItemCheckPoint {
    private Long orderId;
    private String province;
    private String district;
    private String ward;
    private String address;
}
