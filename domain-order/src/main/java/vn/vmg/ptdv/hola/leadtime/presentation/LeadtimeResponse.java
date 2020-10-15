package vn.vmg.ptdv.hola.leadtime.presentation;

import lombok.Data;

import java.time.Instant;

@Data
public class LeadtimeResponse {

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
