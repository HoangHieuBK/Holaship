package vn.vmg.ptdv.hola.cms.rest.groupmanager;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class GroupManagerJSONRequest {
    private String groupCode;
    private String groupName;
    private Integer status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private Instant createdAtFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private Instant createdAtTo;

    private String commonSearch;
    private Integer pageIndex;
    private Integer pageSize;
    private Boolean asc;
    private List<String> fieldSort;

    public GroupManagerJSONRequest(String groupCode, String groupName, Integer status, Instant createdAtFrom,
            Instant createdAtTo, String commonSearch, Integer pageIndex, Integer pageSize, Boolean asc,
            List<String> fieldSort) {
        this.groupCode = groupCode;
        this.groupName = groupName;
        this.status = status;
        this.createdAtFrom = createdAtFrom;
        this.createdAtTo = createdAtTo;
        this.commonSearch = commonSearch;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.asc = asc;
        this.fieldSort = fieldSort;
    }
}
