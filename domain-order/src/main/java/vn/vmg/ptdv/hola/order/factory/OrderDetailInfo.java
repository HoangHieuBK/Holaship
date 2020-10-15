package vn.vmg.ptdv.hola.order.factory;

import lombok.Data;

@Data
public class OrderDetailInfo extends OrderDetail {
    private Long weight;
    private Long length;
    private Long width;
    private Long height;
    private Long quantity;
    private Long productValue;
}
