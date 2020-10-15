package vn.vmg.ptdv.hola.infra.order;

import vn.vmg.ptdv.hola.order.repository.OrderRepository;

public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJDBC orderJDBC;

    public OrderRepositoryImpl(OrderJDBC orderJDBC) {
        this.orderJDBC = orderJDBC;
    }
}
