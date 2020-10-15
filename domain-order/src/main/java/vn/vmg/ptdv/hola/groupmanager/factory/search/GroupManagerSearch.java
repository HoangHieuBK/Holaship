package vn.vmg.ptdv.hola.groupmanager.factory.search;

import lombok.Data;
import vn.vmg.ptdv.hola.groupmanager.core.GroupManagerId;
import vn.vmg.ptdv.hola.shared.AuditLog;

@Data
public class GroupManagerSearch {
    private GroupManagerId groupId;
    private String groupName;
    private Integer status;
    private AuditLog createdAtFrom;
    private AuditLog createdAtTo;
    private String commonSearch;

}
