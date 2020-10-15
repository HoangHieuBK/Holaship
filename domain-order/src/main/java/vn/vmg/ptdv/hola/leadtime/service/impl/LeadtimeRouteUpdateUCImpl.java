package vn.vmg.ptdv.hola.leadtime.service.impl;

import vn.vmg.ptdv.hola.common.address.core.AddressId;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;
import vn.vmg.ptdv.hola.leadtime.factory.LeadtimeRoute;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteResponse;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRouteRepository;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeRouteUpdateUC;
import vn.vmg.ptdv.hola.shared.AuditLog;
import vn.vmg.ptdv.hola.shared.Vehicle;
import vn.vmg.ptdv.hola.shared.Weight;

public class LeadtimeRouteUpdateUCImpl implements LeadtimeRouteUpdateUC {

    private final LeadtimeRouteRepository leadtimeRouteRepository;

    private LeadtimeRouteResponse leadtimeRouteResponse;

    private LeadtimeRoute leadtimeRoute;

    private int executedRecord;

    public LeadtimeRouteUpdateUCImpl(LeadtimeRouteRepository leadtimeRouteRepository) {
        this.leadtimeRouteRepository = leadtimeRouteRepository;
    }

    @Override
    public LeadtimeRouteUpdateUC update(LeadtimeRouteId leadtimeRouteId, AddressId fromProvince, AddressId toProvince,
                                        AddressId fromDistrict, AddressId toDistrict, Weight weight, LeadtimeId leadtimeId,
                                        Integer type, Integer status, Integer estimatedDeliveryDay, Vehicle vehicle,
                                        AuditLog auditLog) throws OrderDomainException {
        int executedRecord = leadtimeRouteRepository
                .update(leadtimeRouteId, fromProvince, toProvince, fromDistrict, toDistrict, weight,
                        leadtimeId, type, status, estimatedDeliveryDay, vehicle, auditLog);
        if (executedRecord == 0) {
            throw new OrderDomainException("Cannot update lead time");
        }
        leadtimeRoute = new LeadtimeRoute();
        leadtimeRoute.setLeadtimeRouteId(leadtimeRouteId);
        leadtimeRoute.setFromProvince(new AddressId(fromProvince.getId(), fromProvince.getCode()));
        leadtimeRoute.setToProvince(new AddressId(toProvince.getId(), toProvince.getCode()));
        leadtimeRoute.setFromDistrict(new AddressId(fromDistrict.getId(), fromDistrict.getCode()));
        leadtimeRoute.setToDistrict(new AddressId(toDistrict.getId(), toDistrict.getCode()));
        leadtimeRoute.setWeight(weight);
        leadtimeRoute.setLeadtimeId(leadtimeId);
        leadtimeRoute.setType(type);
        leadtimeRoute.setStatus(status);
        leadtimeRoute.setEstimatedDeliveryDay(estimatedDeliveryDay);
        leadtimeRoute.setVehicle(vehicle);
        leadtimeRoute.setAuditLog(auditLog);
        return this;
    }

    @Override
    public LeadtimeRouteUpdateUC fail() throws OrderDomainException {
        if (leadtimeRoute == null) {
            throw new OrderDomainException("Chỉnh sửa leadtime route thất bại");
        }
        return this;
    }

    @Override
    public LeadtimeRouteResponse end() {
        leadtimeRouteResponse = new LeadtimeRouteResponse();
        return leadtimeRouteResponse;
    }

}
