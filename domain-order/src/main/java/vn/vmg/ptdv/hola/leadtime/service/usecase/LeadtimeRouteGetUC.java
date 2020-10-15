package vn.vmg.ptdv.hola.leadtime.service.usecase;

import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeRouteSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteListResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteResponse;

public interface LeadtimeRouteGetUC {

    LeadtimeRouteGetUC getAll(LeadtimeRouteSearch request, PagingSortFilter pagingSortFilter);

    LeadtimeRouteGetUC getById(LeadtimeRouteId leadtimeRouteId);

    LeadtimeRouteListResponse endGetAll();

    LeadtimeRouteResponse endGetById();

}
