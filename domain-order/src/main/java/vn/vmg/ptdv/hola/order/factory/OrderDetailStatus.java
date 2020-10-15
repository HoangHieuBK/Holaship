package vn.vmg.ptdv.hola.order.factory;

import lombok.Data;

@Data
public class OrderDetailStatus extends OrderDetail {
    private Integer isPartDelivery;
    private Integer isRefund;
    private Integer isPorter;
    private Integer isDoorDelivery;
    private Integer isDeclareProduct;
    private Integer isFree;
    private Integer status;
    private Integer isDelete;
}
