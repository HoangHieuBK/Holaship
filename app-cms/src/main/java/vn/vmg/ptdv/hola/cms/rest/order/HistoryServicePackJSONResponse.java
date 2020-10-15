package vn.vmg.ptdv.hola.cms.rest.order;

import lombok.Data;

import java.util.List;

@Data
public class HistoryServicePackJSONResponse {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer totalRecords;
    private List<HistoryServicePackJSON> listHistorys;
}
