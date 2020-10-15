package vn.vmg.ptdv.hola.leadtime.factory.search;

import lombok.Data;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeLogId;
import vn.vmg.ptdv.hola.shared.AuditLog;

@Data
public class LeadtimeLogSearch {

    private LeadtimeLogId leadtimeLogId;
    private LeadtimeId leadtimeId;
    private String dataUpdated;
    private AuditLog auditLog;

}
