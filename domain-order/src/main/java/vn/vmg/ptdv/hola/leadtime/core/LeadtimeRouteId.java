package vn.vmg.ptdv.hola.leadtime.core;

import lombok.Data;

@Data
public class LeadtimeRouteId {

    private Long id;
    private String code;

    public LeadtimeRouteId() {

    }

    public LeadtimeRouteId(Long id, String code) {
        this.id = id;
        this.code = code;
    }

}
