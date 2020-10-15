package vn.vmg.ptdv.hola.cms.rest.order;

import lombok.Data;

import java.util.List;

@Data
public class ServicePackJSONResponseTest {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer totalRecord;
    private List<ServicePackDetailJSONResponse> list;
}
