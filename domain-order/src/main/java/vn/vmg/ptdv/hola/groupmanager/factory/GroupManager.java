package vn.vmg.ptdv.hola.groupmanager.factory;

import lombok.Data;
import vn.vmg.ptdv.hola.groupmanager.core.GroupManagerId;
import vn.vmg.ptdv.hola.shared.AuditLog;
@Data
public class GroupManager {
    private GroupManagerId groupManagerId;
    private String groupName;
    private Integer status;
    private AuditLog auditLog;
}
