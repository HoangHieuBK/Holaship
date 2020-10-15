package vn.vmg.ptdv.hola.servicepack.core;

import lombok.Data;

@Data
public class ServicePackHistoryId {

    private Long id;

    public ServicePackHistoryId() {
    }

    public ServicePackHistoryId(Long id) {
        this.id = id;
    }

}
