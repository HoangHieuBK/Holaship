package vn.vmg.ptdv.hola.leadtime.factory;

import lombok.Data;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;


@Data
public class Leadtime {

    private LeadtimeId leadtimeId;
    private String name;
    private Integer status;
    private String note;
    private OffsetDateTime effectiveAt;
    private AuditLog auditLog;

}
