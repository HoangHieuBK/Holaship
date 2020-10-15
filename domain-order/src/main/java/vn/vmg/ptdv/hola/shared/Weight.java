package vn.vmg.ptdv.hola.shared;

import lombok.Data;

@Data
public class Weight {

    private Double limitWeight;
    private Double maxWeight;
    private Integer limitType;

    public Weight(Double limitWeight, Double maxWeight, Integer limitType) {
        this.limitWeight = limitWeight;
        this.maxWeight = maxWeight;
        this.limitType = limitType;
    }

}
