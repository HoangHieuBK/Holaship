package vn.vmg.ptdv.hola.cms.rest.servicepack;

import lombok.Data;

import java.time.Instant;

@Data
public class SPRequest {

    private String commonSearch;
    private String name;
    private String code;
    private Boolean state;
    private Instant effectiveAt;
    private Instant createdFrom;
    private Instant createdTo;
    private Integer pageIndex;
    private Integer pageSize;

}
