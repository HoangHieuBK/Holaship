package vn.vmg.ptdv.hola.order.factory;

import lombok.Data;

@Data
public class OrderDetailCarrier extends OrderDetail {
    private Long carrierId;
    private String carrierCode;
    private String carrierStatus;
    private Long carrierPrice;
}
