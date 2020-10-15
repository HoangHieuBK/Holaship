package vn.vmg.ptdv.hola.leadtime.core;

import lombok.Data;

@Data
public class LeadtimeLogId {

    private Long id;

    public LeadtimeLogId(Long id) {
        this.id = id;
    }
    
}
