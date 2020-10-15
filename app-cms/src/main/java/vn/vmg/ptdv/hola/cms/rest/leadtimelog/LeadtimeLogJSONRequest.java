package vn.vmg.ptdv.hola.cms.rest.leadtimelog;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class LeadtimeLogJSONRequest {

    private Long id;
    private Instant createdAt;
    private Long createdBy;
    private String dataUpdated;
    private Long leadtimeId;
    private Integer pageIndex;
    private Integer pageSize;
    private Boolean asc;
    private List<String> fieldSort;

    public LeadtimeLogJSONRequest(Long id, Instant createdAt, Long createdBy, String dataUpdated, Long leadtimeId, Integer pageIndex, Integer pageSize, Boolean asc, List<String> fieldSort) {
        this.id = id;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.dataUpdated = dataUpdated;
        this.leadtimeId = leadtimeId;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.asc = asc;
        this.fieldSort = fieldSort;
    }
}
