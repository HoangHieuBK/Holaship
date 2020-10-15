package vn.vmg.ptdv.hola.servicepack.core;

import lombok.Data;
import vn.vmg.ptdv.hola.shared.Money;

@Data
public class OrderDetailSetting {
    private String code;
    private Integer minValue;
    private Integer maxValue;
    private Integer baseValue;
    private Money cost;

    public OrderDetailSetting withBase(Integer baseValue) {
        this.baseValue = baseValue;
        return this;
    }

    public OrderDetailSetting withMax(Integer maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public OrderDetailSetting withCost(Money cost) {
        this.cost = cost;
        return this;
    }
}
