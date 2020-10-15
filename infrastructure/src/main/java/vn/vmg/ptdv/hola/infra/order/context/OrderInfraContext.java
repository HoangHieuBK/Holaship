package vn.vmg.ptdv.hola.infra.order.context;

import vn.vmg.ptdv.hola.infra.shared.InfraContext;
import vn.vmg.ptdv.hola.order.core.OrderId;

import javax.sql.DataSource;

public class OrderInfraContext  extends InfraContext<OrderId> {
    public OrderInfraContext(DataSource ds){
        super(ds);
    }
}
