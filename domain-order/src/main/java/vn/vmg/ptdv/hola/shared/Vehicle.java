package vn.vmg.ptdv.hola.shared;

import lombok.Data;

@Data
public class Vehicle {

    private Integer type;

    public Vehicle(Integer type) {
        this.type = type;
    }

}
