package vn.vmg.ptdv.hola.leadtime.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.common.address.core.AddressId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;
import vn.vmg.ptdv.hola.shared.AuditLog;
import vn.vmg.ptdv.hola.shared.Vehicle;
import vn.vmg.ptdv.hola.shared.Weight;

import java.sql.Timestamp;
import java.time.Instant;

@Data
public class LeadtimeRouteRequest {

    private LeadtimeRouteId leadtimeRouteId;
    private LeadtimeId leadtimeId;
    private AddressId fromProvince;
    private AddressId toProvince;
    private AddressId fromDistrict;
    private AddressId toDistrict;
    private Integer type;
    private Integer status;
    private Integer estimatedDeliveryDay;
    private Vehicle vehicle;
    private Weight weight;
    private AuditLog auditLog;

    public LeadtimeRouteRequest() {}

    public LeadtimeRouteRequest(Long leadtimeRouteId, Long leadTimePackId, Long fromProvinceId, String fromProvinceCode, Long toProvinceId,
            String toProvinceCode, Long fromDistrictId, Long toDistrictId, String fromDistrictCode, String toDistrictCode,
            Integer type, Integer status, Integer estimatedDeliveryDay, Integer transportType, Double limitWeight, Double maxWeight,
            Integer limitType, Instant uTimestamp) {
        this.leadtimeRouteId = new LeadtimeRouteId(leadtimeRouteId, null);
        this.leadtimeId = new LeadtimeId(leadTimePackId, null);
        this.fromProvince = new AddressId(fromProvinceId, fromProvinceCode);
        this.toProvince = new AddressId(toProvinceId, toProvinceCode);
        this.fromDistrict = new AddressId(fromDistrictId, fromDistrictCode);
        this.toDistrict = new AddressId(toDistrictId, toDistrictCode);
        this.type = type;
        this.status = status;
        this.estimatedDeliveryDay = estimatedDeliveryDay;
        this.vehicle = new Vehicle(transportType);
        this.weight = new Weight(limitWeight,maxWeight,limitType);
        this.auditLog = new AuditLog().withUTimestamp(uTimestamp != null ? Timestamp.from(uTimestamp) : null);
    }


}
