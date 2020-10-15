package vn.vmg.ptdv.hola.shared;

import lombok.Data;

import java.util.Currency;

@Data
public class Money {

    private Long longValue;
    private Double value;
    private String display;
    private Currency currency;

    public Money(Double value) {
        this.value = value;
    }

}
