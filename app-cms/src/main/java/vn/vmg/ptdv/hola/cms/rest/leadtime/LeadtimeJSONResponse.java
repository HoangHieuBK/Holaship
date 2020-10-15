package vn.vmg.ptdv.hola.cms.rest.leadtime;

import lombok.Data;

import java.time.Instant;

@Data
public class LeadtimeJSONResponse {

    private Long id;
    private String code;
    private String name;
    private Integer status;
    private String note;
    private Instant effectiveAt;
    private Instant createdAt;
    private Long createdBy;
    private Instant updatedAt;
    private Long updatedBy;
    private Instant uTimestamp;

}
