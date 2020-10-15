package vn.vmg.ptdv.hola.order.factory;

import lombok.Data;

@Data
public class OrderCheckPoint {
    private Long id;
    private Long orderId;
    private Long provinceId;
    private Long districtId;
    private Long wardId;
    private String address;
    private Integer isInner;
    private Long rank;
    private Integer status;
    private Long longitude;
    private Long latitude;
    private Integer distanceToPick;
    private Integer distanceToNext;
    private Integer type;
}
