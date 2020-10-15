package vn.vmg.ptdv.hola.leadtime.service.usecase;

import vn.vmg.ptdv.hola.common.address.core.AddressId;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteResponse;
import vn.vmg.ptdv.hola.shared.AuditLog;
import vn.vmg.ptdv.hola.shared.Vehicle;
import vn.vmg.ptdv.hola.shared.Weight;

public interface LeadtimeRouteUpdateUC {

    LeadtimeRouteUpdateUC update(LeadtimeRouteId leadtimeRouteId, AddressId fromProvince, AddressId toProvince,
                                 AddressId fromDistrict, AddressId toDistrict, Weight weight, LeadtimeId leadtimeId,
                                 Integer type, Integer status, Integer estimatedDeliveryDay, Vehicle vehicle,
                                 AuditLog auditLog) throws OrderDomainException;

    LeadtimeRouteUpdateUC fail() throws OrderDomainException;

    LeadtimeRouteResponse end();

}
