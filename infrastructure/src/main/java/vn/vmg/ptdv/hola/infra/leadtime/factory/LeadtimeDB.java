package vn.vmg.ptdv.hola.infra.leadtime.factory;

import lombok.Data;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Data
public class LeadtimeDB {

    public static final String ID = "ID";
    public static final String CODE = "CODE";
    public static final String NAME = "NAME";
    public static final String STATUS = "STATUS";
    public static final String NOTE = "NOTE";
    public static final String EFFECTIVE_AT = "EFFECTIVE_AT";
    public static final String CREATED_AT = "CREATED_AT";
    public static final String CREATED_AT_FROM = "CREATED_AT_FROM";
    public static final String CREATED_AT_TO = "CREATED_AT_TO";
    public static final String CREATED_BY = "CREATED_BY";
    public static final String UPDATED_AT = "UPDATED_AT";
    public static final String UPDATED_BY = "UPDATED_BY";
    public static final String UTIMESTAMP = "UTIMESTAMP";
    public static final String OFFSET = "OFFSET";
    public static final String FETCH_NEXT = "FETCH_NEXT";
    public static final String GLOBAL_SEARCH = "GLOBAL_SEARCH";
    public static final String SEARCH_SUGGEST= "SEARCH_SUGGEST";

    private Long id;
    private String code;
    private String name;
    private Integer status;
    private String note;
    private OffsetDateTime effectiveAt;
    private OffsetDateTime createdAt;
    private Long createdBy;
    private OffsetDateTime updatedAt;
    private Long updatedBy;
    private Timestamp uTimestamp;
    private String searchSuggest;

}
