package vn.vmg.ptdv.hola.leadtime.service.usecase;

import vn.vmg.ptdv.hola.exception.EntityInsertException;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeResponse;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;

public interface LeadtimeCreateUC {

    LeadtimeCreateUC create(LeadtimeId leadtimeId, String name, Integer status, String note, AuditLog auditLog,
                            OffsetDateTime effectiveAt);

    LeadtimeCreateUC fail() throws EntityInsertException;

    LeadtimeResponse end(LeadtimeId leadtimeId, String name, Integer status, String note, AuditLog auditLog,
                         OffsetDateTime effectiveAt);

}
