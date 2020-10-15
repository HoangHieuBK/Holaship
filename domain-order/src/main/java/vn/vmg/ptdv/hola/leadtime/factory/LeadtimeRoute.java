package vn.vmg.ptdv.hola.leadtime.factory;

import lombok.Data;
import vn.vmg.ptdv.hola.common.address.core.AddressId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;
import vn.vmg.ptdv.hola.shared.AuditLog;
import vn.vmg.ptdv.hola.shared.Vehicle;
import vn.vmg.ptdv.hola.shared.Weight;

@Data
public class LeadtimeRoute {

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
    private String fromProvinceName;
    private String toProvinceName;
    private String fromDistrictName;
    private String toDistrictName;

}
