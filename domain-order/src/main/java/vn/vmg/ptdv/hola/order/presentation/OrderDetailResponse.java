package vn.vmg.ptdv.hola.order.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.order.factory.OrderDetail;
import vn.vmg.ptdv.hola.order.factory.search.PagingSortFilter;

import java.util.List;

@Data
public class OrderDetailResponse {
    private PagingSortFilter pagingSortFilter;
    List<OrderDetail> orderDetailList;
}
