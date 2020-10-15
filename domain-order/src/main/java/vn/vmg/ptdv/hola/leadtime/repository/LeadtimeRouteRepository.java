package vn.vmg.ptdv.hola.leadtime.repository;

import vn.vmg.ptdv.hola.common.address.core.AddressId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;
import vn.vmg.ptdv.hola.leadtime.factory.LeadtimeRoute;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeRouteSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.shared.AuditLog;
import vn.vmg.ptdv.hola.shared.Vehicle;
import vn.vmg.ptdv.hola.shared.Weight;

import java.util.List;

public interface LeadtimeRouteRepository {

    List<LeadtimeRoute> findAll(LeadtimeRouteSearch leadtimeRouteSearch, PagingSortFilter pagingSortFilter);

    LeadtimeRoute findById(Long id);

    int create(LeadtimeRouteId leadtimeRouteId, AddressId fromProvince, AddressId toProvince, AddressId fromDistrict,
               AddressId toDistrict, Weight weight, LeadtimeId leadtimeId, Integer type, Integer status,
               Integer estimatedDeliveryDay, Vehicle vehicle);

    int update(LeadtimeRouteId leadtimeRouteId, AddressId fromProvince, AddressId toProvince, AddressId fromDistrict,
               AddressId toDistrict, Weight weight, LeadtimeId leadtimeId, Integer type, Integer status,
               Integer estimatedDeliveryDay, Vehicle vehicle, AuditLog auditLog);

    int delete(Long id);
}
