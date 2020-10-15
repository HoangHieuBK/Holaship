package vn.vmg.ptdv.hola.api.rest.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.vmg.ptdv.hola.order.service.OrderService;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
}
