package vn.vmg.ptdv.hola.order.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.order.factory.OrderItem;
import vn.vmg.ptdv.hola.order.factory.search.PagingSortFilter;

import java.util.List;

@Data
public class OrderItemResponse {
    private PagingSortFilter pagingSortFilter;
    List<OrderItem> orderList;
}
