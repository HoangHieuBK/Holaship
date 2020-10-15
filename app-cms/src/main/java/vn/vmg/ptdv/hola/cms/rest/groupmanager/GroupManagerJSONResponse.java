package vn.vmg.ptdv.hola.cms.rest.groupmanager;

import lombok.Data;

import java.util.List;

@Data
public class GroupManagerJSONResponse {
    private List<GroupManagerResponse> list;
    private Long pageIndex;
    private Long pageSize;
    private Integer totalRecord;

}
