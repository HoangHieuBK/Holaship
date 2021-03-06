package vn.vmg.ptdv.hola.cms.rest.servicepack;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ServicePackSearchJSONRequest {

    private Long id;
    private String name;
    private String code;
    private Instant effectiveAt;
    private Long createdBy;
    private Instant createdAtFrom;
    private Instant createdAtTo;
    private Integer status;
    private String note;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Instant uTimestamp;

    private String globalSearch;
    private Integer pageIndex;
    private Integer pageSize;
    private Boolean asc;
    private List<String> fieldSort;
    private String searchSuggest;

}
