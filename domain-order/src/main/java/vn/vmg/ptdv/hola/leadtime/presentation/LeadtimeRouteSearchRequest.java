package vn.vmg.ptdv.hola.leadtime.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeRouteSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;


@Data
public class LeadtimeRouteSearchRequest {

    private LeadtimeRouteSearch leadtimeRouteSearch;
    private PagingSortFilter pagingSortFilter;

    public LeadtimeRouteSearchRequest() {

    }

    public LeadtimeRouteSearchRequest(LeadtimeRouteSearch leadtimeRouteSearch, PagingSortFilter pagingSortFilter) throws OrderDomainException {
        this.leadtimeRouteSearch = leadtimeRouteSearch;
        this.pagingSortFilter = pagingSortFilter;
    }

}
