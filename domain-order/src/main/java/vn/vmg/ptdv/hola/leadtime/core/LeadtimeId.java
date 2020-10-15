package vn.vmg.ptdv.hola.leadtime.core;

import lombok.Data;

@Data
public class LeadtimeId {

    private Long id;
    private String code;

    public LeadtimeId() {
    }

    public LeadtimeId(Long id, String code) {
        this.id = id;
        this.code = code;
    }
}
