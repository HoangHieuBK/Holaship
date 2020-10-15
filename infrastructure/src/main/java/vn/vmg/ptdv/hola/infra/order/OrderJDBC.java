package vn.vmg.ptdv.hola.infra.order;

import vn.vmg.ptdv.hola.infra.order.context.OrderInfraContext;
import vn.vmg.ptdv.hola.infra.order.factory.OrderCheckPointDB;
import vn.vmg.ptdv.hola.infra.order.factory.OrderDetailDB;
import vn.vmg.ptdv.hola.infra.order.factory.OrderItemDB;
import vn.vmg.ptdv.hola.infra.order.factory.TotalRecordDB;
import vn.vmg.ptdv.hola.infra.order.sql.CountListOrderItem;
import vn.vmg.ptdv.hola.infra.order.sql.ListOrderDetail;
import vn.vmg.ptdv.hola.infra.order.sql.ListOrderItem;
import vn.vmg.ptdv.hola.infra.order.sql.OrderCheckpoint;
import vn.vmg.ptdv.hola.order.core.OrderId;
import vn.vmg.ptdv.hola.order.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.shared.OrderSearch;

import javax.sql.DataSource;
import java.util.List;

public class OrderJDBC {
    private final DataSource dataSource;
    private OrderInfraContext jdbcContext;

    public OrderJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    //order-item
    public List<OrderItemDB> findListOrderItem(OrderId orderId, OrderSearch orderSearch,
            PagingSortFilter pagingSortFilter) {
        this.jdbcContext = new OrderInfraContext(dataSource);
        List<?> result = jdbcContext
                .withSQLCommand(new ListOrderItem())
                .withParams(orderId, orderSearch, pagingSortFilter)
                .executeCommand();
        return (List<OrderItemDB>) result;
    }

    public TotalRecordDB countListOrderItem(OrderId orderId, OrderSearch orderSearch) {
        this.jdbcContext = new OrderInfraContext(dataSource);
        List<TotalRecordDB> totalRecord = (List<TotalRecordDB>) jdbcContext
                .withSQLCommand(new CountListOrderItem())
                .withParams(orderId, orderSearch)
                .executeCommand();
        return (totalRecord.isEmpty() ? new TotalRecordDB() : totalRecord.get(0));
    }

    public List<OrderCheckPointDB> orderCheckPointBy(List<OrderId> orderIds) {
        this.jdbcContext = new OrderInfraContext(dataSource);
        List<?> result = jdbcContext
                .withSQLCommand(new OrderCheckpoint())
                .withParams(orderIds)
                .executeCommand();
        List<OrderCheckPointDB> orderCheckPointDBs = (List<OrderCheckPointDB>) result;
        return orderCheckPointDBs;
    }

    public List<OrderDetailDB> orderDetailByOrder(OrderId orderId) {
        this.jdbcContext = new OrderInfraContext(dataSource);
        List<?> result = jdbcContext
                .withSQLCommand(new ListOrderDetail())
                .withParams(orderId)
                .executeCommand();
        List<OrderDetailDB> orderDetailDBS = (List<OrderDetailDB>) result;
        return orderDetailDBS;
    }



}
