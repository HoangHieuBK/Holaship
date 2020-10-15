package vn.vmg.ptdv.hola.groupmanager.core;

import lombok.Data;

@Data
public class PriceList {
    private Long id;
    private Long price;

    public PriceList(Long id, Long price) {
        this.id = id;
        this.price = price;
    }
}
