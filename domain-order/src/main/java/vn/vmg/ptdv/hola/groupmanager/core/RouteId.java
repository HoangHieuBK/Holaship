package vn.vmg.ptdv.hola.groupmanager.core;

import lombok.Data;

@Data
public class RouteId {
    private Long id;
    private String code;
    public RouteId() {
    }

    public RouteId(Long id, String code) {
        this.id = id;
        this.code = code;
    }
}
