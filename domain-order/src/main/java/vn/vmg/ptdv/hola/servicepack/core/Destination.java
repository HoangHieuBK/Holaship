package vn.vmg.ptdv.hola.servicepack.core;

import lombok.Data;
import vn.vmg.ptdv.hola.shared.Money;
import vn.vmg.ptdv.hola.shared.OrderDomainUnit;

@Data
public class Destination {
    private String code;
    private Integer minValue;
    private Integer maxValue;
    private Integer baseValue;
    private Money cost;
    private OrderDomainUnit unit;

    public Destination withBase(Integer baseValue) {
        this.baseValue = baseValue;
        return this;
    }

    public Destination withMax(Integer maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public Destination withCost(Money cost) {
        this.cost = cost;
        return this;
    }
}
