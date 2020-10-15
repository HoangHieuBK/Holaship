package vn.vmg.ptdv.hola.leadtime.factory;

import lombok.Data;

import java.util.List;

@Data
public class LeadtimeRouteList {

    private List<LeadtimeRoute> leadtimeRoutes;
    private Long pageIndex;
    private Long pageSize;
    private Integer totalRecord;

}
