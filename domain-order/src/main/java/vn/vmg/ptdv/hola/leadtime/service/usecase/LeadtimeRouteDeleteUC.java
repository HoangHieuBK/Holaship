package vn.vmg.ptdv.hola.leadtime.service.usecase;

import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteResponse;

public interface LeadtimeRouteDeleteUC {

    LeadtimeRouteDeleteUC delete(Long id) throws OrderDomainException;

    LeadtimeRouteDeleteUC fail() throws OrderDomainException;

    LeadtimeRouteResponse end();

}
