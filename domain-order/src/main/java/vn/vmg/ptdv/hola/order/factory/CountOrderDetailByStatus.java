package vn.vmg.ptdv.hola.order.factory;

import lombok.Data;

@Data
public class CountOrderDetailByStatus {
    private Integer countListOrderDetail;
    private Integer id;
    private String statusName;
    private int status;
}
