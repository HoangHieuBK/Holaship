package vn.vmg.ptdv.hola.leadtime.mapper;

import vn.vmg.ptdv.hola.common.address.core.AddressId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;
import vn.vmg.ptdv.hola.leadtime.factory.LeadtimeRoute;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteResponse;
import vn.vmg.ptdv.hola.shared.AuditLog;
import vn.vmg.ptdv.hola.shared.Vehicle;
import vn.vmg.ptdv.hola.shared.Weight;

import java.sql.Timestamp;

public class LeadtimeRouteMapper {

    private static LeadtimeRouteMapper INSTANCE;

    private LeadtimeRouteMapper() {
    }

    public static LeadtimeRouteMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LeadtimeRouteMapper();
        }

        return INSTANCE;
    }

    public LeadtimeRouteResponse fromLeadtimeRoute(LeadtimeRoute leadtimeRoute) {
        LeadtimeRouteResponse response = new LeadtimeRouteResponse();
        if (leadtimeRoute != null) {
            LeadtimeRouteId leadtimeRouteId = leadtimeRoute.getLeadtimeRouteId();
            LeadtimeId leadtimeId = leadtimeRoute.getLeadtimeId();
            AddressId fromProvince = leadtimeRoute.getFromProvince();
            AddressId toProvince = leadtimeRoute.getFromProvince();
            AddressId fromDistrict = leadtimeRoute.getFromProvince();
            AddressId toDistrict = leadtimeRoute.getFromProvince();
            Vehicle vehicle = leadtimeRoute.getVehicle();
            Weight weight = leadtimeRoute.getWeight();
            AuditLog auditLog = leadtimeRoute.getAuditLog();

            response.setId(leadtimeRouteId != null ? leadtimeRouteId.getId() : null);
            response.setCode(leadtimeRouteId != null ? leadtimeRouteId.getCode() : null);
            response.setFromProvinceId(fromProvince != null ? fromProvince.getId() : null);
            response.setFromProvinceCode(fromProvince != null ? fromProvince.getCode() : null);
            response.setFromProvinceName(leadtimeRoute.getFromProvinceName());
            response.setToProvinceId(toProvince != null ? toProvince.getId() : null);
            response.setToProvinceCode(toProvince != null ? toProvince.getCode() : null);
            response.setToProvinceName(leadtimeRoute.getToProvinceName());
            response.setFromDistrictId(fromDistrict != null ? fromDistrict.getId() : null);
            response.setFromDistrictCode(fromDistrict != null ? fromDistrict.getCode() : null);
            response.setFromDistrictName(leadtimeRoute.getFromDistrictName());
            response.setToDistrictId(toDistrict != null ? toDistrict.getId() : null);
            response.setToDistrictCode(toDistrict != null ? toDistrict.getCode() : null);
            response.setToDistrictName(leadtimeRoute.getToDistrictName());
            response.setType(leadtimeRoute.getType());
            response.setStatus(leadtimeRoute.getStatus());
            response.setEstimatedDeliveryDay(leadtimeRoute.getEstimatedDeliveryDay());
            response.setTransportType(vehicle != null ? vehicle.getType() : null);
            response.setLimitWeight(weight != null ? weight.getLimitWeight() : null);
            response.setMaxWeight(weight != null ? weight.getMaxWeight() : null);
            response.setLimitType(weight != null ? weight.getLimitType() : null);
            response.setLeadtimeId(leadtimeId != null ? leadtimeId.getId() : null);
            if (auditLog != null) {
                Timestamp uTimestamp = auditLog.getUTimestamp();
                response.setUTimestamp(uTimestamp != null ? uTimestamp.toInstant() : null);
            }
        }
        return response;
    }

}
