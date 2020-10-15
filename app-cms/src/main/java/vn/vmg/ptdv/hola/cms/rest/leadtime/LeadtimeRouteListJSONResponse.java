package vn.vmg.ptdv.hola.cms.rest.leadtime;

import lombok.Data;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteResponse;

import java.util.List;

@Data
public class LeadtimeRouteListJSONResponse {

    List<LeadtimeRouteResponse> leadtimeRoutes;
    private Long pageIndex;
    private Long pageSize;
    private Integer totalRecord;

}
