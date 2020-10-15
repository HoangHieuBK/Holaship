package vn.vmg.ptdv.hola.common.address.core;

import lombok.Data;

@Data
public class AddressId {

    private Long id;
    private String code;

    public AddressId(Long id, String code) {
        this.id = id;
        this.code = code;
    }

}
