package vn.vmg.ptdv.hola.servicepack.core;

import lombok.Data;

@Data
public class ServicePackID {

    private Long id;
    private String code;

    public ServicePackID() {
    }

    public ServicePackID(Long id, String code) {
        this.id = id;
        this.code = code;
    }

}
