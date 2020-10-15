package vn.vmg.ptdv.hola.servicepack.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.shared.AuditLog;

@Data
public class SPUpdateResponse {
    private Boolean success;
    private AuditLog auditLog;
    private ServicePack servicePack;
}
