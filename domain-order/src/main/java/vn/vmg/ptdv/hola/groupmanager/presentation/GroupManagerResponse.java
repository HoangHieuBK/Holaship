package vn.vmg.ptdv.hola.groupmanager.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.groupmanager.core.GroupManagerId;

import java.time.OffsetDateTime;

@Data
public class GroupManagerResponse {
    private GroupManagerId groupid;
    private String grounpName;
    private Integer groupSort;
    private OffsetDateTime createAt;
    private Integer status;

}
