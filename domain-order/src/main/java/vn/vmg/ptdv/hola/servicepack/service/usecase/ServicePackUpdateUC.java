package vn.vmg.ptdv.hola.servicepack.service.usecase;

import vn.vmg.ptdv.hola.exception.EntityUpdateException;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackRequest;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackResponse;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;

public interface ServicePackUpdateUC {

//    ServicePackUpdateUC update(ServicePackID servicePackID, String name, Integer status, AuditLog auditLog,
//                               OffsetDateTime effectiveAt);

    ServicePackUpdateUC applyUpdateInfo(ServicePackID servicePackID, String name, Integer status, AuditLog auditLog);

    ServicePackUpdateUC applyChangeStatusInfo(ServicePackID servicePackID, Integer status, AuditLog auditLog);

    ServicePackUpdateUC update();

    ServicePackUpdateUC changeStatus();

    ServicePackUpdateUC fail() throws EntityUpdateException;

    ServicePackResponse endUpdate();

    ServicePackResponse endChangeStatus();

}
