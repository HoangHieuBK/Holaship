package vn.vmg.ptdv.hola.cms.rest.groupmanager;

import lombok.Data;

import java.time.Instant;

@Data
public class GroupManagerStatusRequest {
    private Integer status;
    private Instant uTimestamp;
}
