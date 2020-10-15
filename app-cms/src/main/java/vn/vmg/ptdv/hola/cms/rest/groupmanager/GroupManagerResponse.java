package vn.vmg.ptdv.hola.cms.rest.groupmanager;

import lombok.Data;
import vn.vmg.ptdv.hola.groupmanager.core.GroupManagerId;

import java.time.Instant;

@Data
public class GroupManagerResponse {
    private GroupManagerId groupid;
    private String grounpName;
    private Integer groupSort;
    private Instant createAt;
    private Integer status;
}
