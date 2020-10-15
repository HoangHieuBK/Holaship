package vn.vmg.ptdv.hola.api.rest.order;

import lombok.Data;
import vn.vmg.ptdv.hola.order.factory.OrderItem;

import java.util.List;

@Data
public class OrderItemJSONResponse {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer totalRecord;
    List<OrderItem> orderItem;
}
