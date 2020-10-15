package vn.vmg.ptdv.hola.servicepack.service.usecase;

import vn.vmg.ptdv.hola.exception.EntityInsertException;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeResponse;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackResponse;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;

public interface ServicePackCreateUC {

    ServicePackCreateUC applyCreateInfo(ServicePackID servicePackID, String name, AuditLog auditLog);

    ServicePackCreateUC create();

    ServicePackCreateUC fail() throws EntityInsertException;

    ServicePackResponse end();

}
