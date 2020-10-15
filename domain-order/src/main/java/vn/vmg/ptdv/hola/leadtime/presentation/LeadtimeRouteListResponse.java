package vn.vmg.ptdv.hola.leadtime.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.leadtime.factory.LeadtimeRoute;

import java.util.List;

@Data
public class LeadtimeRouteListResponse {

    List<LeadtimeRoute> leadtimeRoutes;

}
