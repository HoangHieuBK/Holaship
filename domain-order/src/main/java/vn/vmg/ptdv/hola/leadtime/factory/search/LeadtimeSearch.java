package vn.vmg.ptdv.hola.leadtime.factory.search;

import lombok.Data;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;

@Data
public class LeadtimeSearch {

    private LeadtimeId leadtimeId;
    private String name;
    private Integer status;
    private String note;
    private OffsetDateTime effectiveAt;
    private AuditLog createdAtFrom;
    private AuditLog createdAtTo;
    private String globalSearch;
    private String searchSuggest;

    public LeadtimeSearch withLeadtimeId(Long id, String code) {
        leadtimeId = new LeadtimeId(id, code);
        return this;
    }
}
