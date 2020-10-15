package vn.vmg.ptdv.hola.leadtime.service.usecase;

import vn.vmg.ptdv.hola.exception.EntityUpdateException;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeResponse;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;

public interface LeadtimeUpdateUC {

    LeadtimeUpdateUC update(LeadtimeId leadtimeId, String name, Integer status, String note, AuditLog auditLog,
                            OffsetDateTime effectiveAt);

    LeadtimeUpdateUC changeStatus(LeadtimeId leadtimeId, Integer status, AuditLog auditLog);

    LeadtimeUpdateUC fail() throws EntityUpdateException;

    LeadtimeResponse endUpdate(LeadtimeId leadtimeId, String name, Integer status, String note, AuditLog auditLog, OffsetDateTime effectiveAt);

    LeadtimeResponse endChangeStatus(LeadtimeId leadtimeId, Integer status, AuditLog auditLog);

}
