package vn.vmg.ptdv.hola.servicepack.core;

import lombok.Data;
import vn.vmg.ptdv.hola.shared.DistanceUnit;
import vn.vmg.ptdv.hola.shared.Money;

@Data
public class Distance {
    private String code;
    private Integer minValue;
    private Integer maxValue;
    private Integer baseValue;
    private Money cost;
    private DistanceUnit unit;

    public Distance withBase(Integer base) {
        this.baseValue = base;
        return this;
    }

    public Distance withMax(Integer max) {
        this.maxValue = max;
        return this;
    }

    public Distance withCost(Money cost) {
        this.cost = cost;
        return this;
    }
}
