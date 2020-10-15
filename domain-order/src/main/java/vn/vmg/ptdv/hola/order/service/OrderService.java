package vn.vmg.ptdv.hola.order.service;

import vn.vmg.ptdv.hola.order.repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
}
