package vn.vmg.ptdv.hola.order.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.order.factory.search.PagingSortFilter;

@Data
public class OrderItemRequest {
    private String code;
    private String provinceCode;
    private String districtCode;
    private String wardCode;
    private String address;
    private String phoneSender;
    private String phoneReceiver;
    private PagingSortFilter pagingSortFilter;
    private Long shipperId;
}
