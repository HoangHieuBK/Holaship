package vn.vmg.ptdv.hola.order.factory.search;

import lombok.Data;

@Data
public class OrderSearch {
    private String shortId;
    private String provinceCode;
    private String districtCode;
    private String wardCode;
    private String address;
    private String phoneSender;
    private String phoneReceiver;
    private Integer status;
}
