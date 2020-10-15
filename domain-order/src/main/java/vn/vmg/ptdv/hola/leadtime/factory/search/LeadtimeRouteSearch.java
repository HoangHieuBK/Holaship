package vn.vmg.ptdv.hola.leadtime.factory.search;

import lombok.Data;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;

@Data
public class LeadtimeRouteSearch {

    private LeadtimeRouteId leadtimeRouteId;
    private LeadtimeId leadtimeId;

    public LeadtimeRouteSearch withLeadtimeRouteId(Long id, String code) {
        leadtimeRouteId = new LeadtimeRouteId(id, code);
        return this;
    }

    public LeadtimeRouteSearch withLeadtimeId(Long id, String code) {
        leadtimeId = new LeadtimeId(id, code);
        return this;
    }
}
